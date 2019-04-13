package pl.eduweb.java.advanced;


import java.util.ArrayList;
import java.util.List;

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("String");

        List<String> typedList = list;
        String string = typedList.get(0);
    }

}
