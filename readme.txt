
Software needed:

- scala 2.11 ( http://www.scala-lang.org/ )
- sbt 0.13.18 ( http://github.com/harrah/xsbt/ )
- jarlister ( http://github.com/rjolly/jarlister/ )

  jarlister $SCALA_HOME/lib/scala-library.jar
  jarlister $SCALA_HOME/lib/scala-xml.jar
  jarlister $SCALA_HOME/lib/scala-parser-combinators.jar

To build scas:
  sbt package macros/package
  jarlister target/scala-2.11.0-M6/scas_2.11.0-M6-2.1.jar


To run the test suite, add the bin directory to your path, give bin/scas execution privilege (unix), then:
  cd ../meditor/docs
  scas [examples/index.txt]

