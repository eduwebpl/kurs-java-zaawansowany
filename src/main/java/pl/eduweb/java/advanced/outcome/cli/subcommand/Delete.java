package pl.eduweb.java.advanced.outcome.cli.subcommand;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import pl.eduweb.java.advanced.outcome.repository.OutcomesRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Command(name = "delete")
public class Delete extends BaseSubCommand {

    @SuppressWarnings("unused")
    @Parameters(index = "0", description = "Ids of outcomes to delete", arity = "0..*")
    private Long[] ids;

    @SuppressWarnings("unused")
    @CommandLine.Option(
            names = {"-a", "--all"},
            description = "Delete all outcomes",
            defaultValue = "False")
    private boolean deleteAllOpt;

    @Override
    public void runCommand() {
        Optional<Long[]> idsOptional = Optional.ofNullable(ids);
        if (deleteAllOpt) {
            if (!idsOptional.isPresent()) {
                deleteAll();
            } else {
                throw new RuntimeException("Delete all option can't be used with specified ids");
            }
        } else {
            if (idsOptional.isPresent()) {
                deleteSpecified(Arrays.asList(idsOptional.get()));
            } else {
                throw new RuntimeException("At least one id to delete must be specified or --all/-a option used");
            }
        }
    }

    private void deleteAll() {
        Integer deleted = computeWithRepository(repository -> repository.deleteAll());
        System.out.println(String.format("Successfully deleted: %d outcomes", deleted));
    }

    private void deleteSpecified(java.util.List<Long> ids) {
        Integer deleted = computeWithRepository(repository -> deleteAndCount(ids, repository));
        System.out.println(String.format("Successfully deleted: %d outcomes", deleted));
    }

    private Integer deleteAndCount(List<Long> ids, OutcomesRepository repository) {
        return ids.stream()
                .map(repository::delete)
                .mapToInt(b -> b ? 1 : 0)
                .sum();
    }

}
