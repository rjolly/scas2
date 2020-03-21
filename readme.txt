
Software needed:

- scala 2.11 ( http://www.scala-lang.org/ )
- sbt 0.13.18 ( http://github.com/harrah/xsbt/ )
- jarlister ( http://github.com/rjolly/jarlister/ )
- meditor ( http://github.com/rjolly/meditor/ )

  edit $SCALA_HOME/lib/scala-compiler.jar!/META_INF/MANIFEST.MF to add Class-Path: scala-library.jar scala-reflect.jar
  jarlister $SCALA_HOME/lib/scala-library.jar
  jarlister $SCALA_HOME/lib/scala-xml_2.11.0-M8-1.0.0-RC7.jar
  jarlister $SCALA_HOME/lib/scala-parser-combinators_2.11.0-M8-1.0.0-RC5.jar
  jarlister ../meditor/dist/meditor.jar
  jarlister ../meditor/txt2xhtml/dist/txt2xhtml.jar

To build scas:
  sbt package macros/package
  jarlister target/scala-2.11.0-M8/scas_2.11.0-M8-2.1.1.jar


To run the test suite, add the bin directory to your path, give bin/scas execution privilege (unix), then:
  cd ../meditor/docs
  scas [examples/index.txt]

