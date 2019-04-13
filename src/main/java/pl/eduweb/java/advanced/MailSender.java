package pl.eduweb.java.advanced;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

class MailSender {
    private ExecutorService executorService;

    public MailSender(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public CompletableFuture<?> sendMail(String mail) {
        return CompletableFuture.runAsync(() -> {
            someLongBlockingInternalOperation();
            System.out.println(mail + " sent");
        }, executorService);
    }

    private void someLongBlockingInternalOperation() {
        try {
            Thread.sleep(2000); // don't do it at home
        } catch (InterruptedException e) {
            System.out.println("Ignore interrupted");
        }
    }
}
