package pl.eduweb.java.advanced;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        var regex = "\\d";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("d");
        System.out.println(matcher.matches());
    }

}
