Index.PACKAGES = {"com.github.benfradet.spark.kafka" : [], "com.github.benfradet.spark" : [], "com" : [], "com.github.benfradet.spark.kafka.writer" : [{"name" : "com.github.benfradet.spark.kafka.writer.DatasetKafkaWriter", "shortDescription" : "Class used for writing Datasets to Kafka", "members_class" : [{"label" : "writeToKafka", "tail" : "(producerConfig: Map[String, AnyRef], transformFunc: (T) ⇒ ProducerRecord[K, V], callback: Option[Callback]): Unit", "member" : "com.github.benfradet.spark.kafka.writer.DatasetKafkaWriter.writeToKafka", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#writeToKafka[K,V](producerConfig:Map[String,Object],transformFunc:T=>org.apache.kafka.clients.producer.ProducerRecord[K,V],callback:Option[org.apache.kafka.clients.producer.Callback]):Unit", "kind" : "def"}, {"member" : "com.github.benfradet.spark.kafka.writer.DatasetKafkaWriter#<init>", "error" : "unsupported entity"}, {"label" : "synchronized", "tail" : "(arg0: ⇒ T0): T0", "member" : "scala.AnyRef.synchronized", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#synchronized[T0](x$1:=>T0):T0", "kind" : "final def"}, {"label" : "##", "tail" : "(): Int", "member" : "scala.AnyRef.##", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html###():Int", "kind" : "final def"}, {"label" : "!=", "tail" : "(arg0: Any): Boolean", "member" : "scala.AnyRef.!=", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#!=(x$1:Any):Boolean", "kind" : "final def"}, {"label" : "==", "tail" : "(arg0: Any): Boolean", "member" : "scala.AnyRef.==", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#==(x$1:Any):Boolean", "kind" : "final def"}, {"label" : "ne", "tail" : "(arg0: AnyRef): Boolean", "member" : "scala.AnyRef.ne", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#ne(x$1:AnyRef):Boolean", "kind" : "final def"}, {"label" : "eq", "tail" : "(arg0: AnyRef): Boolean", "member" : "scala.AnyRef.eq", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#eq(x$1:AnyRef):Boolean", "kind" : "final def"}, {"label" : "finalize", "tail" : "(): Unit", "member" : "scala.AnyRef.finalize", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#finalize():Unit", "kind" : "def"}, {"label" : "wait", "tail" : "(): Unit", "member" : "scala.AnyRef.wait", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#wait():Unit", "kind" : "final def"}, {"label" : "wait", "tail" : "(arg0: Long, arg1: Int): Unit", "member" : "scala.AnyRef.wait", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#wait(x$1:Long,x$2:Int):Unit", "kind" : "final def"}, {"label" : "wait", "tail" : "(arg0: Long): Unit", "member" : "scala.AnyRef.wait", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#wait(x$1:Long):Unit", "kind" : "final def"}, {"label" : "notifyAll", "tail" : "(): Unit", "member" : "scala.AnyRef.notifyAll", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#notifyAll():Unit", "kind" : "final def"}, {"label" : "notify", "tail" : "(): Unit", "member" : "scala.AnyRef.notify", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#notify():Unit", "kind" : "final def"}, {"label" : "toString", "tail" : "(): String", "member" : "scala.AnyRef.toString", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#toString():String", "kind" : "def"}, {"label" : "clone", "tail" : "(): AnyRef", "member" : "scala.AnyRef.clone", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#clone():Object", "kind" : "def"}, {"label" : "equals", "tail" : "(arg0: Any): Boolean", "member" : "scala.AnyRef.equals", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#equals(x$1:Any):Boolean", "kind" : "def"}, {"label" : "hashCode", "tail" : "(): Int", "member" : "scala.AnyRef.hashCode", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#hashCode():Int", "kind" : "def"}, {"label" : "getClass", "tail" : "(): Class[_]", "member" : "scala.AnyRef.getClass", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#getClass():Class[_]", "kind" : "final def"}, {"label" : "asInstanceOf", "tail" : "(): T0", "member" : "scala.Any.asInstanceOf", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#asInstanceOf[T0]:T0", "kind" : "final def"}, {"label" : "isInstanceOf", "tail" : "(): Boolean", "member" : "scala.Any.isInstanceOf", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html#isInstanceOf[T0]:Boolean", "kind" : "final def"}], "class" : "com\/github\/benfradet\/spark\/kafka\/writer\/DatasetKafkaWriter.html", "kind" : "class"}, {"name" : "com.github.benfradet.spark.kafka.writer.DStreamKafkaWriter", "shortDescription" : "Class used for writing DStreams to Kafka", "members_class" : [{"label" : "writeToKafka", "tail" : "(producerConfig: Map[String, AnyRef], transformFunc: (T) ⇒ ProducerRecord[K, V], callback: Option[Callback]): Unit", "member" : "com.github.benfradet.spark.kafka.writer.DStreamKafkaWriter.writeToKafka", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#writeToKafka[K,V](producerConfig:Map[String,Object],transformFunc:T=>org.apache.kafka.clients.producer.ProducerRecord[K,V],callback:Option[org.apache.kafka.clients.producer.Callback]):Unit", "kind" : "def"}, {"member" : "com.github.benfradet.spark.kafka.writer.DStreamKafkaWriter#<init>", "error" : "unsupported entity"}, {"label" : "synchronized", "tail" : "(arg0: ⇒ T0): T0", "member" : "scala.AnyRef.synchronized", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#synchronized[T0](x$1:=>T0):T0", "kind" : "final def"}, {"label" : "##", "tail" : "(): Int", "member" : "scala.AnyRef.##", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html###():Int", "kind" : "final def"}, {"label" : "!=", "tail" : "(arg0: Any): Boolean", "member" : "scala.AnyRef.!=", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#!=(x$1:Any):Boolean", "kind" : "final def"}, {"label" : "==", "tail" : "(arg0: Any): Boolean", "member" : "scala.AnyRef.==", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#==(x$1:Any):Boolean", "kind" : "final def"}, {"label" : "ne", "tail" : "(arg0: AnyRef): Boolean", "member" : "scala.AnyRef.ne", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#ne(x$1:AnyRef):Boolean", "kind" : "final def"}, {"label" : "eq", "tail" : "(arg0: AnyRef): Boolean", "member" : "scala.AnyRef.eq", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#eq(x$1:AnyRef):Boolean", "kind" : "final def"}, {"label" : "finalize", "tail" : "(): Unit", "member" : "scala.AnyRef.finalize", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#finalize():Unit", "kind" : "def"}, {"label" : "wait", "tail" : "(): Unit", "member" : "scala.AnyRef.wait", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#wait():Unit", "kind" : "final def"}, {"label" : "wait", "tail" : "(arg0: Long, arg1: Int): Unit", "member" : "scala.AnyRef.wait", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#wait(x$1:Long,x$2:Int):Unit", "kind" : "final def"}, {"label" : "wait", "tail" : "(arg0: Long): Unit", "member" : "scala.AnyRef.wait", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#wait(x$1:Long):Unit", "kind" : "final def"}, {"label" : "notifyAll", "tail" : "(): Unit", "member" : "scala.AnyRef.notifyAll", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#notifyAll():Unit", "kind" : "final def"}, {"label" : "notify", "tail" : "(): Unit", "member" : "scala.AnyRef.notify", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#notify():Unit", "kind" : "final def"}, {"label" : "toString", "tail" : "(): String", "member" : "scala.AnyRef.toString", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#toString():String", "kind" : "def"}, {"label" : "clone", "tail" : "(): AnyRef", "member" : "scala.AnyRef.clone", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#clone():Object", "kind" : "def"}, {"label" : "equals", "tail" : "(arg0: Any): Boolean", "member" : "scala.AnyRef.equals", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#equals(x$1:Any):Boolean", "kind" : "def"}, {"label" : "hashCode", "tail" : "(): Int", "member" : "scala.AnyRef.hashCode", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#hashCode():Int", "kind" : "def"}, {"label" : "getClass", "tail" : "(): Class[_]", "member" : "scala.AnyRef.getClass", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#getClass():Class[_]", "kind" : "final def"}, {"label" : "asInstanceOf", "tail" : "(): T0", "member" : "scala.Any.asInstanceOf", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#asInstanceOf[T0]:T0", "kind" : "final def"}, {"label" : "isInstanceOf", "tail" : "(): Boolean", "member" : "scala.Any.isInstanceOf", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html#isInstanceOf[T0]:Boolean", "kind" : "final def"}], "class" : "com\/github\/benfradet\/spark\/kafka\/writer\/DStreamKafkaWriter.html", "kind" : "class"}, {"name" : "com.github.benfradet.spark.kafka.writer.KafkaProducerCache", "shortDescription" : "Cache of KafkaProducers", "object" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html", "members_object" : [{"label" : "close", "tail" : "(producerConfig: Map[String, AnyRef]): Unit", "member" : "com.github.benfradet.spark.kafka.writer.KafkaProducerCache.close", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#close(producerConfig:Map[String,Object]):Unit", "kind" : "def"}, {"label" : "getProducer", "tail" : "(producerConfig: Map[String, AnyRef]): KafkaProducer[K, V]", "member" : "com.github.benfradet.spark.kafka.writer.KafkaProducerCache.getProducer", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#getProducer[K,V](producerConfig:Map[String,Object]):org.apache.kafka.clients.producer.KafkaProducer[K,V]", "kind" : "def"}, {"label" : "synchronized", "tail" : "(arg0: ⇒ T0): T0", "member" : "scala.AnyRef.synchronized", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#synchronized[T0](x$1:=>T0):T0", "kind" : "final def"}, {"label" : "##", "tail" : "(): Int", "member" : "scala.AnyRef.##", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html###():Int", "kind" : "final def"}, {"label" : "!=", "tail" : "(arg0: Any): Boolean", "member" : "scala.AnyRef.!=", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#!=(x$1:Any):Boolean", "kind" : "final def"}, {"label" : "==", "tail" : "(arg0: Any): Boolean", "member" : "scala.AnyRef.==", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#==(x$1:Any):Boolean", "kind" : "final def"}, {"label" : "ne", "tail" : "(arg0: AnyRef): Boolean", "member" : "scala.AnyRef.ne", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#ne(x$1:AnyRef):Boolean", "kind" : "final def"}, {"label" : "eq", "tail" : "(arg0: AnyRef): Boolean", "member" : "scala.AnyRef.eq", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#eq(x$1:AnyRef):Boolean", "kind" : "final def"}, {"label" : "finalize", "tail" : "(): Unit", "member" : "scala.AnyRef.finalize", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#finalize():Unit", "kind" : "def"}, {"label" : "wait", "tail" : "(): Unit", "member" : "scala.AnyRef.wait", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#wait():Unit", "kind" : "final def"}, {"label" : "wait", "tail" : "(arg0: Long, arg1: Int): Unit", "member" : "scala.AnyRef.wait", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#wait(x$1:Long,x$2:Int):Unit", "kind" : "final def"}, {"label" : "wait", "tail" : "(arg0: Long): Unit", "member" : "scala.AnyRef.wait", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#wait(x$1:Long):Unit", "kind" : "final def"}, {"label" : "notifyAll", "tail" : "(): Unit", "member" : "scala.AnyRef.notifyAll", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#notifyAll():Unit", "kind" : "final def"}, {"label" : "notify", "tail" : "(): Unit", "member" : "scala.AnyRef.notify", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#notify():Unit", "kind" : "final def"}, {"label" : "toString", "tail" : "(): String", "member" : "scala.AnyRef.toString", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#toString():String", "kind" : "def"}, {"label" : "clone", "tail" : "(): AnyRef", "member" : "scala.AnyRef.clone", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#clone():Object", "kind" : "def"}, {"label" : "equals", "tail" : "(arg0: Any): Boolean", "member" : "scala.AnyRef.equals", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#equals(x$1:Any):Boolean", "kind" : "def"}, {"label" : "hashCode", "tail" : "(): Int", "member" : "scala.AnyRef.hashCode", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#hashCode():Int", "kind" : "def"}, {"label" : "getClass", "tail" : "(): Class[_]", "member" : "scala.AnyRef.getClass", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#getClass():Class[_]", "kind" : "final def"}, {"label" : "asInstanceOf", "tail" : "(): T0", "member" : "scala.Any.asInstanceOf", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#asInstanceOf[T0]:T0", "kind" : "final def"}, {"label" : "isInstanceOf", "tail" : "(): Boolean", "member" : "scala.Any.isInstanceOf", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaProducerCache$.html#isInstanceOf[T0]:Boolean", "kind" : "final def"}], "kind" : "object"}, {"name" : "com.github.benfradet.spark.kafka.writer.KafkaWriter", "shortDescription" : "Class used to write DStreams, RDDs and Datasets to Kafka", "members_class" : [{"member" : "com.github.benfradet.spark.kafka.writer.KafkaWriter#<init>", "error" : "unsupported entity"}, {"label" : "synchronized", "tail" : "(arg0: ⇒ T0): T0", "member" : "scala.AnyRef.synchronized", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#synchronized[T0](x$1:=>T0):T0", "kind" : "final def"}, {"label" : "##", "tail" : "(): Int", "member" : "scala.AnyRef.##", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html###():Int", "kind" : "final def"}, {"label" : "!=", "tail" : "(arg0: Any): Boolean", "member" : "scala.AnyRef.!=", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#!=(x$1:Any):Boolean", "kind" : "final def"}, {"label" : "==", "tail" : "(arg0: Any): Boolean", "member" : "scala.AnyRef.==", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#==(x$1:Any):Boolean", "kind" : "final def"}, {"label" : "ne", "tail" : "(arg0: AnyRef): Boolean", "member" : "scala.AnyRef.ne", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#ne(x$1:AnyRef):Boolean", "kind" : "final def"}, {"label" : "eq", "tail" : "(arg0: AnyRef): Boolean", "member" : "scala.AnyRef.eq", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#eq(x$1:AnyRef):Boolean", "kind" : "final def"}, {"label" : "finalize", "tail" : "(): Unit", "member" : "scala.AnyRef.finalize", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#finalize():Unit", "kind" : "def"}, {"label" : "wait", "tail" : "(): Unit", "member" : "scala.AnyRef.wait", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#wait():Unit", "kind" : "final def"}, {"label" : "wait", "tail" : "(arg0: Long, arg1: Int): Unit", "member" : "scala.AnyRef.wait", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#wait(x$1:Long,x$2:Int):Unit", "kind" : "final def"}, {"label" : "wait", "tail" : "(arg0: Long): Unit", "member" : "scala.AnyRef.wait", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#wait(x$1:Long):Unit", "kind" : "final def"}, {"label" : "notifyAll", "tail" : "(): Unit", "member" : "scala.AnyRef.notifyAll", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#notifyAll():Unit", "kind" : "final def"}, {"label" : "notify", "tail" : "(): Unit", "member" : "scala.AnyRef.notify", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#notify():Unit", "kind" : "final def"}, {"label" : "toString", "tail" : "(): String", "member" : "scala.AnyRef.toString", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#toString():String", "kind" : "def"}, {"label" : "clone", "tail" : "(): AnyRef", "member" : "scala.AnyRef.clone", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#clone():Object", "kind" : "def"}, {"label" : "equals", "tail" : "(arg0: Any): Boolean", "member" : "scala.AnyRef.equals", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#equals(x$1:Any):Boolean", "kind" : "def"}, {"label" : "hashCode", "tail" : "(): Int", "member" : "scala.AnyRef.hashCode", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#hashCode():Int", "kind" : "def"}, {"label" : "getClass", "tail" : "(): Class[_]", "member" : "scala.AnyRef.getClass", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#getClass():Class[_]", "kind" : "final def"}, {"label" : "asInstanceOf", "tail" : "(): T0", "member" : "scala.Any.asInstanceOf", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#asInstanceOf[T0]:T0", "kind" : "final def"}, {"label" : "isInstanceOf", "tail" : "(): Boolean", "member" : "scala.Any.isInstanceOf", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#isInstanceOf[T0]:Boolean", "kind" : "final def"}, {"label" : "writeToKafka", "tail" : "(producerConfig: Map[String, AnyRef], transformFunc: (T) ⇒ ProducerRecord[K, V], callback: Option[Callback]): Unit", "member" : "com.github.benfradet.spark.kafka.writer.KafkaWriter.writeToKafka", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html#writeToKafka[K,V](producerConfig:Map[String,Object],transformFunc:T=>org.apache.kafka.clients.producer.ProducerRecord[K,V],callback:Option[org.apache.kafka.clients.producer.Callback]):Unit", "kind" : "abstract def"}], "class" : "com\/github\/benfradet\/spark\/kafka\/writer\/KafkaWriter.html", "kind" : "class"}, {"name" : "com.github.benfradet.spark.kafka.writer.RDDKafkaWriter", "shortDescription" : "Class used for writing RDDs to Kafka", "members_class" : [{"label" : "writeToKafka", "tail" : "(producerConfig: Map[String, AnyRef], transformFunc: (T) ⇒ ProducerRecord[K, V], callback: Option[Callback]): Unit", "member" : "com.github.benfradet.spark.kafka.writer.RDDKafkaWriter.writeToKafka", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#writeToKafka[K,V](producerConfig:Map[String,Object],transformFunc:T=>org.apache.kafka.clients.producer.ProducerRecord[K,V],callback:Option[org.apache.kafka.clients.producer.Callback]):Unit", "kind" : "def"}, {"member" : "com.github.benfradet.spark.kafka.writer.RDDKafkaWriter#<init>", "error" : "unsupported entity"}, {"label" : "synchronized", "tail" : "(arg0: ⇒ T0): T0", "member" : "scala.AnyRef.synchronized", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#synchronized[T0](x$1:=>T0):T0", "kind" : "final def"}, {"label" : "##", "tail" : "(): Int", "member" : "scala.AnyRef.##", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html###():Int", "kind" : "final def"}, {"label" : "!=", "tail" : "(arg0: Any): Boolean", "member" : "scala.AnyRef.!=", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#!=(x$1:Any):Boolean", "kind" : "final def"}, {"label" : "==", "tail" : "(arg0: Any): Boolean", "member" : "scala.AnyRef.==", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#==(x$1:Any):Boolean", "kind" : "final def"}, {"label" : "ne", "tail" : "(arg0: AnyRef): Boolean", "member" : "scala.AnyRef.ne", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#ne(x$1:AnyRef):Boolean", "kind" : "final def"}, {"label" : "eq", "tail" : "(arg0: AnyRef): Boolean", "member" : "scala.AnyRef.eq", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#eq(x$1:AnyRef):Boolean", "kind" : "final def"}, {"label" : "finalize", "tail" : "(): Unit", "member" : "scala.AnyRef.finalize", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#finalize():Unit", "kind" : "def"}, {"label" : "wait", "tail" : "(): Unit", "member" : "scala.AnyRef.wait", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#wait():Unit", "kind" : "final def"}, {"label" : "wait", "tail" : "(arg0: Long, arg1: Int): Unit", "member" : "scala.AnyRef.wait", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#wait(x$1:Long,x$2:Int):Unit", "kind" : "final def"}, {"label" : "wait", "tail" : "(arg0: Long): Unit", "member" : "scala.AnyRef.wait", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#wait(x$1:Long):Unit", "kind" : "final def"}, {"label" : "notifyAll", "tail" : "(): Unit", "member" : "scala.AnyRef.notifyAll", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#notifyAll():Unit", "kind" : "final def"}, {"label" : "notify", "tail" : "(): Unit", "member" : "scala.AnyRef.notify", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#notify():Unit", "kind" : "final def"}, {"label" : "toString", "tail" : "(): String", "member" : "scala.AnyRef.toString", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#toString():String", "kind" : "def"}, {"label" : "clone", "tail" : "(): AnyRef", "member" : "scala.AnyRef.clone", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#clone():Object", "kind" : "def"}, {"label" : "equals", "tail" : "(arg0: Any): Boolean", "member" : "scala.AnyRef.equals", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#equals(x$1:Any):Boolean", "kind" : "def"}, {"label" : "hashCode", "tail" : "(): Int", "member" : "scala.AnyRef.hashCode", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#hashCode():Int", "kind" : "def"}, {"label" : "getClass", "tail" : "(): Class[_]", "member" : "scala.AnyRef.getClass", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#getClass():Class[_]", "kind" : "final def"}, {"label" : "asInstanceOf", "tail" : "(): T0", "member" : "scala.Any.asInstanceOf", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#asInstanceOf[T0]:T0", "kind" : "final def"}, {"label" : "isInstanceOf", "tail" : "(): Boolean", "member" : "scala.Any.isInstanceOf", "link" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html#isInstanceOf[T0]:Boolean", "kind" : "final def"}], "class" : "com\/github\/benfradet\/spark\/kafka\/writer\/RDDKafkaWriter.html", "kind" : "class"}], "com.github" : [], "com.github.benfradet" : []};