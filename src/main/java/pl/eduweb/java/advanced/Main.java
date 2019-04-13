package pl.eduweb.java.advanced;


import java.io.*;

public class Main {
    public static void main(String[] args) {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader("files/lorem.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("files/output.txt"))) {
            while ((line = reader.readLine()) != null) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
