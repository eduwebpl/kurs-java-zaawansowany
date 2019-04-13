package pl.eduweb.java.advanced;


import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AdditionalStreamFeaturesCheatSheet {

    @Test
    void allMatch() {
        System.out.println(Stream.of(1, 2, 3, 4, 5)
                .allMatch(e -> e >= 0));
    }

    @Test
    void anyMatch() {
        System.out.println(Stream.of(-1, 2, 3, 4, 5)
                .anyMatch(e -> e < 0));
    }

    @Test
    void noneMatch() {
        System.out.println(Stream.of(1, 2, 3, 4, 5)
                .noneMatch(e -> e == 10));
    }
    @Test
    void skip() {
        System.out.println(Stream.of(1, 2, 3, 4, 5)
                .skip(2).collect(Collectors.toList()));
    }

    @Test
    void limit() {
        System.out.println(Stream.of(1, 2, 3, 4, 5)
                .limit(2).collect(Collectors.toList()));
    }

    @Test
    void primitiveStreamCreation() {
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
        IntStream intStream2 = Stream.of(1, 2, 3, 4, 5).mapToInt(e -> e);
    }

    @Test
    void primitiveStreamToObjectStream() {
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
        IntStream intStream2 = Stream.of(1, 2, 3, 4, 5).mapToInt(e -> e);

        Stream<Integer> integerStream = intStream2.mapToObj(e -> e);
        Stream<Integer> integerStream2 = intStream.boxed();
    }

    @Test
    void primitiveStreamStatisticalOperations() {
        System.out.println(
                IntStream.of(1, 2, 3, 4, 5).sum()
        );

        System.out.println(
                IntStream.of(1, 2, 3, 4, 5).average()
        );

        System.out.println(
                IntStream.of(1, 2, 3, 4, 5).min()
        );

        System.out.println(
                IntStream.of(1, 2, 3, 4, 5).max()
        );

        System.out.println(
                IntStream.of(1, 2, 3, 4, 5).summaryStatistics()
        );
    }

    @Test
    void benchmarkSequentialStream() {
        long duration = measureDuration(() ->
                IntStream.rangeClosed(1, 100)
                        .sequential()
                        .map(this::heavyOperation)
                        .forEach(System.out::println)
        );
        System.out.println(duration);
    }

    @Test
    void benchmarkParallelStream() {
        long duration = measureDuration(() ->
                IntStream.rangeClosed(1, 100)
                        .parallel()
                        .map(this::heavyOperation)
                        .forEach(System.out::println)
        );
        System.out.println(duration);
    }

    @Test
    void isParallel() {
        System.out.println(IntStream.rangeClosed(1, 100).parallel().isParallel());
        System.out.println(IntStream.rangeClosed(1, 100).sequential().isParallel());
        System.out.println(IntStream.rangeClosed(1, 100).isParallel());
    }

    private long measureDuration(Runnable toBenchmark) {
        long startTime = System.currentTimeMillis();
        toBenchmark.run();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private int heavyOperation(int element) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        return element + 1;
    }
}
