var manager = new javax.script.ScriptEngineManager();
var n = 0;
var m = 0;
var dir = pathToFile("examples");
var files = dir.listFiles();
for (var f in files) {
    var file = files[f];
    var name = file.getName();
    println(name);
    var engine = manager.getEngineByName("scala");
    var reader = new java.io.FileReader(file);
    try {
        engine.eval(reader);
        n++;
    } catch (e) {
        e.printStackTrace();
        println(name + " failure");
        m++;
    } finally {
        reader.close();
    }
}
println("success : " + n + ", failure : " + m);
