package pl.eduweb.java.advanced;


import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Object> objectList = new ArrayList<>();
        objectList.add(1);
        objectList.add("too");
        objectList.add("also");
        objectList.add(2.0);

        System.out.println(ListUtils.prettyToString(objectList));
    }

}
