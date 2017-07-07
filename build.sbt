import com.typesafe.sbt.SbtGit.GitKeys._

lazy val buildSettings = Seq(
  organization := "com.github.benfradet",
  version := "0.4.0-SNAPSHOT",
  scalaVersion := "2.11.11",
  crossScalaVersions := Seq("2.10.6", "2.11.11")
)

lazy val sparkVersion = "2.1.1"
lazy val kafka010Version = "0.10.2.1"

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
    "org.apache.kafka" %% "kafka" % kafka010Version
  ) ++ Seq(
    "org.apache.spark" %% "spark-core",
    "org.apache.spark" %% "spark-streaming"
  ).map(_ % sparkVersion % "provided") ++ Seq(
    "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion,
    "org.scalatest" %% "scalatest" % "2.2.6"
  ).map(_ % "test"),
  scalacOptions ++= compilerOptions,
  parallelExecution in Test := false
)

lazy val publishSettings = Seq(
  publishMavenStyle := true,
  publishArtifact := true,
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
    else Some("releases"  at nexus + "service/local/staging/deploy/maven2")
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

lazy val docSettings = Seq(
  siteSubdirName in ScalaUnidoc := "",
  addMappingsToSiteDir(mappings in (ScalaUnidoc, packageDoc), siteSubdirName in ScalaUnidoc),
  gitRemoteRepo := "https://github.com/BenFradet/spark-kafka-writer.git"
)

lazy val sparkKafkaWriter = (project in file("."))
  .settings(moduleName := "spark-kafka-0-10-writer")
  .settings(allSettings)
  .enablePlugins(ScalaUnidocPlugin, GhpagesPlugin)
  .settings(docSettings)
