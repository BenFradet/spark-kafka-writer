# spark-kafka-writer

[![Build Status](https://travis-ci.org/BenFradet/spark-kafka-writer.svg?branch=master)](https://travis-ci.org/BenFradet/spark-kafka-writer)
[![codecov](https://codecov.io/gh/BenFradet/spark-kafka-writer/branch/master/graph/badge.svg)](https://codecov.io/gh/BenFradet/spark-kafka-writer)
[![Join the chat at https://gitter.im/BenFradet/spark-kafka-writer](https://badges.gitter.im/BenFradet/spark-kafka-writer.svg)](https://gitter.im/BenFradet/spark-kafka-writer?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.benfradet/spark-kafka-0-10-writer_2.11.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.benfradet/spark-kafka-0-10-writer_2.11)
[![Stories in Ready](https://badge.waffle.io/BenFradet/spark-kafka-writer.png?label=ready&title=Ready)](https://waffle.io/BenFradet/spark-kafka-writer)

Write your `RDD`s and `DStream`s to Kafka seamlessly

## Installation

spark-kafka-writer is available on maven central with the following coordinates depending on whether
you're using Kafka 0.8 or 0.10 and your version of Spark:

|   | Kafka 0.8 | Kafka 0.10 |
|:-:|:-:|:-:|
| **Spark 1.6.X** | `"com.github.benfradet" %% "spark-kafka-writer" % "0.1.0"` | :x: |
| **Spark 2.0.X** | `"com.github.benfradet" %% "spark-kafka-0-8-writer" % "0.2.0"` | `"com.github.benfradet" %% "spark-kafka-0-10-writer" % "0.2.0"` |

## Usage

- if you want to save an `RDD` to Kafka

```scala
import java.util.Properties

// replace by kafka08 if you're using Kafka 0.8
import com.github.benfradet.spark.kafka010.writer._
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
  s => new ProducerRecord[String, String](topic, s)
)
```

- if you want to save a `DStream` to Kafka

```scala
import java.util.Properties

// replace by kafka08 if you're using Kafka 0.8
import com.github.benfradet.spark.kafka010.writer._
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

### Callback

It is also possible to assign a `Callback` that will be triggered after each write, this has a default value of None.
The `Callback` must implement the `onComplete` method and the `Exception` parameter will be `null` if the write was successful. 
Any `Callback` implementations will need to be serializable to be used in Spark.

- If you want to use a `Callback` when saving an `RDD` to Kafka

```scala
val rdd: RDD[String] = ...
rdd.writeToKafka(
  producerConfig,
  s => new ProducerRecord[String, String](topic, s),
  Some(new Callback with Serializable {
    override def onCompletion(metadata: RecordMetadata, e: Exception) = {
      if(Option(e).isDefined) "write failed!" else "write succeeded!"
    }
  })
)
```

- If you want to use a `Callback` when saving a `DStream` to Kafka

```scala
val dStream: DStream[String] = ...
dStream.writeToKafka(
  producerConfig,
  s => new ProducerRecord[String, String](topic, s),
  Some(new Callback with Serializable {
      override def onCompletion(metadata: RecordMetadata, e: Exception) = {
        if(Option(e).isDefined) "write failed!" else "write succeeded!"
      }
  })
)
```

You can find the full scaladoc at
https://benfradet.github.io/spark-kafka-writer.

## Credit

The original code was written by [Hari Shreedharan](https://github.com/harishreedharan).
