lazy val buildSettings = Seq(
  organization := "com.github.benfradet",
  version := "0.2.0-SNAPSHOT",
  scalaVersion := "2.11.8",
  crossScalaVersions := Seq("2.10.6", "2.11.8")
)

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

lazy val baseSettings = Seq(
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

lazy val publishSettings = Seq(
  publishMavenStyle := true,
  publishArtifact := true,
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  },
  publishArtifact in Test := false,
  pomIncludeRepository := { _ => false },
  licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
  homepage := Some(url("https://github.com/BenFradet/spark-kafka-writer")),
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/BenFradet/spark-kafka-writer"),
      "scm:git:git@github.com:BenFradet/spark-kafka-writer.git"
    )
  ),
  pomExtra :=
    <developers>
      <developer>
        <id>BenFradet</id>
        <name>Ben Fradet</name>
        <url>https://benfradet.github.io/</url>
      </developer>
    </developers>
)

lazy val allSettings = baseSettings ++ buildSettings ++ publishSettings

lazy val root = (project in file("."))
  .settings(name := "spark-kafka-writer")
  .settings(allSettings)

parallelExecution in Test := false
