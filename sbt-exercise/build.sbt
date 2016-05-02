
scalaVersion in ThisBuild := "2.11.6"
scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfatal-warnings")

lazy val projectSettings = Seq(
  organization := "com.autoscout24",
  name := "tatsu-service",
  version := Option(System.getenv("GO_PIPELINE_LABEL")).getOrElse("1.0-SNAPSHOT")
)

libraryDependencies ++= Seq(
  "net.codingwell" %% "scala-guice" % "4.0.0-beta4",
  "org.scalatestplus" %% "play" % "1.2.0" % "test",
  ws
)

/* Exercise 1-a: Create a new SettingKey and SettingValue */


/* Exercise 1-b: Create a new TaskKey and TaskValue */


/* Exercise 2-a: Create a new Test configuration that runs only the Unit tests */

lazy val UnitTest = config("unit") extend (Test)

/* Exercise 2-b: Create filters that detect Unit tests and Integration test files */

def itFilter(name: String): Boolean = ???
def unitFilter(name: String): Boolean = name.endsWith("UnitTest")

/* Exercise 2-c: Add configuration to project and apply filters to the testOptions */

lazy val tatsuService: Project = project
  .in(file("."))
  .settings(projectSettings: _*)
  .configs(UnitTest)
  .enablePlugins(PlayScala)
  .settings(inConfig(UnitTest)(Defaults.testTasks): _*)
  .settings(
    cleanFiles <+= baseDirectory { base => base / "logs" },
    testOptions in UnitTest := Seq(Tests.Filter(unitFilter))
  )

lazy val myTask=taskKey[Unit]("My task")
myTask := println("This is my task")

lazy val myKey=settingKey[String]("This is my key")
myKey  := "my no scope value"
myKey in Test := "my keys test value"
myKey in Runtime := "my runtime value"

lazy val myTask2=taskKey[Unit]("My task2")
myTask2 := println(myKey.value)
myTask2 in Test := println((myKey in Test).value)
myTask2 in Runtime := println((myKey in Runtime).value)


