package pl.eduweb.java.advanced;

class MailSender {
    public void sendMail(String mail) {
        someLongBlockingInternalOperation();
        System.out.println(mail + " sent");
    }

    private void someLongBlockingInternalOperation() {
        while (true) {
            try {
                Thread.sleep(1000000); // don't do it at home
            } catch (InterruptedException e) {
                System.out.println("interrupted ignored");
            }
        }
    }
}
