package pl.eduweb.java.advanced;

import java.time.OffsetDateTime;

public class Sample implements Comparable<Sample> {
    private final OffsetDateTime time;
    private final int value;

    public Sample(OffsetDateTime time, int value) {
        this.time = time;
        this.value = value;
    }

    public OffsetDateTime getTime() {
        return time;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Sample{" +
                "time=" + time +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Sample o) {
        return time.compareTo(o.time);
    }
}
