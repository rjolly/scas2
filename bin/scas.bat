@echo off
set SCAS_HOME=%~dp0..
java -Dscala.usejavacp=true -classpath "%JAVA_HOME%\lib\tools.jar";"%SCALA_HOME%\lib\scala-compiler.jar";"%SCALA_HOME%\lib\scala-reflect.jar";"%SCALA_HOME%\lib\scala-library.jar";"%SCALA_HOME%\lib\scala-xml_2.11-1.0.5.jar";"%SCALA_HOME%\lib\scala-parser-combinators_2.11-1.0.4.jar";"%SCAS_HOME%\macros\target\scala-2.11\macros_2.11-2.1.1.jar";"%SCAS_HOME%\target\scala-2.11\scas_2.11-2.1.1.jar" com.sun.tools.script.shell.Main -l scala %*
