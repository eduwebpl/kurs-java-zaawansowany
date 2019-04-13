package pl.eduweb.java.advanced;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Integer[] integersArray = {-1, 1, 2, 3, 4, 5, 5, 6};
        var integersList = Arrays.asList(integersArray);

        var positiveIntegers = filterPositive(integersList);
        long duplicatesNumber = countDuplicates(positiveIntegers);

        System.out.println("Number of positive duplicates: " + duplicatesNumber);
    }

    private static long countDuplicates(List<Integer> list) {
        return list.size() - sizeWithoutDuplicates(list);
    }

    private static long sizeWithoutDuplicates(List<Integer> positiveIntegers) {
        return positiveIntegers.stream()
                .distinct()
                .count();
    }

    private static List<Integer> filterPositive(List<Integer> integers) {
        return integers.stream()
                .filter(e -> e > 0)
                .collect(Collectors.toList());
    }

}
