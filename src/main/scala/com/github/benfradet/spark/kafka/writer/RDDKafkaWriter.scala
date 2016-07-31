package com.github.benfradet.spark.kafka.writer

import java.util.Properties

import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.spark.rdd.RDD

import scala.reflect.ClassTag

class RDDKafkaWriter[T: ClassTag](@transient rdd: RDD[T])
    extends KafkaWriter[T] with Serializable {
  override def writeToKafka[K, V](
    producerConfig: Properties,
    transformFunc: T => ProducerRecord[K, V]
  ): Unit =
    rdd.foreachPartition { partition =>
      val producer = KafkaProducerCache.getProducer[K, V](producerConfig)
      partition
        .map(transformFunc)
        .foreach(record => producer.send(record))
    }
}
