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

import io.github.embeddedkafka.{EmbeddedKafka, EmbeddedKafkaConfig}

import java.util.concurrent.atomic.AtomicInteger
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.scalatest.concurrent.Eventually
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

case class Foo(a: Int, b: String)

trait SKRSpec
  extends AnyWordSpec
  with Matchers
  with BeforeAndAfterEach
  with BeforeAndAfterAll
  with Eventually
  with EmbeddedKafka {
  private val brokerHost = "localhost"
  private val brokerPort = 9092
  implicit val config = EmbeddedKafkaConfig(kafkaPort = brokerPort)

  val sparkConf = new SparkConf()
    .setMaster("local[1]")
    .setAppName(getClass.getSimpleName)

  override def beforeAll(): Unit = {
    if(EmbeddedKafka.isRunning) {
      EmbeddedKafka.stop()
    }
    EmbeddedKafka.start()
  }
  override def afterAll(): Unit = {
    SKRSpec.callbackTriggerCount.set(0)
    EmbeddedKafka.stop()
  }

  var topic: String = _
  var ssc: StreamingContext = _
  var spark: SparkSession = _
  override def afterEach(): Unit = {
    if (ssc != null) {
      ssc.stop()
      ssc = null
    }
    if (spark != null) {
      spark.stop()
      spark = null
    }
  }
  override def beforeEach(): Unit = {
    ssc = new StreamingContext(sparkConf, Seconds(1))
    spark = SparkSession.builder
      .config(sparkConf)
      .getOrCreate()
    topic = s"topic-${Random.nextInt()}"
    EmbeddedKafka.createCustomTopic(topic)
  }

  def collect(ssc: StreamingContext, topic: String): ArrayBuffer[String] = {
    val kafkaParams = Map(
      "bootstrap.servers" -> s"$brokerHost:$brokerPort",
      "auto.offset.reset" -> "earliest",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "test-collect"
    )
    val results = new ArrayBuffer[String]
    KafkaUtils.createDirectStream[String, String](
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](Set(topic), kafkaParams)
    ).map(_.value())
      .foreachRDD { rdd =>
        results ++= rdd.collect()
        ()
      }
    results
  }

  val producerConfig = Map(
    "bootstrap.servers" -> "127.0.0.1:9092",
    "key.serializer" -> classOf[StringSerializer].getName,
    "value.serializer" -> classOf[StringSerializer].getName
  )
}

object SKRSpec {
  val callbackTriggerCount = new AtomicInteger()
}
