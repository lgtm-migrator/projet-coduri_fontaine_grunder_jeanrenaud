package websitebuilder;

import commands.BuildCommand;
import commands.CleanCommand;
import commands.NewCommand;
import commands.ServeCommand;
import commands.VersionCommand;
import commands.InitCommand;
import picocli.CommandLine;

@CommandLine.Command(
        name = "dillab1",
        subcommands = {
                NewCommand.class,
                CleanCommand.class,
                BuildCommand.class,
                ServeCommand.class,
                VersionCommand.class,
                InitCommand.class,
        },
        description = "Application DIL Labo 1")
public class App implements Runnable {
    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

    /**
     * Main method.
     * @param args command line arguments.
     */
    public static void main(final String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        throw new CommandLine.ParameterException(spec.commandLine(), "Specify a subcommand");
    }
}
