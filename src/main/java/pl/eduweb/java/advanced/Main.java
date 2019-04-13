package pl.eduweb.java.advanced;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000);
        while (true) {
            LinkedList<Object> objects = new LinkedList<>();
            for (int i = 0; i < 100000000; i++) {
                objects.add(new Object());
            }
        }
    }

}
