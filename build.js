mkdir("target");
mkdir("target/scala-2.11.0-M8");
mkdir("target/scala-2.11.0-M8/classes");
mkdir("target/scala-2.11.0-M8/sources");
mkdir("target/scala-2.11.0-M8/sources/jscl");
mkdir("target/scala-2.11.0-M8/javadoc");

scalac("src/main/scala", "target/scala-2.11.0-M8/classes", ["-language:experimental.macros"]);
copy("src/main/resources", "target/scala-2.11.0-M8/classes");
copy("src/main/scala", "target/scala-2.11.0-M8/sources");
copy("src/main/resources/jscl", "target/scala-2.11.0-M8/sources/jscl");
scaladoc("src/main/scala", "target/scala-2.11.0-M8/javadoc", ["-language:experimental.macros"]);

var name = "scas_2.11.0-M8";
jar("target/scala-2.11.0-M8/" + name + ".jar", "target/scala-2.11.0-M8/classes");
jarlister("target/scala-2.11.0-M8/" + name + ".jar")
jar("target/scala-2.11.0-M8/" + name + "-source.jar", "target/scala-2.11.0-M8/sources");
jar("target/scala-2.11.0-M8/" + name + "-javadoc.jar", "target/scala-2.11.0-M8/javadoc");
cp("pom.xml", "target/scala-2.11.0-M8/" + name + ".pom")

publish("target/scala-2.11.0-M8")
