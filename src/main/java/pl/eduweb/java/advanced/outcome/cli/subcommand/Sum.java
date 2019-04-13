package pl.eduweb.java.advanced.outcome.cli.subcommand;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.time.OffsetDateTime;

@Command(name = "sum")
public class Sum extends BaseSubCommand {

    @Option(names = {"-t", "--to-date"}, description = "To date")
    private String toDateStr;

    @Option(names = {"-f", "--from-date"}, description = "From date")
    private String fromDateStr;

    @Override
    public void runCommand() {
        OffsetDateTime from = parseOptionalDate(this.fromDateStr).orElse(null);
        OffsetDateTime to = parseOptionalDate(this.toDateStr).orElse(null);
        doInRepository(repository -> {
            var sum = repository.computeSum(from, to);
            System.out.println("Outcome sum: " + sum.toString());
        });
    }
}
