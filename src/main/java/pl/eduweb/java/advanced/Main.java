package pl.eduweb.java.advanced;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        BricksWarehouse bricksWarehouse1 = new BricksWarehouse(50L);
        BricksWarehouse bricksWarehouse2 = new BricksWarehouse(100L);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> bricksWarehouse1.transportTo(bricksWarehouse2, 50));
        executorService.execute(() -> bricksWarehouse2.transportTo(bricksWarehouse1, 50));
        executorService.shutdown();
        System.out.println("The end of main");
    }

    private static class BricksWarehouse {
        private long bricksCount;

        public BricksWarehouse(long bricksCount) {
            this.bricksCount = bricksCount;
        }

        public synchronized void transportTo(BricksWarehouse targetWarehouse, long bricks) {
            this.setBricksCount(this.getBricksCount() - bricks);
            sleep();
            targetWarehouse.setBricksCount(targetWarehouse.getBricksCount() + bricks);
        }

        public synchronized long getBricksCount() {
            return bricksCount;
        }

        public synchronized void setBricksCount(long bricksCount) {
            this.bricksCount = bricksCount;
        }

        private void sleep() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }
}
