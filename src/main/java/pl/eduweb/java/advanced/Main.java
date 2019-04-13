package pl.eduweb.java.advanced;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
        var localDate = LocalDate.now();
        var localTime = LocalTime.now();
        var localDateTime = LocalDateTime.now();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);
    }

}
