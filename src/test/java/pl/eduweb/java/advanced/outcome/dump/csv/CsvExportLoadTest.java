package pl.eduweb.java.advanced.outcome.dump.csv;

import org.junit.jupiter.api.Test;
import pl.eduweb.java.advanced.outcome.Outcome;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvExportLoadTest {

    private static final Outcome outcome1 = new Outcome("Outcome1", OffsetDateTime.parse("2019-01-01T12:00:00+01:00"), new BigDecimal("100.50"));
    private static final Outcome outcome2 = new Outcome("Outcome2", OffsetDateTime.parse("2019-01-02T12:00:00+01:00"), new BigDecimal("50.50"));
    private static final Outcome outcome3 = new Outcome("Outcome3", OffsetDateTime.parse("2019-01-03T12:00:00+01:00"), new BigDecimal("30.50"));

    @Test
    void exportAndLoadTest() throws IOException {
        Path tempFile = Files.createTempFile(null, null);
        List<Outcome> toExport = Arrays.asList(outcome1, outcome2, outcome3);

        CsvOutcomeExporter csvOutcomeExporter = new CsvOutcomeExporter(tempFile);
        CsvOutcomeLoader csvOutcomeLoader = new CsvOutcomeLoader(tempFile);

        csvOutcomeExporter.export(toExport);
        List<Outcome> loaded = csvOutcomeLoader.load();

        assertEquals(toExport, loaded);
    }

    @Test
    void exportAndLoadEmptyListTest() throws IOException {
        Path tempFile = Files.createTempFile(null, null);
        List<Outcome> toExport = Collections.emptyList();

        CsvOutcomeExporter csvOutcomeExporter = new CsvOutcomeExporter(tempFile);
        CsvOutcomeLoader csvOutcomeLoader = new CsvOutcomeLoader(tempFile);

        csvOutcomeExporter.export(toExport);
        List<Outcome> loaded = csvOutcomeLoader.load();

        assertTrue(loaded.isEmpty());
    }

}