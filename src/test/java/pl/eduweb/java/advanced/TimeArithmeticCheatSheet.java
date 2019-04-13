package pl.eduweb.java.advanced;


import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Period;
import java.time.ZonedDateTime;

public class TimeArithmeticCheatSheet {

    @Test
    void periodAndDurationAddComparisonDate() {
        var dateTimeBeforeOffsetChange = ZonedDateTime.parse("2018-10-27T12:00:00+02:00[Europe/Warsaw]");
        System.out.println(dateTimeBeforeOffsetChange);
        System.out.println(dateTimeBeforeOffsetChange.plus(Period.ofDays(1)));
        System.out.println(dateTimeBeforeOffsetChange.plus(Duration.ofDays(1)));
    }

    @Test
    void computeNumberOfDaysBetween() {
        var zonedDateTime1 = ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]");
        var zonedDateTime2 = ZonedDateTime.parse("2008-12-03T10:15:30+01:00[Europe/Paris]");
        System.out.println(Duration.between(zonedDateTime1, zonedDateTime2).toDays());        
    }

    @Test
    void datesComparison() {
        var zonedDateTime1 = ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]");
        var zonedDateTime2 = ZonedDateTime.parse("2008-12-03T10:15:30+01:00[Europe/Paris]");

        System.out.println(zonedDateTime1.isAfter(zonedDateTime2));
        System.out.println(zonedDateTime1.isBefore(zonedDateTime2));
        System.out.println(zonedDateTime1.isEqual(zonedDateTime2));
    }

    @Test
    void durationComparison() {
        System.out.println(Duration.ofDays(1).compareTo(Duration.ofDays(2)));
    }
}
