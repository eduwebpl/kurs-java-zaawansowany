package pl.eduweb.java.advanced;

public class Tuple<L, R> {

    private L left;
    private R right;

    public Tuple(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    public static <E> Tuple<E, E> duplicated(E element) {
        return new Tuple<>(element, element);
    }

}
