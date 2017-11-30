import java.io.File

scalaVersion := "2.12.2"


val NAME: String = "CT_ASSMNT"
val VERSION: String = "0.0.1"
val PORT: Int = 9000

name := NAME
version := VERSION

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)
PlayKeys.devSettings := Seq("play.server.http.port" -> s"$PORT")
PlayKeys.playDefaultPort := PORT

libraryDependencies += play.sbt.PlayImport.cacheApi

libraryDependencies += ws
libraryDependencies += guice
libraryDependencies += "com.github.karelcemus" %% "play-redis" % "1.5.1"


