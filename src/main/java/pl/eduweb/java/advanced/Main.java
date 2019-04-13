package pl.eduweb.java.advanced;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {

    public static void main(String[] args) throws IOException {
        Files.copy(
                Paths.get("out"),
                Paths.get("output"),
                StandardCopyOption.REPLACE_EXISTING
        );
    }

}
