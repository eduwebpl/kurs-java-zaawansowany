package pl.eduweb.java.advanced;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class LocalTimeCheatSheet {

    @Test
    void createUsingNow() {
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
    }

    @Test
    void createLocalDateTimeAtSpecificZone() {
        System.out.println(LocalDateTime.now(ZoneId.of("America/New_York")));
    }

    @Test
    void createUsingOf() {
        System.out.println(LocalDateTime.of(2018, 12, 1, 12, 30));
    }

    @Test
    void createUsingParse() {
        System.out.println(LocalDateTime.parse("2007-12-03T10:15:30"));
    }

    @Test
    void getSpecificField() {
        var localDateTime = LocalDateTime.of(2018, 12, 1, 12, 30);

        System.out.println(localDateTime.getHour());
        System.out.println(localDateTime.getMinute());
        System.out.println(localDateTime.get(ChronoField.SECOND_OF_DAY));
    }

    @Test
    void truncateToSpecificField() {
        var localDateTime = LocalDateTime.of(2018, 12, 1, 12, 30, 15);

        System.out.println(localDateTime);
        LocalDateTime truncatedToMinutes = localDateTime.truncatedTo(ChronoUnit.MINUTES);
        System.out.println(truncatedToMinutes.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
    
}
