package pl.eduweb.java.advanced;


import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalCheatSheet {

    @Test
    void optionalCreation() {
        // will throw exception when value is null
        Optional<String> opt1 = Optional.of("Foo");// when we know that our value can't be a null (if it is it is a programmer error)

        Optional<String> opt2 = Optional.ofNullable("Bar"); // when we know that in some cases returned value can be a null
        Optional<Object> emptyOpt = Optional.empty();
    }

    @Test
    void optionalAsMethodResult() {
        var result = divide(20, 2);
        if (result.isPresent()) {
            System.out.println("Result: " + result.get());
        } else {
            System.out.println("Computation error");
        }
    }

    @Test
    void mappingOptional() {
        var result = divide(20, 2);
        String communicate = result.map(r -> "Result: " + r)
                .orElse("Computation error");
        System.out.println(communicate);
    }

    @Test
    void orElseThrow() {
        var result = divide(20, 0);
        String communicate = result.map(r -> "Result: " + r)
                .orElseThrow();
        System.out.println(communicate);
    }

    private Optional<Double> divide(int dividend, int divider) {
        if (divider == 0) {
            return Optional.empty();
        } else {
            return Optional.of((double) dividend / divider);
        }
    }
    
}
