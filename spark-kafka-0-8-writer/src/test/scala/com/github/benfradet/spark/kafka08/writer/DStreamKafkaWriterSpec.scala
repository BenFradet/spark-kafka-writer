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

import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.DStream

import scala.collection.mutable
import scala.concurrent.duration._

class DStreamKafkaWriterSpec extends SKRSpec {

  "a DStreamKafkaWriter v08" when {
    "given a dstream" should {
      "write its content to Kafka" in {
        val localTopic = topic
        val msgs = (1 to 10).map(_.toString)
        val stream = createDStream(msgs)
        stream.writeToKafka(
          producerConfig,
          s => new ProducerRecord[String, String](localTopic, s)
        )

        val results = collect(ssc, localTopic)

        ssc.start()
        eventually(timeout(30.seconds), interval(1.second)) {
          results shouldBe msgs
        }
      }
    }
  }

  private def createDStream(seq: Seq[String]): DStream[String] = {
    val q = mutable.Queue.empty[RDD[String]]
    q.enqueue(ssc.sparkContext.makeRDD(seq))
    ssc.queueStream(q)
  }
}
