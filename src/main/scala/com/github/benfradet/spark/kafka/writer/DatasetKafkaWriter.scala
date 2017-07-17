/**
 * Copyright (c) 2016-2017, Benjamin Fradet, and other contributors.
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

package com.github.benfradet.spark.kafka.writer

import org.apache.kafka.clients.producer.{Callback, ProducerRecord}
import org.apache.spark.sql.Dataset

import scala.reflect.ClassTag

/**
 * Class used for writing [[Dataset]]s to Kafka
 * @param dataset [[Dataset]] to be written to Kafka
 */
class DatasetKafkaWriter[T: ClassTag](@transient private val dataset: Dataset[T])
    extends KafkaWriter[T] with Serializable {
  /**
   * Write a [[Dataset]] to Kafka
   * @param producerConfig producer configuration for creating KafkaProducer
   * @param transformFunc a function used to transform values of T type into [[ProducerRecord]]s
   * @param callback an optional [[Callback]] to be called after each write, default value is None.
   */
  override def writeToKafka[K, V](
    producerConfig: Map[String, Object],
    transformFunc: T => ProducerRecord[K, V],
    callback: Option[Callback] = None
  ): Unit = {
    val rddWriter = new RDDKafkaWriter[T](dataset.rdd)
    rddWriter.writeToKafka(producerConfig, transformFunc, callback)
  }
}
