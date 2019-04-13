package pl.eduweb.java.advanced;


import java.time.Duration;
import java.time.Instant;
import java.time.Period;

public class Main {

    public static void main(String[] args) {
        var instant = Instant.now();
        var period2d = Period.ofDays(2);
        var duration2h = Duration.ofHours(2);
    }

}
