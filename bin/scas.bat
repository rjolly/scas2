@echo off
set SCAS_HOME=%~dp0..
set MEDITOR_HOME=%SCAS_HOME%\..\meditor\dist
set TXT2XHTML_HOME=%MEDITOR_HOME%\..\txt2xhtml\dist
jrunscript -classpath "%SCALA_HOME%\lib\scala-compiler.jar";"%SCALA_HOME%\lib\scala-xml_2.11-1.0.5.jar";"%SCALA_HOME%\lib\scala-parser-combinators_2.11-1.0.4.jar";"%MEDITOR_HOME%\meditor.jar";"%TXT2XHTML_HOME%\txt2xhtml.jar";"%SCAS_HOME%\macros\target\scala-2.11\macros_2.11-2.1.1.jar";"%SCAS_HOME%\target\scala-2.11\scas_2.11-2.1.1.jar" -l scala %*
