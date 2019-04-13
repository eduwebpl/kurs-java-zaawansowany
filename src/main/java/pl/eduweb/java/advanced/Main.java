package pl.eduweb.java.advanced;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Character> list = Arrays.asList("  bar ", " fo o", "hello")
                .stream()
                .map(String::trim)
                .flatMap(e -> e.chars().mapToObj(c -> (char) c))
                .filter(c -> !" ".equals(c.toString()))
                .collect(Collectors.toList());
    }

}
