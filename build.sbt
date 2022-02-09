name := """play-java-hello-world-tutorial"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.6"

libraryDependencies += guice

libraryDependencies ++=Seq("com.typesafe.play" %% "play" % "2.8.13")

libraryDependencies += "org.apache.commons" % "commons-csv" % "1.9.0"

