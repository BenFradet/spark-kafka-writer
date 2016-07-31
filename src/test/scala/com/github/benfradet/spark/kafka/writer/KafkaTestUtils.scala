package com.github.benfradet.spark.kafka.writer

import java.io.File
import java.net.InetSocketAddress
import java.util.Properties

import kafka.admin.AdminUtils
import kafka.consumer.{ConsumerConfig, Consumer, KafkaStream, ConsumerConnector}
import kafka.server.{KafkaServerStartable, KafkaConfig}
import kafka.utils.ZKStringSerializer
import org.I0Itec.zkclient.ZkClient
import org.apache.kafka.clients.producer.{ProducerRecord, KafkaProducer}
import org.apache.zookeeper.server.{NIOServerCnxnFactory, ZooKeeperServer}

import scala.util.Random

class KafkaTestUtils {
  // zk
  private val zkHost = "localhost"
  private val zkPort = 2181
  private val zkSessionTimeout = 6000
  private val zkConnectionTimeout = 6000
  private var zk: EmbeddedZookeeper = _
  private var zkClient: ZkClient = _
  private var zkReady = false

  // kafka
  private val brokerHost = "localhost"
  private val brokerPort = 9092
  private var kafkaServer: KafkaServerStartable = _
  private var producer: KafkaProducer[String, String] = _
  private var consumer: ConsumerConnector = _
  private var topicCountMap = Map.empty[String, Int]
  private var consumerMap:
    scala.collection.Map[String, List[KafkaStream[Array[Byte], Array[Byte]]]] = _
  private var brokerReady = false

  /** Zookeeper address */
  def zkAddress: String = {
    assert(zkReady, "Zk not ready, cannot get address")
    s"$zkHost:$zkPort"
  }

  /** Kafka broker address */
  def brokerAddress: String = {
    assert(brokerReady, "Broker not ready, cannot get address")
    s"$brokerHost:$brokerPort"
  }

  /** Zookeeper client */
  def zookeeperClient: ZkClient = {
    assert(zkReady, "Zk not ready, cannot get zk client")
    Option(zkClient).getOrElse(
      throw new IllegalStateException("Zk client not initialized"))
  }

  /** Start the Zookeeper and Kafka servers */
  def setup(): Unit = {
    setupEmbeddedZookeeper()
    setupEmbeddedKafkaServer()
  }

  private def setupEmbeddedZookeeper(): Unit = {
    zk = new EmbeddedZookeeper(zkHost, zkPort)
    zkClient = new ZkClient(s"$zkHost:$zkPort", zkSessionTimeout,
      zkConnectionTimeout, ZKStringSerializer)
    zkReady = true
  }

  private def setupEmbeddedKafkaServer(): Unit = {
    assert(zkReady, "Zk should be setup beforehand")
    val kafkaConfig = new KafkaConfig(brokerProps)
    kafkaServer = new KafkaServerStartable(kafkaConfig)
    kafkaServer.startup()
    brokerReady = true
  }

  /** Close the Kafka producer, consumer and server as well as the Zookeeper client and server */
  def tearDown(): Unit = {
    brokerReady = false
    zkReady = false

    if (consumer != null) {
      consumer.shutdown()
      consumer = null
    }

    if (kafkaServer != null) {
      kafkaServer.shutdown()
      kafkaServer = null
    }

    if (zkClient != null) {
      zkClient.close()
      zkClient = null
    }

    if (zk != null) {
      zk.shutdown()
      zk = null
    }

    topicCountMap = Map.empty
  }

  /** Create one or more topics */
  @scala.annotation.varargs
  def createTopics(topics: String*): Unit =
    for (topic <- topics) {
      AdminUtils.createTopic(zkClient, topic, 1, 1)
      topicCountMap = topicCountMap + (topic -> 1)
    }

  /** Send messages to a topic */
  def sendMessages(topic: String, messages: Seq[String]): Unit = {
    producer = new KafkaProducer[String, String](producerProps)
    messages.foreach(m => producer.send(new ProducerRecord[String, String](topic, m)))
    producer.close()
    producer = null
  }

  /** Send messages to a topic */
  def sendMessages(topic: String, messages: Map[String, String]): Unit = {
    producer = new KafkaProducer[String, String](producerProps)
    messages.foreach {
      case (k, v) =>
        producer.send(new ProducerRecord[String, String](topic, k, v))
    }
    producer.close()
    producer = null
  }

  /** Send a single message to a topic */
  def sendMessage(topic: String, message: String): Unit =
    sendMessage(topic, null, message)

  /** Send a single message to a topic */
  def sendMessage(topic: String, key: String, value: String): Unit = {
    producer = new KafkaProducer[String, String](producerProps)
    producer.send(new ProducerRecord[String, String](topic, key, value))
    producer.close()
    producer = null
  }

  /** Initialize the consumer */
  def initConsumer(): Unit = {
    consumer = Consumer.create(new ConsumerConfig(consumerProps))
    consumerMap = consumer.createMessageStreams(topicCountMap)
  }

  /** Retrieve the next message in a topic */
  def getNextMessage(topic: String): Option[String] = {
    val stream = consumerMap.get(topic).get(0)
    val it = stream.iterator()
    if (it.hasNext()) {
      Some(new String(it.next().message()))
    } else {
      None
    }
  }

  private def brokerProps: Properties = {
    val props = new Properties
    props.put("broker.id", "0")
    props.put("host.name", brokerHost)
    props.put("log.dir",
      {
        val dir = System.getProperty("java.io.tmpdir") +
          "/logDir-" + new Random().nextInt(Int.MaxValue)
        val f = new File(dir)
        f.mkdirs()
        dir
      }
    )
    props.put("port", brokerPort.toString)
    props.put("zookeeper.connect", zkAddress)
    props.put("zookeeper.connection.timeout.ms", "10000")
    props
  }

  private def producerProps: Properties = {
    val props = new Properties
    props.put("client.id", getClass.getSimpleName)
    props.put("bootstrap.servers", brokerAddress)
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("acks", "0")
    props.put("buffer.memory", "1048576")
    props.put("compression.type", "snappy")
    props
  }

  private def consumerProps = {
    val props = new Properties
    props.put("metadata.broker.list", brokerAddress)
    props.put("auto.offset.reset", "smallest")
    props.put("zookeeper.connect", zkAddress)
    props.put("group.id", "consumer")
    props
  }

  private class EmbeddedZookeeper(hostname: String, port: Int) {
    private val snapshotDir = {
      val f = new File(System.getProperty("java.io.tmpdir"),
        "snapshotDir-" + Random.nextInt(Int.MaxValue))
      f.mkdirs()
      f
    }
    private val logDir = {
      val f = new File(System.getProperty("java.io.tmpdir"),
        "logDir-" + Random.nextInt(Int.MaxValue))
      f.mkdirs()
      f
    }

    private val factory = {
      val zkTickTime = 500
      val zk = new ZooKeeperServer(snapshotDir, logDir, zkTickTime)
      val f = new NIOServerCnxnFactory
      val maxCnxn = 16
      f.configure(new InetSocketAddress(hostname, port), maxCnxn)
      f.startup(zk)
      f
    }

    def shutdown(): Unit = {
      factory.shutdown()
      snapshotDir.delete()
      logDir.delete()
      ()
    }
  }
}

