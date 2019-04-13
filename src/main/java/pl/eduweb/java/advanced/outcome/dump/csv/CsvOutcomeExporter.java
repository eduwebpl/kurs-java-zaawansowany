package pl.eduweb.java.advanced.outcome.dump.csv;

import pl.eduweb.java.advanced.outcome.Outcome;
import pl.eduweb.java.advanced.outcome.dump.OutcomeExport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CsvOutcomeExporter implements OutcomeExport {

    private Path filePath;

    public CsvOutcomeExporter(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public void export(List<Outcome> outcomes) {
        try {
            Files.write(filePath, mapToCsvRows(outcomes));
        } catch (IOException e) {
            throw new RuntimeException("Exception during exporting outcomes to file", e);
        }
    }

    private List<String> mapToCsvRows(List<Outcome> outcomes) {
        return outcomes.stream()
                .map(this::mapOutcomeToCsvRow)
                .collect(Collectors.toList());
    }

    private String mapOutcomeToCsvRow(Outcome outcome) {
        String amount = outcome.getAmount().toString();
        String time = outcome.getTime().toString();
        String comment = Optional.ofNullable(outcome.getComment()).orElse("");
        return String.format("%s,%s,%s", amount, time, comment);
    }

}
