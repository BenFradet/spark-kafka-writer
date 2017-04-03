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

import org.apache.kafka.clients.producer.KafkaProducer

import scala.collection.mutable

/** Cache of [[KafkaProducer]]s */
private[writer] object KafkaProducerCache {
  private val producers = mutable.HashMap.empty[Properties, KafkaProducer[_, _]]

  /**
   * Retrieve a [[KafkaProducer]] in the cache or create a new one
   * @param producerConfig properties for a [[KafkaProducer]]
   * @return a [[KafkaProducer]] already in the cache
   */
  def getProducer[K, V](producerConfig: Properties): KafkaProducer[K, V] = {
    producers
      .getOrElseUpdate(producerConfig, new KafkaProducer[K, V](producerConfig))
      .asInstanceOf[KafkaProducer[K, V]]
  }
}
