package pl.eduweb.java.advanced;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

class UserService {
    private ExecutorService executorService;

    public UserService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public CompletableFuture<String> getUserName(Long userId) {
        return CompletableFuture.supplyAsync(() -> {
            someLongBlockingInternalOperation();
            return "user" + userId;
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
