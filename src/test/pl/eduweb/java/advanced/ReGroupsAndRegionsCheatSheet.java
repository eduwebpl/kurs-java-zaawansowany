package pl.eduweb.java.advanced;


import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReGroupsAndRegionsCheatSheet {

    @Test
    void groupsAtIndexes() {
        var mail = "name@example.com";
        var regex = "([a-zA-Z0-9.]+)@([a-zA-Z0-9.]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mail);
        if (matcher.matches()) {
            System.out.println("local part:  " + matcher.group(1));
            System.out.println("domain name: " + matcher.group(2));
        }
    }

    @Test
    void namedGroups() {
        var mail = "name@example.com";
        var regex = "(?<local>[a-zA-Z0-9.]+)@(?<domain>[a-zA-Z0-9.]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mail);
        if (matcher.matches()) {
            System.out.println("local part:  " + matcher.group("local"));
            System.out.println("domain name: " + matcher.group("domain"));
        }
    }

    @Test
    void groupCanBeUnavailableWhenAlternativeIsUsed() {
        var mail = "name@@example.com";
        var regex = "(?<local>[a-zA-Z0-9.]+)@(?<domain>[a-zA-Z0-9.]+)|(?<rest>.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mail);
        if (matcher.matches()) {
            System.out.println("local part:  " + matcher.group("local"));
            System.out.println("domain name: " + matcher.group("domain"));
            System.out.println("domain name: " + matcher.group("rest"));
        }
    }

    @Test
    void matchWithoutRegion() {
        String text = "2018/11/05 17:07:41 INFO [lt-dispatcher-5] org.apache.flink.runtime.jobmanager.JobManager - Stopping JobManager akka://flink/user/jobmanager_1.";
        var regex = "(INFO|WARN|ERROR).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        System.out.println(matcher.matches());
    }

    @Test
    void ignoreSomeTextAtTheBeginningUsingRegion() {
        String text = "2018/11/05 17:07:41 INFO [lt-dispatcher-5] org.apache.flink.runtime.jobmanager.JobManager - Stopping JobManager akka://flink/user/jobmanager_1.";
        var regex = "(INFO|WARN|ERROR).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text).region(20, text.length());
        System.out.println(matcher.matches());
    }
}
