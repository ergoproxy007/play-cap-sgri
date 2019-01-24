name := """play-cap-sgri"""
organization := "com.ceiba.capacitacion"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.8"

libraryDependencies += guice

// https://mvnrepository.com/artifact/org.mockito/mockito-core
//libraryDependencies += "org.mockito" % "mockito-core" % "2.23.4" % Test
