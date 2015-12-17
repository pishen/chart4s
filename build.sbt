lazy val root = (project in file(".")).settings(
  name := "chart4s",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.7",
  organization := "net.pishen",
  libraryDependencies ++= Seq(
    "com.typesafe.play" %% "play" % "2.4.4",
    "com.typesafe.play" %% "play-netty-server" % "2.4.4"
  )
)
