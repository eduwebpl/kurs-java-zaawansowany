package pl.eduweb.java.advanced;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 10000000; i++) {
            executorService.execute(Main::sendMail);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        }
        System.out.println(counter);
    }

    private static void sendMail() {
        counter++;
    }
}
