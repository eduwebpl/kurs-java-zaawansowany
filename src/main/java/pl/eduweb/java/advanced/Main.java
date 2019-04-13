package pl.eduweb.java.advanced;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 10000000; i++) {
            executorService.execute(main::sendMail);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        }
        System.out.println(counter);
    }

    private void sendMail() {
        counter.incrementAndGet();
    }
}
