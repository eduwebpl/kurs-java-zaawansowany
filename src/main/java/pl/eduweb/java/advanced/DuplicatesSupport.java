package pl.eduweb.java.advanced;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DuplicatesSupport {

    private DuplicatesSupport() {
    }

    public static <E> Set<E> getDuplicates(List<E> list) {
        return getDuplicatesMap(list).keySet();
    }

    public static <E> Map<E, Integer> getDuplicatesMap(List<E> list) {
        return list.stream()
                .collect(Collectors.groupingBy(e -> e))
                .values()
                .stream()
                .filter(l -> l.size() > 1)
                .collect(Collectors.toMap(l -> l.get(0), List::size));
    }

    public static <E> Set<E> getWithoutDuplicates(List<E> list) {
        return new HashSet<>(list);
    }

}
