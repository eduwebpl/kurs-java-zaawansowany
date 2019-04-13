package pl.eduweb.java.advanced.outcome.repository;

import pl.eduweb.java.advanced.outcome.Outcome;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface OutcomesRepository {

    void add(Outcome outcome);

    void addAll(List<Outcome> outcomes);

    boolean update(Outcome outcome);

    boolean delete(long id);

    int deleteAll();

    List<Outcome> getAll();

    Optional<Outcome> getOne(long id);

    BigDecimal computeSum(OffsetDateTime fromDate, OffsetDateTime toDate);

}
