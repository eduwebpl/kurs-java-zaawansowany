package pl.eduweb.java.advanced.outcome.dump.csv;

import pl.eduweb.java.advanced.outcome.Outcome;
import pl.eduweb.java.advanced.outcome.dump.OutcomeLoader;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CsvOutcomeLoader implements OutcomeLoader {
    private static final int EXPECTED_COLUMNS_NUMBER = 3;
    private Path filePath;

    public CsvOutcomeLoader(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Outcome> load() {
        try {
            return Files.readAllLines(filePath).stream()
                    .filter(s -> !s.isEmpty())
                    .map(this::mapToOutcome)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException("Exception during loading outcomes from csv file", e);
        }
    }

    private Outcome mapToOutcome(String csvRow) {
        try {
            String[] parts = csvRow.split(",", -1);
            if (parts.length != EXPECTED_COLUMNS_NUMBER) {
                throw new RuntimeException("Wrong number of columns: " + parts.length);
            }
            return new Outcome(parts[2], OffsetDateTime.parse(parts[1]), new BigDecimal(parts[0]));
        } catch (Exception e) {
            throw new RuntimeException("Exception during parsing csv file", e);
        }
    }
}
