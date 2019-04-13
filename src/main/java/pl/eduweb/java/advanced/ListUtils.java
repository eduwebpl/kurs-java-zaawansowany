package pl.eduweb.java.advanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtils {
    private ListUtils() {
    }

    public static <V> List<V> add(List<? extends V> list1, List<? extends V> list2) {
        List<V> result = new ArrayList<>();
        result.addAll(list1);
        result.addAll(list2);
        return result;
    }

    public static <V> void addToEach(List<V> sourceList,
                                     List<? super V> targetList1,
                                     List<? super V> targetList2) {
        targetList1.addAll(sourceList);
        targetList2.addAll(sourceList);
    }

    public static String prettyToString(List<?> list) {
        return Arrays.toString(list.toArray());
    }

}
