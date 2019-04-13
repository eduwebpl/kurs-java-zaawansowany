package pl.eduweb.java.advanced;

import java.util.Arrays;

public class SupportMain {

    public static void main(String[] args) {
        var integersList = Arrays.asList(-1, 1, 2, 3, 3, 4, 5, 5, 6, 8, 8, 8);

        System.out.println(DuplicatesSupport.getDuplicates(integersList));
        System.out.println(DuplicatesSupport.getDuplicatesMap(integersList));
        System.out.println(DuplicatesSupport.getWithoutDuplicates(integersList));
    }
}
