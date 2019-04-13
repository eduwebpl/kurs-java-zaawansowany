package pl.eduweb.java.advanced;


import java.time.Duration;
import java.time.Period;
import java.time.ZonedDateTime;

public class Main {

    public static void main(String[] args) {
        var dateTimeBeforeOffsetChange = ZonedDateTime.parse("2018-10-27T12:00:00+02:00[Europe/Warsaw]");
        System.out.println(dateTimeBeforeOffsetChange);

        System.out.println(dateTimeBeforeOffsetChange.plus(Period.ofDays(1)));
        System.out.println(dateTimeBeforeOffsetChange.plus(Duration.ofDays(1)));
    }

}
