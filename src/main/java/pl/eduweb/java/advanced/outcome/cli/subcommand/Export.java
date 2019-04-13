package pl.eduweb.java.advanced.outcome.cli.subcommand;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import pl.eduweb.java.advanced.outcome.Outcome;
import pl.eduweb.java.advanced.outcome.dump.csv.CsvOutcomeExporter;
import pl.eduweb.java.advanced.outcome.repository.OutcomesRepository;

import java.nio.file.Path;
import java.util.List;

@Command(name = "export")
public class Export extends BaseSubCommand {

    @SuppressWarnings("unused")
    @Option(
            names = {"-f", "--file"},
            description = "Path to export file",
            defaultValue = "outcome-registry-dump.csv")
    private Path exportPath;

    @Override
    public void runCommand() {
        List<Outcome> outcomes = computeWithRepository(OutcomesRepository::getAll);
        new CsvOutcomeExporter(exportPath).export(outcomes);
        System.out.println(String.format("Successfully exported %d outcomes", outcomes.size()));
    }
}
