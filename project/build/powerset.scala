import sbt._

class PowersetProject(info: ProjectInfo) extends DefaultProject(info) {

  val sc = "org.scala-tools.testing" % "scalacheck_2.8.1" % "1.8" % "test"
  val mavenLocal = "Local Maven Repository" at "file://"+Path.userHome+"/.m2/repository"

}

// vim: set ts=4 sw=4 et: