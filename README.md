# spark-kafka-writer

[![Build Status](https://travis-ci.org/BenFradet/spark-kafka-writer.svg?branch=master)](https://travis-ci.org/BenFradet/spark-kafka-writer)
[![Join the chat at https://gitter.im/BenFradet/spark-kafka-writer](https://badges.gitter.im/BenFradet/spark-kafka-writer.svg)](https://gitter.im/BenFradet/spark-kafka-writer?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.benfradet/spark-kafka-writer_2.11.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.benfradet/spark-kafka-writer_2.11)

Write your `RDD`s and `DStream`s to Kafka seamlessly

## Installation

spark-kafka-writer is available on maven central:

- if you're using sbt:

```scala
libraryDependencies ++= Seq(
  "com.github.benfradet" %% "spark-kafka-writer" % "0.1.0"
)
```

- if you're using mvn:

```xml
<dependencies>
    <dependency>
        <groupId>com.github.benfradet</groupId>
        <artifactId>spark-kafka-writer_${scala.binary.version}</artifactId>
        <version>0.1.0</version>
    </dependency>
</dependencies>
```

## Usage

- if you want to save an `RDD` to Kafka

```scala
import java.util.Properties

import com.github.benfradet.spark.kafka.writer.KafkaWriter._
import org.apache.kafka.common.serialization.StringSerializer

val topic = "my-topic"
val producerConfig = {
  val p = new Properties()
  p.setProperty("bootstrap.servers", "127.0.0.1:9092")
  p.setProperty("key.serializer", classOf[StringSerializer].getName)
  p.setProperty("value.serializer", classOf[StringSerializer].getName)
  p
}

val rdd: RDD[String] = ...
rdd.writeToKafka(
  producerConfig,
  s => new ProducerRecord[String, String](localTopic, s)
)
```

- if you want to save a `DStream` to Kafka

```scala
import java.util.Properties

import com.github.benfradet.spark.kafka.writer.KafkaWriter._
import org.apache.kafka.common.serialization.StringSerializer

val topic = "my-topic"
val producerConfig = {
  val p = new Properties()
  p.setProperty("bootstrap.servers", "127.0.0.1:9092")
  p.setProperty("key.serializer", classOf[StringSerializer].getName)
  p.setProperty("value.serializer", classOf[StringSerializer].getName)
  p
}

val dStream: DStream[String] = ...
dStream.writeToKafka(
  producerConfig,
  s => new ProducerRecord[String, String](topic, s)
)
```

## Credit

The original code was written by [Hari Shreedharan](https://github.com/harishreedharan).
