package pl.eduweb.java.advanced;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
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
