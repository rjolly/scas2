@echo off
set SCAS_HOME=%~dp0..
java -Dscala.usejavacp=true -classpath "%JAVA_HOME%\lib\tools.jar";"%SCALA_HOME%\lib\scala-compiler.jar";"%SCALA_HOME%\lib\scala-reflect.jar";"%SCALA_HOME%\lib\scala-library.jar";"%SCALA_HOME%\lib\scala-xml.jar";"%SCALA_HOME%\lib\scala-parser-combinators.jar";"%SCAS_HOME%\macros\target\scala-2.11.0-M6\macros_2.11.0-M6-2.1.2.jar";"%SCAS_HOME%\target\scala-2.11.0-M6\scas_2.11.0-M6-2.1.2.jar" com.sun.tools.script.shell.Main -l scala %*
