package com.github.benfradet.spark.kafka010

import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.DStream

import scala.reflect.ClassTag

/** Implicit conversions between
 * [[DStream]] -> [[com.github.benfradet.spark.kafka010.writer.KafkaWriter]]
 * and
 * [[RDD]] -> [[com.github.benfradet.spark.kafka010.writer.KafkaWriter]]
 */
package object writer {
  /**
   * Convert a [[DStream]] to a [[KafkaWriter]] implicitly
   * @param dStream [[DStream]] to be converted
   * @return [[KafkaWriter]] ready to write messages to Kafka
   */
  implicit def dStreamToKafkaWriter[T: ClassTag, K, V](dStream: DStream[T]): KafkaWriter[T] =
    new DStreamKafkaWriter[T](dStream)

  /**
   * Convert a [[RDD]] to a [[KafkaWriter]] implicitly
   * @param rdd [[RDD]] to be converted
   * @return [[KafkaWriter]] ready to write messages to Kafka
   */
  implicit def rddToKafkaWriter[T: ClassTag, K, V](rdd: RDD[T]): KafkaWriter[T] =
    new RDDKafkaWriter[T](rdd)
}
