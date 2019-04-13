package pl.eduweb.java.advanced.outcome.cli.subcommand;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import pl.eduweb.java.advanced.outcome.Outcome;
import pl.eduweb.java.advanced.outcome.dump.OutcomeLoader;
import pl.eduweb.java.advanced.outcome.dump.csv.CsvOutcomeLoader;

import java.nio.file.Path;
import java.util.List;

@Command(name = "import")
public class Import extends BaseSubCommand {

    @SuppressWarnings("unused")
    @Option(names = {"-f", "--file"},
            description = "File to import",
            required = true)
    private Path importPath;

    @Override
    public void runCommand() {
        OutcomeLoader outcomeLoader = new CsvOutcomeLoader(importPath);
        List<Outcome> outcomesToImport = outcomeLoader.load();
        doInRepository(repository -> repository.addAll(outcomesToImport));
        System.out.println(String.format("Successfully imported %d outcomes", outcomesToImport.size()));
    }
}
