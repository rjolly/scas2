@echo off
set SCAS_HOME=%~dp0..
set MEDITOR_HOME=%SCAS_HOME%\..\meditor\dist
set TXT2XHTML_HOME=%MEDITOR_HOME%\..\txt2xhtml\dist
jrunscript -classpath "%SCALA_HOME%\lib\scala-compiler.jar";"%SCALA_HOME%\lib\scala-xml.jar";"%SCALA_HOME%\lib\scala-parser-combinators.jar";"%MEDITOR_HOME%\meditor.jar";"%TXT2XHTML_HOME%\txt2xhtml.jar";"%SCAS_HOME%\macros\target\scala-2.11.0-M6\macros_2.11.0-M6-2.1.1.jar";"%SCAS_HOME%\target\scala-2.11.0-M6\scas_2.11.0-M6-2.1.1.jar" -l scala %*
