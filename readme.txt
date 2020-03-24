
Software needed:

- scala 2.11 ( http://www.scala-lang.org/ )
- sbt 0.13.18 ( http://github.com/harrah/xsbt/ )
- meditor ( http://github.com/rjolly/meditor/ )

To build scas:
  sbt package macros/package


To run the test suite, add the bin directory to your path, give bin/scas execution privilege (unix), then:
  cd ../meditor/docs
  scas [examples/index.txt]

