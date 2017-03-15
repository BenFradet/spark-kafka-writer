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

import org.apache.kafka.clients.producer._

import scala.concurrent.duration._

class RDDKafkaWriterSpec extends SKRSpec {

  "a RDDKafkaWriterSpec v010" when {
    "given a RDD" should {
      "write its content to Kafka" in {
        val localTopic = topic
        val msgs = (1 to 10).map(_.toString)
        val rdd = ssc.sparkContext.parallelize(msgs)
        rdd.writeToKafka(
          producerConfig,
          s => new ProducerRecord[String, String](localTopic, s)
        )

        val results = collect(ssc, localTopic)

        ssc.start()
        eventually(timeout(30.seconds), interval(1.second)) {
          results shouldBe msgs
        }
      }

      "trigger a given callback for every write to Kafka" in {
        val localTopic = topic
        val msgs = (1 to 10).map(_.toString)
        val rdd = ssc.sparkContext.parallelize(msgs)
        rdd.writeToKafka(
          producerConfig,
          s => new ProducerRecord[String, String](localTopic, s),
          Some(new Callback with Serializable {
            override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
              SKRSpec.callbackTriggerCount.incrementAndGet()
            }
          })
        )

        eventually(timeout(30.seconds), interval(1.second)) {
          SKRSpec.callbackTriggerCount.get() shouldBe 10
        }
      }
    }
  }
}
