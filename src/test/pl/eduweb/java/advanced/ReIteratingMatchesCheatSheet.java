package pl.eduweb.java.advanced;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReIteratingMatchesCheatSheet {

    private static final String REGEX = "[\\w][^.]+\\.";
    private static final Pattern pattern = Pattern.compile(REGEX);

    @Test
    void iterateUsingWhile() throws IOException {
        var firstLine = getFirstLine();
        Matcher matcher = pattern.matcher(firstLine);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    void iterateUsingMatcherResults() throws IOException {
        var firstLine = getFirstLine();
        Matcher matcher = pattern.matcher(firstLine);

        matcher.results()
                .map(MatchResult::group)
                .forEach(System.out::println);
    }

    private String getFirstLine() throws IOException {
        return Files
                .lines(Paths.get("lorem.txt"))
                .findFirst()
                .orElseThrow();
    }
}
