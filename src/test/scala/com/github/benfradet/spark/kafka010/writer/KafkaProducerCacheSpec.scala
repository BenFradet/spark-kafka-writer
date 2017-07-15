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

package com.github.benfradet.spark.kafka010.writer

import com.google.common.cache.Cache
import org.apache.kafka.clients.producer._
import org.apache.kafka.common.serialization.StringSerializer
import org.scalatest.PrivateMethodTester

import scala.concurrent.duration._

class KafkaProducerCacheSpec extends SKRSpec with PrivateMethodTester {
  val cache = PrivateMethod[Cache[Seq[(String, Object)], KafkaProducer[_, _]]]('cache)
  val m1 = Map(
    "bootstrap.servers" -> "127.0.0.1:9092",
    "key.serializer" -> classOf[StringSerializer].getName,
    "value.serializer" -> classOf[StringSerializer].getName
  )
  val m2 = m1 + ("acks" -> "0")

  override def beforeAll(): Unit = {
    super.beforeAll()
    KafkaProducerCache.invokePrivate(cache()).invalidateAll()
  }

  "A KafkaProducerCache" when {
    "calling getProducer" should {
      "create the producer if it doesn't exist and retrieve it if it exists" in {
        cacheSize shouldBe 0
        val p1 = KafkaProducerCache.getProducer[String, String](m1)
        cacheSize shouldBe 1
        val p2 = KafkaProducerCache.getProducer[String, String](m1)
        p1 shouldBe p2
        cacheSize shouldBe 1
      }
    }

    "closing a producer" should {
      "close the correct producer" in {
        cacheSize shouldBe 1
        val p1 = KafkaProducerCache.getProducer[String, String](m1)
        cacheSize shouldBe 1
        val p2 = KafkaProducerCache.getProducer[String, String](m2)
        cacheSize shouldBe 2
        p1 should not be p2
        KafkaProducerCache.close(m1)
        cacheSize shouldBe 1
      }
    }
  }

  private def cacheSize: Int = KafkaProducerCache.invokePrivate(cache()).asMap.size
}