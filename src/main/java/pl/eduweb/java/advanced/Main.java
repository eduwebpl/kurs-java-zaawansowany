package pl.eduweb.java.advanced;


public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        MailSender mailSender = new MailSender();
        mailSender.sendMail("mail1");

        System.out.println(System.currentTimeMillis() - start);
        System.out.println("Response to client");
    }

}
