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

import org.apache.kafka.clients.producer._

import scala.concurrent.duration._

class DatasetKafkaWriterSpec extends SKRSpec {

  "a DatasetKafkaWriter" when {
    "given a Dataset" should {
      "write its content to Kafka" in {
        val s = spark
        import s.implicits._
        val localTopic = topic
        val msgs = (1 to 10).map(i => Foo(i, i.toString))
        val dataset = s.createDataset[Foo](msgs)
        dataset.writeToKafka(
          producerConfig,
          f => new ProducerRecord[String, String](localTopic, f.toString)
        )

        val results = collect(ssc, localTopic)

        ssc.start()
        eventually(timeout(30.seconds), interval(1.second)) {
          results shouldBe msgs.map(_.toString)
        }
      }

      "trigger a given callback for every write to Kafka" in {
        val s = spark
        import s.implicits._
        val localTopic = topic
        val msgs = (1 to 10).map(i => Foo(i, i.toString))
        val dataset = s.createDataset[Foo](msgs)
        dataset.writeToKafka(
          producerConfig,
          f => new ProducerRecord[String, String](localTopic, f.toString),
          Some(new Callback with Serializable {
            override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
              SKRSpec.callbackTriggerCount.incrementAndGet()
            }
          })
        )

        eventually(timeout(30.seconds), interval(1.second)) {
          SKRSpec.callbackTriggerCount.get() shouldBe msgs.size
        }
      }
    }
  }
}
