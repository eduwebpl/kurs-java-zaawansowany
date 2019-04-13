package pl.eduweb.java.advanced;


import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class Main {

    public static void main(String[] args) {
        var localDate = Instant.now();
        var localTime = OffsetDateTime.now();
        var localDateTime = ZonedDateTime.now();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);
    }

}
