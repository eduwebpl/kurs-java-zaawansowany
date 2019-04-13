package pl.eduweb.java.advanced;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread otherThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Tick");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted Exception");
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("The end of worker thread");
        });
        otherThread.start();
        Thread.sleep(1000);
        otherThread.interrupt();
        System.out.println("The end of main");
    }

}
