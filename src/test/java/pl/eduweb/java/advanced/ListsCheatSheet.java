package pl.eduweb.java.advanced;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ListsCheatSheet {

    @Test
    void arrayListCreation() {
        List<Integer> list = new ArrayList<>();
    }

    @Test
    void linkedListCreation() {
        List<Integer> list = new LinkedList<>();
    }

    @Test
    void asListFactoryMethod() {
        List<Integer> list = Arrays.asList(1, 2, 3);
    }

    @Test
    void commonOperations() {
        List<Integer> list = new ArrayList<>();
        list.add(1);

        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        System.out.println(list.get(0));
    }

    @Test
    void addingTwoLists() {
        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2));
        list.addAll(Arrays.asList(3, 4, 5));
        System.out.println(list);
    }

    @Test
    void equalsBasedRemove() {
        List<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(Integer.valueOf(1));
        System.out.println(list);
    }

    @Test
    void indexBasedRemove() {
        List<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(1);
        System.out.println(list);
    }

    @Test
    void copyList() {
        List<Integer> originalList = Arrays.asList(1, 5, 3, 6, 9, -1);
        var copiedList = new ArrayList<>(originalList);
        copiedList.add(100);
        System.out.println(originalList);
        System.out.println(copiedList);
    }

    @Test
    void sortList() {
        List<Integer> list = Arrays.asList(1, 5, 3, 6, 9, -1);
        list.sort(Comparator.naturalOrder());
        System.out.println(list);
    }

    @Test
    void sortListWithoutChangingOriginalList() {
        List<Integer> originalList = Arrays.asList(1, 5, 3, 6, 9, -1);

        var copiedList = new ArrayList<>(originalList);
        copiedList.sort(Comparator.naturalOrder());

        System.out.println(originalList);
        System.out.println(copiedList);
    }

    @Test
    void unmodifiableListCreation() {
        List<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println(list);

        List<Integer> unmodifiableList = Collections.unmodifiableList(list);
        System.out.println(unmodifiableList.get(0));

        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            unmodifiableList.set(0, 5);
        });
    }

    @Test
    void emptyUnmodifiableListCreation() {
        List<Integer> immutableEmptyList = Collections.emptyList();
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            immutableEmptyList.add(5);
        });
    }
}
