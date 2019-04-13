package pl.eduweb.java.advanced.outcome.cli.subcommand;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import pl.eduweb.java.advanced.outcome.Outcome;

import java.math.BigDecimal;
import java.util.Optional;

@SuppressWarnings("unused")
@Command(name = "update")
public class Update extends BaseSubCommand {

    @Parameters(index = "0", description = "Id of outcome to update")
    private long id;

    @Option(names = {"-c", "--comment"}, description = "New comment")
    private String comment;

    @Option(names = {"-d", "--date"}, description = "New date")
    private String dateStr;

    @Option(names = {"-a", "--amount"}, description = "New amount")
    private BigDecimal amount;

    @Override
    public void runCommand() {
        boolean updated = computeWithRepository(repository ->
                repository.getOne(id)
                        .map(this::mergeWithParameters)
                        .map(repository::update)
                        .orElse(false)
        );
        if (updated) {
            System.out.println("Outcome successfully updated");
        } else {
            System.err.println(String.format("No outcome with such id: %d", id));
        }
    }

    private Outcome mergeWithParameters(Outcome outcome) {
        Optional.ofNullable(comment).ifPresent(outcome::setComment);
        Optional.ofNullable(amount).ifPresent(outcome::setAmount);
        parseOptionalDate(this.dateStr).ifPresent(outcome::setTime);
        return outcome;
    }

}
