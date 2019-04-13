package pl.eduweb.java.advanced;


import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        var path = Paths.get("src/main/java/pl/eduweb/java/advanced/Main.java");

        System.out.println(path.toString());

        System.out.println(path.getFileName().toString());

        System.out.println(path.toAbsolutePath().toString());

        System.out.println(path.endsWith(Paths.get("Main.java")));

        Path parentDirPath = path.getParent();

        System.out.println(parentDirPath.toString());

        System.out.println(parentDirPath.resolve("NotExisting.java"));

        System.out.println(Paths.get("src/main/java/pl/eduweb/../eduweb/java/advanced/Main.java").normalize().toString());
    }
}