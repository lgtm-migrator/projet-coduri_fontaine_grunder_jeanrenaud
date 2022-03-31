package init;

import picocli.CommandLine.Command;

@Command (  name = "init", mixinStandardHelpOptions = true, version = "init 1.0",
            description = "Initialize a static website in the designed folder." )
public class InitCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("init");
    }
}

