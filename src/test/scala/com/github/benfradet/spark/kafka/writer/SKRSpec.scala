package com.github.benfradet.spark.kafka.writer

import org.scalatest.concurrent.Eventually
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfter, Matchers, WordSpec}

trait SKRSpec
  extends WordSpec
  with Matchers
  with BeforeAndAfter
  with BeforeAndAfterAll
  with Eventually
