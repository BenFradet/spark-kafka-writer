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
| **Spark 2.1.X** | `"com.github.benfradet" %% "spark-kafka-0-8-writer" % "0.3.0"` | `"com.github.benfradet" %% "spark-kafka-0-10-writer" % "0.3.0"` |
| **Spark 2.0.X** | `"com.github.benfradet" %% "spark-kafka-0-8-writer" % "0.2.0"` | `"com.github.benfradet" %% "spark-kafka-0-10-writer" % "0.2.0"` |
| **Spark 1.6.X** | `"com.github.benfradet" %% "spark-kafka-writer" % "0.1.0"` | :x: |

## Usage

### Without callbacks

- if you want to save an `RDD` to Kafka

```scala
// replace by kafka08 if you're using Kafka 0.8
import com.github.benfradet.spark.kafka010.writer._
import org.apache.kafka.common.serialization.StringSerializer

val topic = "my-topic"
val producerConfig = Map(
  "bootstrap.servers" -> "127.0.0.1:9092",
  "key.serializer" -> classOf[StringSerializer].getName,
  "value.serializer" -> classOf[StringSerializer].getName
)

val rdd: RDD[String] = ...
rdd.writeToKafka(
  producerConfig,
  s => new ProducerRecord[String, String](topic, s)
)
```

- if you want to save a `DStream` to Kafka

```scala
// replace by kafka08 if you're using Kafka 0.8
import com.github.benfradet.spark.kafka010.writer._
import org.apache.kafka.common.serialization.StringSerializer

val dStream: DStream[String] = ...
dStream.writeToKafka(
  producerConfig,
  s => new ProducerRecord[String, String](topic, s)
)
```

### With callbacks

It is also possible to assign a `Callback` from the Kafka Producer API that will
be triggered after each write, this has a default value of None.

The `Callback` must implement the `onCompletion` method and the `Exception`
parameter will be `null` if the write was successful.

Any `Callback` implementations will need to be serializable to be used in Spark.

- if you want to use a `Callback` when saving an `RDD` to Kafka

```scala
// replace by kafka08 if you're using Kafka 0.8
import com.github.benfradet.spark.kafka010.writer._
import org.apache.kafka.clients.producer.{Callback, ProducerRecord, RecordMetadata}

@transient lazy val log = org.apache.log4j.Logger.getLogger("spark-kafka-writer")

val rdd: RDD[String] = ...
rdd.writeToKafka(
  producerConfig,
  s => new ProducerRecord[String, String](topic, s),
  Some(new Callback with Serializable {
    override def onCompletion(metadata: RecordMetadata, e: Exception): Unit = {
      if (Option(e).isDefined) {
        log.warn("error sending message", e)
      } else {
        log.info(s"write succeeded! offset: ${metadata.offset()}")
      }
    }
  })
)
```

- if you want to use a `Callback` when saving a `DStream` to Kafka

```scala
// replace by kafka08 if you're using Kafka 0.8
import com.github.benfradet.spark.kafka010.writer._
import org.apache.kafka.clients.producer.{Callback, ProducerRecord, RecordMetadata}

val dStream: DStream[String] = ...
dStream.writeToKafka(
  producerConfig,
  s => new ProducerRecord[String, String](topic, s),
  Some(new Callback with Serializable {
    override def onCompletion(metadata: RecordMetadata, e: Exception): Unit = {
      if (Option(e).isDefined) {
        log.warn("error sending message", e)
      } else {
        log.info(s"write succeeded! offset: ${metadata.offset()}")
      }
    }
  })
)
```

Check out [the Kafka documentation](http://kafka.apache.org/0102/javadoc/org/apache/kafka/clients/producer/KafkaProducer.html#send(org.apache.kafka.clients.producer.ProducerRecord,%20org.apache.kafka.clients.producer.Callback))
to know more about callbacks.

### Java usage

It's also possible to use the library from Java:

```java
// Define a serializable Function1 separately
abstract class SerializableFunc1<T, R> extends AbstractFunction1<T, R> implements Serializable {}

Map<String, Object> producerConfig = new HashMap<String, Object>();
producerConfig.put("bootstrap.servers", "localhost:9092");
producerConfig.put("key.serializer", StringSerializer.class);
producerConfig.put("value.serializer", StringSerializer.class);

KafkaWriter<String> kafkaWriter = new DStreamKafkaWriter<>(javaDStream.dstream(),
    scala.reflect.ClassTag$.MODULE$.apply(String.class));
kafkaWriter.writeToKafka(producerConfig.asScala,
     new SerializableFunc1<String, ProducerRecord<String, String>>() {
         @Override
         public ProducerRecord<String, String> apply(final String s) {
             return new ProducerRecord<>(topic, s);
         }
     },
     //new Some<>((metadata, exception) -> {}), // with callback, define your lambda here.
     Option.empty()                             // or without callback.
);
```

However, [#59](https://github.com/benfradet/spark-kafka-writer/issues/59) will provide a better Java API.

## Scaladoc

You can find the full scaladoc at
https://benfradet.github.io/spark-kafka-writer.

## Adopters

- _Submit a pull-request to include your company/project into the list_

## Credit

The original code was written by [Hari Shreedharan](https://github.com/harishreedharan).
