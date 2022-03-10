import picocli.CommandLine;

@CommandLine.Command(
        name = "dillab1",
        subcommands = {
                NewCommand.class,
                CleanCommand.class,
                BuildCommand.class,
                ServeCommand.class,
        },
        description = "Application DIL Labo 1")
public class Main implements Runnable{
    @CommandLine.Spec
    CommandLine.Model.CommandSpec spec;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        throw new CommandLine.ParameterException(spec.commandLine(), "Specify a subcommand");
    }
}
