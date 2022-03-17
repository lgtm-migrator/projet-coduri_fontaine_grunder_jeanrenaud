import picocli.CommandLine.Command;

@Command (
        name = "clean"
)
public class CleanCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("clean");
    }
}
