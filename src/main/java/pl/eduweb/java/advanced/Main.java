package pl.eduweb.java.advanced;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String input = "Lorem ipsum dolor sit amet,\n(consectetur adipiscing) elit.";
        var regex = ".+^\\(.*\\).*";
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(input);
        System.out.println(matcher.matches());
    }

}
