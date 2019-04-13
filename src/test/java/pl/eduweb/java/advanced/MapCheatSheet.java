package pl.eduweb.java.advanced;


import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapCheatSheet {

    @Test
    void mapCreation() {
        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 5);
        System.out.println(map);
    }

    @Test
    void getElement() {
        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 1);
        System.out.println(map.get("key1"));
        System.out.println(map.get("not existing"));
        System.out.println(map.getOrDefault("not existing", 0));
        System.out.println(Optional.ofNullable(map.get("not existing")));
    }

    @Test
    void workingWithStream() {
        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 5);

        List<String> list = map.entrySet()
                .stream()
                .map(e -> e.getKey() + e.getValue())
                .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    void keyAndValueSet() {
        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 5);

        var keys = new ArrayList<>(map.keySet());
        System.out.println(keys);

        var values = new ArrayList<>(map.values());
        System.out.println(values);
    }

    @Test
    void changeListToMap() {
        List<String> words = Arrays.asList("foo", "bar", "rocket", "online");

        Map<String, Integer> wordsLength = words.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(wordsLength);
    }

    @Test
    void groupListElements() {
        List<String> words = Arrays.asList("foo", "bar", "rocket", "online");

        Map<Integer, List<String>> wordsLength = words.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(wordsLength);
    }

    @Test
    void sortedMap() {
        SortedMap<Integer, String> map = new TreeMap<>();

        map.put(1, "foo");
        map.put(5, "bar");
        map.put(-10, "frog");

        System.out.println(map.get(5));
        System.out.println(map.firstKey());
        System.out.println(map.lastKey());
        System.out.println(map.tailMap(1));
        System.out.println(map.headMap(1));
    }

    @Test
    void navigableMap() {
        NavigableMap<Integer, String> map = new TreeMap<>();

        map.put(1, "foo");
        map.put(5, "bar");
        map.put(-10, "frog");

        System.out.println(map.get(5));
        System.out.println(map.lowerEntry(5));
        System.out.println(map.higherEntry(5));

        System.out.println(map.floorEntry(4));
        System.out.println(map.floorEntry(5));
    }
    
}
