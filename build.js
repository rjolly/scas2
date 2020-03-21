mkdir("target");
mkdir("target/scala-2.11");
mkdir("target/scala-2.11/classes");
mkdir("target/scala-2.11/sources");
mkdir("target/scala-2.11/sources/jscl");
mkdir("target/scala-2.11/javadoc");

scalac("src/main/scala", "target/scala-2.11/classes", ["-language:experimental.macros"]);
copy("src/main/resources", "target/scala-2.11/classes");
copy("src/main/scala", "target/scala-2.11/sources");
copy("src/main/resources/jscl", "target/scala-2.11/sources/jscl");
scaladoc("src/main/scala", "target/scala-2.11/javadoc", ["-language:experimental.macros"]);

var name = "scas_2.11";
jar("target/scala-2.11/" + name + ".jar", "target/scala-2.11/classes");
jarlister("target/scala-2.11/" + name + ".jar")
jar("target/scala-2.11/" + name + "-source.jar", "target/scala-2.11/sources");
jar("target/scala-2.11/" + name + "-javadoc.jar", "target/scala-2.11/javadoc");
cp("pom.xml", "target/scala-2.11/" + name + ".pom")

publish("target/scala-2.11")
