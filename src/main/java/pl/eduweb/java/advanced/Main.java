package pl.eduweb.java.advanced;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(4, task -> {
            Thread thread = new Thread();
            thread.setDaemon(true);
            return thread;
        });

        MailSender mailSender = new MailSender();
        executorService.execute(() -> mailSender.sendMail("mail1"));

        System.out.println(System.currentTimeMillis() - start);
        System.out.println("Response to client");

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("End of main");
    }

}
