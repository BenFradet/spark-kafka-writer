package com.github.benfradet.spark.kafka.writer

import java.util.Properties

import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.DStream

import scala.reflect.ClassTag

abstract class KafkaWriter[T: ClassTag] {
  def writeToKafka[K, V](
    producerConfig: Properties,
    transformFunc: T => ProducerRecord[K, V]
  )
}
