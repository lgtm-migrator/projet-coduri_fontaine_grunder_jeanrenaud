import picocli.CommandLine.Command;

@Command(
        name = "new"
)
public class NewCommand implements Runnable{
    @Override
    public void run() {
        System.out.println("new");
    }
}
