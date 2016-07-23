package com.github.benfradet.spark.kafka.writer

import java.util.Properties

import org.apache.kafka.clients.producer.KafkaProducer

import scala.collection.mutable

private[writer] object KafkaProducerCache {
  private val producers = mutable.HashMap.empty[Properties, KafkaProducer[_, _]]

  def getProducer[K, V](producerConfig: Properties): KafkaProducer[K, V] = {
    producers.getOrElse(producerConfig, {
      val producer = new KafkaProducer[K, V](producerConfig)
      producers(producerConfig) = producer
      producer
    }).asInstanceOf[KafkaProducer[K, V]]
  }
}
