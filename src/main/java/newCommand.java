import picocli.CommandLine.Command;

@Command(
        name = "new"
)
public class newCommand implements Runnable{
    @Override
    public void run() {
        System.out.println("new");
    }
}
