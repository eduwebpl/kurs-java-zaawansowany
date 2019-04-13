package pl.eduweb.java.advanced;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String line;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("files/lorem.txt"), Charset.defaultCharset());
             BufferedWriter writer = Files.newBufferedWriter(Paths.get("files/output-utf16.txt"), StandardCharsets.UTF_16)) {
            while ((line = reader.readLine()) != null) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
