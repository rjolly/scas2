name := "scas"

version := "2.0"

scalaVersion := "2.10.0-RC1"

scalacOptions ++= Seq("-language:higherKinds", "-language:implicitConversions", "-language:postfixOps")

resolvers += Resolver.sonatypeRepo("snapshots")

mainClass in (Compile, run) := Some("scas.application.PolyPower")
