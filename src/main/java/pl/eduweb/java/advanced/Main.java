package pl.eduweb.java.advanced;


import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        Predicate<Integer> lowerThan50 = i -> i < 50;
        System.out.println(lowerThan50.test(30));

        Function<String, Double> parseStringToDouble = Double::valueOf;
        System.out.println(parseStringToDouble.apply("2.0"));

        Consumer<String> printString = System.out::println;
        printString.accept("some string to print");

        Supplier<Integer> pseudoRandomSupplier = () -> (int) (Math.random() * 50 + 1);
        System.out.println(pseudoRandomSupplier.get());
    }

}
