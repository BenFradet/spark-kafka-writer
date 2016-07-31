package com.github.benfradet.spark.kafka.writer

import java.util.Properties

import kafka.serializer.StringDecoder
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.kafka.KafkaUtils
import org.scalatest.concurrent.Eventually
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfter, Matchers, WordSpec}

import scala.collection.mutable.ArrayBuffer

trait SKRSpec
  extends WordSpec
  with Matchers
  with BeforeAndAfter
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
