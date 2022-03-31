package commands;

import picocli.CommandLine.Command;

@Command (
        name = "build"
)
public class BuildCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("build");
    }
}
