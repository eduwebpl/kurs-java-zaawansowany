package pl.eduweb.java.advanced;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

class MailSender {
    private ExecutorService executorService;

    public MailSender(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Future<?> sendMail(String mail) {
        return executorService.submit(() -> {
            someLongBlockingInternalOperation();
            System.out.println(mail + " sent");
        });
    }

    private void someLongBlockingInternalOperation() {
        try {
            Thread.sleep(2000); // don't do it at home
        } catch (InterruptedException e) {
            System.out.println("Ignore interrupted");
        }
    }
}
