package pl.eduweb.java.advanced.outcome.cli.subcommand;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import pl.eduweb.java.advanced.outcome.Outcome;
import pl.eduweb.java.advanced.outcome.repository.OutcomesRepository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Command(name = "add")
public class Add extends BaseSubCommand {

    @Parameters(index = "0")
    BigDecimal amount;

    @Option(names = {"-d", "--date"})
    String date;

    @Option(names = {"-c", "--comment"})
    String comment;

    @Override
    public void runCommand() {
        OffsetDateTime date = parseOptionalDate(this.date).orElseGet(OffsetDateTime::now);
        Outcome outcome = new Outcome(comment, date, amount);
        doInRepository(repository -> addOutcome(outcome, repository));
    }

    private void addOutcome(Outcome outcome, OutcomesRepository repository) {
        repository.add(outcome);
        System.out.println("Outcome successfully added.");
    }

}
