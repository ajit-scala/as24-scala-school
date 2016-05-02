organization := "com.autoscout24"
name := "play-filters-actions"
version := "1.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.11.6"
scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfatal-warnings")

libraryDependencies ++= Seq(
  ws,
  specs2 % Test,
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"
)

lazy val playFiltersActions: Project = project
  .in(file("."))
  .enablePlugins(PlayScala)
  .settings(
      cleanFiles <+= baseDirectory { base => base / "logs" }
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

routesGenerator := InjectedRoutesGenerator
// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
