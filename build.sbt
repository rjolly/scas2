val buildSettings = Defaults.coreDefaultSettings ++ Seq(
  organization := "scas",
  version := "2.1",
  scalaVersion := "2.11.0-M6",
  scalacOptions ++= Seq(
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-language:experimental.macros"
  )
)

lazy val macros: Project = Project(
  "macros",
  file("macros"),
  settings = buildSettings ++ Seq(
    libraryDependencies += (scalaVersion)("org.scala-lang" % "scala-reflect" % _).value)
)

lazy val core: Project = Project(
  "scas",
  file("."),
  settings = buildSettings ++ Seq(
    libraryDependencies += (scalaVersion)(s => "org.scala-lang.modules" %% "scala-xml" % "1.0.0-RC6").value) ++ Seq(
    libraryDependencies += (scalaVersion)(s => "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.0-RC4").value)

) dependsOn(macros)
