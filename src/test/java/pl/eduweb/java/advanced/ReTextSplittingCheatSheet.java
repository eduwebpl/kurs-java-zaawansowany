package pl.eduweb.java.advanced;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class ReTextSplittingCheatSheet {

    private static final String FILE_NAME = "lorem.txt";

    @Test
    void stringSplit() throws IOException {
        var firstLine = getFirstLine();
        for (String word : firstLine.split("\\W+")) {
            System.out.println(word);
        }
    }

    @Test
    void patternSplit() throws IOException {
        var firstLine = getFirstLine();
        Pattern pattern = Pattern.compile("\\W+");
        for (String word : pattern.split(firstLine)) {
            System.out.println(word);
        }
    }

    @Test
    void patternSplitAsStream() throws IOException {
        var firstLine = getFirstLine();
        Pattern pattern = Pattern.compile("\\W+");
        pattern.splitAsStream(firstLine).forEach(System.out::println);
    }

    @Test
    void countWordsFromWholeFile() throws IOException {
        Pattern pattern = Pattern.compile("\\W+");
        long count = Files.lines(Paths.get(FILE_NAME))
                .filter(s -> !s.isEmpty())
                .flatMap(pattern::splitAsStream)
                .count();
        System.out.println(count);
    }

    private String getFirstLine() throws IOException {
        return Files.lines(Paths.get(FILE_NAME)).findFirst().orElseThrow();
    }

}
