package pl.eduweb.java.advanced;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Main {

    private static final DateTimeFormatter PARSE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mmX'D'yyyy-MM-dd");
    private static final DateTimeFormatter PRINT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    public static void main(String[] args) throws IOException {
        Files.lines(Paths.get("files/samples2.csv"))
                .map(Main::parseLine)
                .sorted(Comparator.comparing(Sample::getTime).reversed())
                .map(s -> PRINT_DATE_TIME_FORMATTER.format(s.getTime()))
                .forEach(System.out::println);
    }

    private static Sample parseLine(String line) {
        String[] columns = line.split(",");
        var rawTime = columns[0];
        var rawValue = columns[1];

        var time = OffsetDateTime.parse(rawTime, PARSE_DATE_TIME_FORMATTER);
        var value = Integer.parseInt(rawValue);
        return new Sample(time, value);
    }

}
