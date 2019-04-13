package pl.eduweb.java.advanced;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Main {
    private final static String DOMAIN_NAME = "example.com";
    private final static String ID_PREFIX = "e";
    private final static AtomicLong sentMails = new AtomicLong(0L);


    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100000)
                .parallel()
                .mapToObj(Main::makeAddress)
                .forEach(handleIOException(Main::sendMail));

        System.out.println(sentMails);
    }

    private static String makeAddress(Integer id) {
        return ID_PREFIX + id + "@" + DOMAIN_NAME;
    }

    private static void sendMail(String address) throws IOException {
        System.out.println("Email sent to: " + address);
        sentMails.incrementAndGet();
        throw new IOException();
    }

    private static Consumer<String> handleIOException(ThrowingConsumer<String, IOException> consumer) {
        return (element) -> {
            try {
                consumer.accept(element);
            } catch (IOException e1) {
                throw new RuntimeException(e1);
            }
        };
    }

}