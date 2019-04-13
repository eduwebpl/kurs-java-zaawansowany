package pl.eduweb.java.advanced.outcome.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.eduweb.java.advanced.outcome.Outcome;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class JdbcOutcomesRepositoryTest {

    private static final OffsetDateTime TIME_1 = OffsetDateTime.parse("2019-01-01T12:00:00+01:00");
    private static final OffsetDateTime TIME_2 = OffsetDateTime.parse("2019-01-02T12:00:00+01:00");
    private static final OffsetDateTime TIME_3 = OffsetDateTime.parse("2019-01-03T12:00:00+01:00");

    private static final Outcome outcome1 = new Outcome("Outcome1", TIME_1, new BigDecimal("100.50"));
    private static final Outcome outcome2 = new Outcome("Outcome2", TIME_2, new BigDecimal("50.50"));
    private static final Outcome outcome3 = new Outcome("Outcome3", TIME_3, new BigDecimal("30.50"));

    private JdbcOutcomesRepository repo;


    @BeforeEach
    void setUp() throws Exception {
        Path tmpFile = Files.createTempFile("testDatabase", null);
        this.repo = new JdbcOutcomesRepository(tmpFile);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.repo.close();
    }

    @Test
    void addSingleAndGetAllTest() {
        repo.add(outcome1);
        repo.add(outcome2);
        var results = repo.getAll();
        assertEquals(2, results.size());
    }

    @Test
    void addAllAndGetAllTest() {
        repo.addAll(Arrays.asList(outcome1, outcome2));
        var results = repo.getAll();
        assertEquals(2, results.size());
    }

    @Test
    void deleteAllTest() {
        repo.addAll(Arrays.asList(outcome1, outcome2));
        var deleted = repo.deleteAll();
        var remaining = repo.getAll();
        assertEquals(2, deleted);
        assertEquals(0, remaining.size());
    }

    @Test
    void deleteByIdTest() {
        repo.addAll(Arrays.asList(outcome1, outcome2));
        Long idToDelete = repo.getAll().get(0).getId();
        var deleted = repo.delete(idToDelete);
        var remaining = repo.getAll();
        assertTrue(deleted);
        assertEquals(1, remaining.size());
    }

    @Test
    void getOneTest() {
        repo.add(outcome1);
        Long idToUpdate = repo.getAll().get(0).getId();
        var receivedOpt = repo.getOne(idToUpdate);

        assertTrue(receivedOpt.isPresent());
        var received = receivedOpt.get();
        assertNotNull(received.getId());
        assertEquals(outcome1.getAmount(), received.getAmount());
        assertEquals(outcome1.getTime(), received.getTime());
        assertEquals(outcome1.getComment(), received.getComment());
    }

    @Test
    void updateTest() {
        repo.addAll(Arrays.asList(outcome1, outcome2));
        Long idToUpdate = repo.getAll().get(0).getId();
        var toUpdateOpt = repo.getOne(idToUpdate);

        assertTrue(toUpdateOpt.isPresent());
        var toUpdate = toUpdateOpt.get();
        assertNotNull(toUpdate.getId());

        String updatedComment = "Some different comment";
        BigDecimal updatedAmount = new BigDecimal("50000.00");
        OffsetDateTime updatedTime = OffsetDateTime.parse("2007-12-03T10:15:30+01:00");

        toUpdate.setComment(updatedComment);
        toUpdate.setAmount(updatedAmount);
        toUpdate.setTime(updatedTime);

        repo.update(toUpdate);
        var updatedOpt = repo.getOne(idToUpdate);

        assertTrue(updatedOpt.isPresent());
        var updated = updatedOpt.get();
        assertNotNull(updated.getId());
        assertEquals(updatedComment, updated.getComment());
        assertEquals(updatedAmount, updated.getAmount());
        assertEquals(updatedTime, updated.getTime());
    }

    @Test
    void computeSumOfAllOutcomesTest() {
        repo.addAll(Arrays.asList(outcome1, outcome2, outcome3));
        BigDecimal sum = repo.computeSum(null, null);
        assertEquals(0, new BigDecimal("181.5").compareTo(sum));
    }

    @Test
    void computeSumTest() {
        repo.addAll(Arrays.asList(outcome1, outcome2, outcome3));
        BigDecimal sum = repo.computeSum(TIME_1, TIME_2);
        assertEquals(0, new BigDecimal("151").compareTo(sum));
    }

    @Test
    void computeSumWhenEmptyTest() {
        BigDecimal sum = repo.computeSum(TIME_1, TIME_2);
        assertEquals(0, BigDecimal.ZERO.compareTo(sum));
    }

}