package pl.eduweb.java.advanced;


import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        UserService userService = new UserService(executorService);
        MailSender mailSender = new MailSender(executorService);

        Future<?> sendMailF = sendMailToUserWithId(1L, userService, mailSender, executorService);
        sendMailF.get(10, TimeUnit.SECONDS);

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

    private static Future<?> sendMailToUserWithId(Long userId,
                                                  UserService userService,
                                                  MailSender mailSender,
                                                  ExecutorService executorService) {
        return executorService.submit(() -> {
            try {
                Future<String> userNameF = userService.getUserName(userId);
                String userName = userNameF.get(10, TimeUnit.SECONDS);
                Future<?> mailSentF = mailSender.sendMail("mail to " + userName);
                mailSentF.get(10, TimeUnit.SECONDS);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}
