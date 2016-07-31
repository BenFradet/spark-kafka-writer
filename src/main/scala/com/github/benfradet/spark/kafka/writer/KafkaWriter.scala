package com.github.benfradet.spark.kafka.writer

import java.util.Properties

import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.DStream

import scala.reflect.ClassTag

object KafkaWriter {
  implicit def dStreamToKafkaWriter[T: ClassTag, K, V](dStream: DStream[T]): KafkaWriter[T] =
    new DStreamKafkaWriter[T](dStream)
  implicit def rddToKafkaWriter[T: ClassTag, K, V](rdd: RDD[T]): KafkaWriter[T] =
    new RDDKafkaWriter[T](rdd)
}

abstract class KafkaWriter[T: ClassTag] extends Serializable {
  def writeToKafka[K, V](
    producerConfig: Properties,
    transformFunc: T => ProducerRecord[K, V]
  ): Unit
}
