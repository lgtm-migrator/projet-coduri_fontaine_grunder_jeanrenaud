import picocli.CommandLine.Command;

@Command (
        name = "serve"
)
public class ServeCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("serve");
    }
}
