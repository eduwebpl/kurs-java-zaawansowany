package pl.eduweb.java.advanced;


import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try (FileReader reader = new FileReader("files/lorem.txt")) {
            int read;
            while ((read = reader.read()) != -1) {
                System.out.print((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
