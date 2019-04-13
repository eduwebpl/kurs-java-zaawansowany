package pl.eduweb.java.advanced.outcome.cli.subcommand;

import picocli.CommandLine.Command;
import pl.eduweb.java.advanced.outcome.Outcome;
import pl.eduweb.java.advanced.outcome.repository.OutcomesRepository;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Optional;

@Command(name = "list")
public class List extends BaseSubCommand {

    @Override
    public void runCommand() {
        var outcomes = computeWithRepository(OutcomesRepository::getAll);
        printOutcomes(outcomes);
    }

    private void printOutcomes(java.util.List<Outcome> outcomes) {
        outcomes.stream()
                .map(this::formatOutcome)
                .forEach(System.out::println);
        System.out.println("Total: " + outcomes.size());
    }

    private String formatOutcome(Outcome outcome) {
        return String.format("%d%20s\t\t\t%s\t\t%s",
                outcome.getId(),
                NumberFormat.getCurrencyInstance().format(outcome.getAmount()),
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(outcome.getTime()),
                Optional.ofNullable(outcome.getComment()).orElse(""));
    }
}
