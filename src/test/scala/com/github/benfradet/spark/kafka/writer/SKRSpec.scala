package com.github.benfradet.spark.kafka.writer

import java.util.Properties

import kafka.serializer.StringDecoder
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.scalatest.concurrent.Eventually
import org.scalatest.{BeforeAndAfterEach, BeforeAndAfterAll, Matchers, WordSpec}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

trait SKRSpec
  extends WordSpec
  with Matchers
  with BeforeAndAfterEach
  with BeforeAndAfterAll
  with Eventually {

  val sparkConf = new SparkConf()
    .setMaster("local[1]")
    .setAppName(getClass.getSimpleName)

  var ktu: KafkaTestUtils = _
  override def beforeAll(): Unit = {
    ktu = new KafkaTestUtils
    ktu.setup()
  }
  override def afterAll(): Unit = {
    if (ktu != null) {
      ktu.tearDown()
      ktu = null
    }
  }

  var topic: String = _
  var ssc: StreamingContext = _
  override def afterEach(): Unit = {
    if (ssc != null) {
      ssc.stop()
      ssc = null
    }
  }
  override def beforeEach(): Unit = {
    ssc = new StreamingContext(sparkConf, Seconds(1))
    topic = s"topic-${Random.nextInt()}"
    ktu.createTopics(topic)
  }

  def collect(ssc: StreamingContext, topic: String): ArrayBuffer[String] = {
    val kafkaParams = Map(
      "metadata.broker.list" -> ktu.brokerAddress,
      "auto.offset.reset" -> "smallest"
    )
    val results = new ArrayBuffer[String]
    KafkaUtils
      .createDirectStream[String, String, StringDecoder, StringDecoder](
      ssc, kafkaParams, Set(topic))
      .map(_._2)
      .foreachRDD { rdd =>
        results ++= rdd.collect()
        ()
      }
    results
  }

  val producerConfig = {
    val p = new Properties()
    p.setProperty("bootstrap.servers", "127.0.0.1:9092")
    p.setProperty("key.serializer", classOf[StringSerializer].getName)
    p.setProperty("value.serializer", classOf[StringSerializer].getName)
    p
  }
}
