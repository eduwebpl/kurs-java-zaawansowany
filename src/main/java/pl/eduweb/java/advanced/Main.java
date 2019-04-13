package pl.eduweb.java.advanced;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        UserService userService = new UserService(executorService);
        MailSender mailSender = new MailSender(executorService);

        Future<?> sendMailF = sendMailToUserWithId(1L, userService, mailSender);
        sendMailF.get(10, TimeUnit.SECONDS);

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

    private static Future<?> sendMailToUserWithId(Long userId,
                                                  UserService userService,
                                                  MailSender mailSender) {
        return userService
                .getUserName(userId)
                .thenApply(userName -> userName + "@example.com")
                .thenCompose(userName -> mailSender.sendMail("mail to " + userName));
    }

}
