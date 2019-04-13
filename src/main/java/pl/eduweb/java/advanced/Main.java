package pl.eduweb.java.advanced;


import java.io.*;

public class Main {

    public static void main(String[] args) {
        int read;
        try (FileInputStream fileInputStream = new FileInputStream("files/lorem.txt");
             FileOutputStream fileOutputStream = new FileOutputStream("files/output.txt", true);
             BufferedInputStream inputStream = new BufferedInputStream(fileInputStream);
             BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream)) {
            while ((read = inputStream.read()) != -1) {
                outputStream.write(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
