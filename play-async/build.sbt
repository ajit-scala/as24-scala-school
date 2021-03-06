organization := "com.autoscout24"
name := "play-async"
version := "1.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.11.6"
scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfatal-warnings")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.scalatestplus" %% "play" % "1.4.0-M3" % "test",
  "org.scala-lang.modules" % "scala-async_2.11" % "0.9.6-RC2",
  ws,
  specs2 % Test
)

lazy val playAsync: Project = project
  .in(file("."))
  .enablePlugins(PlayScala)
  .settings(
      cleanFiles <+= baseDirectory { base => base / "logs" }
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

routesGenerator := InjectedRoutesGenerator
// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
