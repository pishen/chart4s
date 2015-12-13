lazy val root = (project in file(".")).settings(
  name := "chart4s",
  version := "0.1.0",
  scalaVersion := "2.11.7",
  libraryDependencies ++= Seq(
    "com.typesafe.play" %% "play" % "2.4.4",
    "com.typesafe.play" %% "play-netty-server" % "2.4.4"
  )
)
