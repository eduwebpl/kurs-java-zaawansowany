package pl.eduweb.java.advanced;

class MailSender {
    public void sendMail(String mail) {
        someLongBlockingInternalOperation();
        System.out.println(mail + " sent");
    }

    private void someLongBlockingInternalOperation() {
        try {
            Thread.sleep(2000); // don't do it at home
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
