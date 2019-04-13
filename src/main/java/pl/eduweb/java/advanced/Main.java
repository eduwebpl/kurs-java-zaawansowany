package pl.eduweb.java.advanced;


public class Main {

    private static boolean stop = false;

    private static synchronized boolean getStop() {
        return stop;
    }

    private static synchronized void setStop(boolean stop) {
        Main.stop = stop;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!getStop()) {
            }
            System.out.println("The end of second thread");
        });
        thread.start();
        Thread.sleep(1000);
        setStop(true);
        System.out.println("The end of main");
    }

}
