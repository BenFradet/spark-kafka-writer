package com.github.benfradet.spark.kafka.writer

import java.util.Properties

import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.spark.streaming.dstream.DStream

import scala.reflect.ClassTag

class DStreamKafkaWriter[T: ClassTag](@transient dStream: DStream[T]) extends KafkaWriter[T] {
  override def writeToKafka[K, V](
    producerConfig: Properties,
    transformFunc: T => ProducerRecord[K, V]
  ): Unit =
    dStream.foreachRDD { rdd =>
      val rddWriter = new RDDKafkaWriter[T](rdd)
      rddWriter.writeToKafka(producerConfig, transformFunc)
    }
}
