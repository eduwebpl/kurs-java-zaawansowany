package pl.eduweb.java.advanced.outcome.cli;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import pl.eduweb.java.advanced.outcome.cli.subcommand.*;

@Command(
        name = "outcome-registry",
        mixinStandardHelpOptions = true,
        version = "Outcome registry 1.0",
        subcommands = {
                Add.class,
                List.class,
                Update.class,
                Delete.class,
                Sum.class,
                Import.class,
                Export.class
        })
public class App implements Runnable {

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new App());
        commandLine.parseWithHandler(new CommandLine.RunLast(), args);
    }

    @Override
    public void run() {
        CommandLine.usage(this, System.err);
    }

}
