/**
 * Copyright (c) 2016-2016, Benjamin Fradet, and other contributors.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.github.benfradet.spark.kafka08.writer

import java.util.Properties

import org.apache.kafka.clients.producer.{Callback, ProducerRecord}
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.DStream

import scala.reflect.ClassTag

/**
 * Class used to write [[DStream]]s and [[RDD]]s to Kafka
 * Example usage:
 * {{{
 *   import java.util.Properties
 *
 *   import com.github.benfradet.spark.kafka08.writer.KafkaWriter._
 *   import org.apache.kafka.common.serialization.StringSerializer
 *
 *   val topic = "my-topic"
 *   val producerConfig = {
 *     val p = new Properties()
 *     p.setProperty("bootstrap.servers", "127.0.0.1:9092")
 *     p.setProperty("key.serializer", classOf[StringSerializer].getName)
 *     p.setProperty("value.serializer", classOf[StringSerializer].getName)
 *     p
 *   }
 *
 *   val dStream: DStream[String] = ...
 *   dStream.writeToKafka(
 *     producerConfig,
 *     s => new ProducerRecord[String, String](topic, s)
 *   )
 *
 *   val rdd: RDD[String] = ...
 *   rdd.writeToKafka(
 *     producerConfig,
 *     s => new ProducerRecord[String, String](localTopic, s)
 *   )
 * }}}
 */
abstract class KafkaWriter[T: ClassTag] extends Serializable {
  /**
   * Write a [[DStream]] or [[RDD]] to Kafka
   * @param producerConfig properties for a [[org.apache.kafka.clients.producer.KafkaProducer]]
   * @param transformFunc a function used to transform values of T type into [[ProducerRecord]]s
   */
  def writeToKafka[K, V](
    producerConfig: Properties,
    transformFunc: T => ProducerRecord[K, V],
    callback: Option[Callback] = None
  ): Unit
}
