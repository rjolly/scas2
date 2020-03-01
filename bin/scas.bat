@echo off
set SCAS_HOME=%~dp0..
jrunscript -classpath "%SCALA_HOME%\lib\scala-compiler.jar";"%SCALA_HOME%\lib\scala-xml.jar";"%SCALA_HOME%\lib\scala-parser-combinators.jar";"%SCAS_HOME%\lib\txt2xhtml.jar";"%SCAS_HOME%\macros\target\scala-2.11.0-M6\macros_2.11.0-M6-2.1.jar";"%SCAS_HOME%\target\scala-2.11.0-M6\scas_2.11.0-M6-2.1.jar" -l scala %*
