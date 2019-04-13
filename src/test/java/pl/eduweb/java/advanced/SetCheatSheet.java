package pl.eduweb.java.advanced;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class SetCheatSheet {

    @Test
    void creationAndBasicOperations() {
        Set<Integer> set = new HashSet<>();

        set.add(1);
        set.add(1);
        set.add(2);

        System.out.println(set);

        set.remove(1);
        System.out.println(set);

        System.out.println(set.size());

        set.addAll(Arrays.asList(3, 4, 5));
        System.out.println(set);

        System.out.println(set.contains(3));
    }

    @Test
    void intersection() {
        Set<Integer> set1 = new HashSet<>();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        Set<Integer> set2 = new HashSet<>();
        set2.add(2);
        set2.add(3);
        set2.add(4);

        System.out.println(intersection(set1, set2));
    }

    @Test
    void sortedSet() {
        SortedSet<Integer> sortedSet = new TreeSet<>();
        sortedSet.add(5);
        sortedSet.add(8);
        sortedSet.add(-3);
        sortedSet.add(3);
        sortedSet.add(2);
        System.out.println(sortedSet);
        System.out.println(sortedSet.first());
        System.out.println(sortedSet.last());
    }

    @Test
    void navigableSet() {
        NavigableSet<Integer> navigableSet = new TreeSet<>();
        navigableSet.add(5);
        navigableSet.add(8);
        navigableSet.add(-3);
        navigableSet.add(3);
        navigableSet.add(2);
        System.out.println(navigableSet);
        System.out.println(navigableSet.first());
        System.out.println(navigableSet.last());

        System.out.println(navigableSet.higher(5));
        System.out.println(navigableSet.lower(5));
    }

    @Test
    void workingWithNotComparableTypes() {
        NavigableSet<Box<Integer>> navigableSet = new TreeSet<>(Comparator.comparing(Box::getValue, Comparator.nullsLast(Integer::compareTo)));
        navigableSet.add(new Box<>(null));
        navigableSet.add(new Box<>(5));
        navigableSet.add(new Box<>(8));
        navigableSet.add(new Box<>(-3));
        navigableSet.add(new Box<>(3));
        navigableSet.add(new Box<>(2));
        System.out.println(navigableSet);
        System.out.println(navigableSet.first());
        System.out.println(navigableSet.last());
    }

    @Test
    void unmodifiableSet() {
        Set<Integer> set = new HashSet<>();
        set.add(1);

        var unmodifiableSet = Collections.unmodifiableSet(set);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            unmodifiableSet.add(-1);
        });
    }

    private <E> Set<E> intersection(Set<? extends E> set1, Set<? extends E> set2) {
        var result = new HashSet<E>(set1);
        result.retainAll(set2);
        return result;
    }

    private static class Box<E> {
        private E value;

        public Box(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Box{" +
                    "value=" + value +
                    '}';
        }
    }
}
