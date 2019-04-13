package pl.eduweb.java.advanced;


public class Main {

    public static void main(String[] args) {
        Tuple<String, Integer> tuple1 = new Tuple<>("text", 1);

        Integer right = tuple1.getRight();
        String left = tuple1.getLeft();

        Tuple<String, String> duplicated = Tuple.duplicated("foo");
    }

}
