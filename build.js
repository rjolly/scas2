mkdir("target");
mkdir("target/scala-2.11.0-M6");
mkdir("target/scala-2.11.0-M6/classes");
mkdir("target/scala-2.11.0-M6/sources");
mkdir("target/scala-2.11.0-M6/sources/jscl");
mkdir("target/scala-2.11.0-M6/javadoc");

scalac("src/main/scala", "target/scala-2.11.0-M6/classes", ["-language:experimental.macros"]);
copy("src/main/resources", "target/scala-2.11.0-M6/classes");
copy("src/main/scala", "target/scala-2.11.0-M6/sources");
copy("src/main/resources/jscl", "target/scala-2.11.0-M6/sources/jscl");
scaladoc("src/main/scala", "target/scala-2.11.0-M6/javadoc", ["-language:experimental.macros"]);

var name = "scas_2.11.0-M6";
jar("target/scala-2.11.0-M6/" + name + ".jar", "target/scala-2.11.0-M6/classes");
jar("target/scala-2.11.0-M6/" + name + "-source.jar", "target/scala-2.11.0-M6/sources");
jar("target/scala-2.11.0-M6/" + name + "-javadoc.jar", "target/scala-2.11.0-M6/javadoc");
cp("pom.xml", "target/scala-2.11.0-M6/" + name + ".pom")

publish("target/scala-2.11.0-M6")
