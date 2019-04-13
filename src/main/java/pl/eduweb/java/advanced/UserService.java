package pl.eduweb.java.advanced;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

class UserService {
    private ExecutorService executorService;

    public UserService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Future<String> getUserName(Long userId) {
        return executorService.submit(() -> {
            someLongBlockingInternalOperation();
            return "user" + userId;
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
