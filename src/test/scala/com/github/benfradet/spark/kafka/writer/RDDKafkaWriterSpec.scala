package com.github.benfradet.spark.kafka.writer

import org.apache.kafka.clients.producer.ProducerRecord

import scala.concurrent.duration._

class RDDKafkaWriterSpec extends SKRSpec {

  "a RDDKafkaWriterSpec" when {
    "given a RDD" should {
      "write its content to Kafka" in {
        val localTopic = topic
        val msgs = (1 to 10).map(_.toString)
        val rdd = ssc.sparkContext.parallelize(msgs)
        import KafkaWriter._
        rdd.writeToKafka(
          producerConfig,
          s => new ProducerRecord[String, String](localTopic, s)
        )

        val results = collect(ssc, localTopic)

        ssc.start()
        eventually(timeout(30.seconds), interval(1.second)) {
          results shouldBe msgs
        }
      }
    }
  }
}
