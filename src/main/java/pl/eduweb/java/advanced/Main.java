package pl.eduweb.java.advanced;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        Path tempDir = Files.createTempDirectory("java-test");
        System.out.println(Files.exists(tempDir));
        System.out.println(Files.isDirectory(tempDir));
    }
}