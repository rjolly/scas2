mkdir("target");
mkdir("target/scala-2.11");
mkdir("target/scala-2.11/classes");
mkdir("target/scala-2.11/sources");
mkdir("target/scala-2.11/javadoc");

scalac("src/main/scala", "target/scala-2.11/classes");
copy("src/main/scala", "target/scala-2.11/sources");
scaladoc("src/main/scala", "target/scala-2.11/javadoc");

var name = "macros_2.11";
jar("target/scala-2.11/" + name + ".jar", "target/scala-2.11/classes");
jar("target/scala-2.11/" + name + "-source.jar", "target/scala-2.11/sources");
jar("target/scala-2.11/" + name + "-javadoc.jar", "target/scala-2.11/javadoc");
cp("pom.xml", "target/scala-2.11/" + name + ".pom")

publish("target/scala-2.11")
