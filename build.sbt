lazy val sparkVersion = "1.6.2"
lazy val kafkaVersion = "0.8.2.2"

lazy val compilerOptions = Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Xfuture",
  "-Xlint"
)

lazy val root = (project in file("."))
  .settings(
    organization := "com.github.benfradet",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.11.8",
    name := "spark-kafka-writer",
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core",
      "org.apache.spark" %% "spark-streaming",
      "org.apache.spark" %% "spark-streaming-kafka"
    ).map(_ % sparkVersion) ++ Seq(
      "org.apache.kafka" %% "kafka" % kafkaVersion,
      "org.scalatest" %% "scalatest" % "2.2.6" % "test"
    ),
    scalacOptions ++= compilerOptions
  )

parallelExecution in Test := false
