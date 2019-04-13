package pl.eduweb.java.advanced;


public class Main {

    private static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!stop) {
            }
            System.out.println("The end of second thread");
        });
        thread.start();
        Thread.sleep(1000);
        stop = true;
        System.out.println("The end of main");
    }


}
