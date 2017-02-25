name := "twitter-sentiment-demo"

version := "1.0"

scalaVersion := "2.11.8"

logLevel := Level.Info

lazy val akkaVersion = "2.4.14"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.slick" % "slick_2.11" % "3.1.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.1.0",
  "mysql" % "mysql-connector-java" % "5.1.40",
  "edu.stanford.nlp" % "stanford-corenlp" % "3.7.0",
  "edu.stanford.nlp" % "stanford-corenlp" % "3.7.0" classifier "models",
  "org.twitter4j" % "twitter4j-core" % "3.0.6",
  "org.twitter4j" % "twitter4j-stream" % "3.0.6"
)