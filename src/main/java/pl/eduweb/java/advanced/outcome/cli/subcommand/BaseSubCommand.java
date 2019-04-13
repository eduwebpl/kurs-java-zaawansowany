package pl.eduweb.java.advanced.outcome.cli.subcommand;

import pl.eduweb.java.advanced.outcome.repository.JdbcOutcomesRepository;
import pl.eduweb.java.advanced.outcome.repository.OutcomesRepository;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static picocli.CommandLine.Option;

public abstract class BaseSubCommand implements Runnable {

    @SuppressWarnings("unused")
    @Option(
            names = {"-p", "--path"},
            description = "Path to database file",
            defaultValue = "outcome-registry.db")
    private Path databasePath;

    @SuppressWarnings("unused")
    @Option(
            names = {"-v", "--verbose"},
            defaultValue = "false")
    private boolean verbose;

    protected void doInRepository(Consumer<OutcomesRepository> consumer) {
        try (var repository = new JdbcOutcomesRepository(databasePath)) {
            consumer.accept(repository);
        } catch (Exception e) {
            throw new RuntimeException("Exception during working with repository", e);
        }
    }

    protected <R> R computeWithRepository(Function<OutcomesRepository, R> function) {
        try (var repository = new JdbcOutcomesRepository(databasePath)) {
            return function.apply(repository);
        } catch (Exception e) {
            throw new RuntimeException("Exception during working with repository", e);
        }
    }

    protected Optional<OffsetDateTime> parseOptionalDate(String dateString) {
        return Optional.ofNullable(dateString)
                .map(str -> LocalDateTime.parse(str)
                        .atZone(ZoneOffset.systemDefault())
                        .toOffsetDateTime());
    }

    @Override
    public void run() {
        try {
            runCommand();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            if (verbose) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void runCommand();

}
