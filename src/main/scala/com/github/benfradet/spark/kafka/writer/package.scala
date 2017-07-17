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

package com.github.benfradet.spark.kafka

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Dataset
import org.apache.spark.streaming.dstream.DStream

import scala.reflect.ClassTag

/** Implicit conversions between
 *  - [[DStream]] -> [[KafkaWriter]]
 *  - [[RDD]] -> [[KafkaWriter]]
 *  - [[Dataset]] -> [[KafkaWriter]]
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

  /**
   * Convert a [[Dataset]] to a [[KafkaWriter]] implicitly
   * @param dataset [[Dataset]] to be converted
   * @return [[KafkaWriter]] ready to write messages to Kafka
   */
  implicit def datasetToKafkaWriter[T: ClassTag, K, V](dataset: Dataset[T]): KafkaWriter[T] =
    new DatasetKafkaWriter[T](dataset)
}
