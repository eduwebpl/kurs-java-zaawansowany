package pl.eduweb.java.advanced.outcome;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Outcome {
    private Long id;
    private String comment;
    private OffsetDateTime time;
    private BigDecimal amount;

    public Outcome(Long id, String comment, OffsetDateTime time, BigDecimal amount) {
        this.id = id;
        this.comment = comment;
        this.time = time;
        this.amount = amount;
    }

    public Outcome(String comment, OffsetDateTime time, BigDecimal amount) {
        this(null, comment, time, amount);
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public OffsetDateTime getTime() {
        return time;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTime(OffsetDateTime time) {
        this.time = time;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Outcome{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", time=" + time +
                ", amount=" + amount +
                '}';
    }
}
