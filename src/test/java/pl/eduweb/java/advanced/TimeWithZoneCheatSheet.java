package pl.eduweb.java.advanced;


import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TimeWithZoneCheatSheet {

    @Test
    void createWithNow() {
        System.out.println(Instant.now());
        System.out.println(OffsetDateTime.now());
        System.out.println(ZonedDateTime.now());
    }

    @Test
    void instantPresentation() {
        var instant = Instant.ofEpochMilli(1544265466253L);
        System.out.println(instant);
        System.out.println(instant.toEpochMilli());

        var offsetDateTime = instant.atOffset(ZoneOffset.ofHours(1));
        var zonedDateTime = instant.atZone(ZoneId.of("Europe/Warsaw"));

        System.out.println(offsetDateTime);
        System.out.println(zonedDateTime);
    }

    @Test
    void zonedDateTimePresentation() {
        ZonedDateTime inDefault = ZonedDateTime.now();
        ZonedDateTime inNY = ZonedDateTime.now(ZoneId.of("America/New_York"));
        ZonedDateTime inSydney = ZonedDateTime.now(ZoneId.of("Australia/Sydney"));

        System.out.println(inDefault);
        System.out.println(inNY);
        System.out.println(inSydney);
        System.out.println(inDefault.getOffset());
        System.out.println(inDefault.getZone());
    }

    @Test
    void zonedAndOffsetDateTimeComparison() {
        var offsetDateTime = OffsetDateTime.parse("2018-10-27T12:00:00+02:00");
        var zonedDateTime = offsetDateTime.atZoneSameInstant(ZoneId.of("Europe/Warsaw"));
        System.out.println("Original:");
        System.out.println(offsetDateTime);
        System.out.println(zonedDateTime);
        System.out.println(offsetDateTime.toInstant());

        OffsetDateTime offsetDateTimePlus1d = offsetDateTime.plusDays(1);
        ZonedDateTime zonedDateTimePlus1d = zonedDateTime.plusDays(1);

        System.out.println();
        System.out.println("OffsetDataTime + 1d:");
        System.out.println(offsetDateTimePlus1d.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
        System.out.println(offsetDateTimePlus1d.toInstant());

        System.out.println();
        System.out.println("ZonedDataTime + 1d:");
        System.out.println(zonedDateTimePlus1d.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
        System.out.println(zonedDateTimePlus1d.toInstant());
    }
    
}
