package pl.eduweb.java.advanced;


import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, TimeoutException, ExecutionException {
        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        UserService userService = new UserService(executorService);

        Future<String> mailF = userService.getUserName(1L);
        String result = mailF.get(10, TimeUnit.SECONDS);
        System.out.println(result);

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

}
