package pl.eduweb.java.advanced;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        int linesNumber = countLines("src/pl/eduweb/java/advanced/Main.java");
        System.out.println(linesNumber);
    }

    private static int countLines(String filePath) {
        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                counter++;
            }
            return counter;
        } catch (IOException e) {
            throw new RuntimeException("Error during counting lines", e);
        }
    }

}