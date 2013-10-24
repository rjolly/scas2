
Software needed:

- scala 2.11 ( http://www.scala-lang.org/ )
- sbt 0.12.1 ( http://github.com/harrah/xsbt/ )
- jarlister ( http://github.com/rjolly/jarlister/ )


To build scas:
  sbt -Dscala_home=$SCALA_HOME package macros/package
  jarlister target/scala-2.11/scas_2.11-2.1.jar


To run scas, add the bin directory to your path, give bin/scas execution privilege (unix), then:
  scas [example.txt]

