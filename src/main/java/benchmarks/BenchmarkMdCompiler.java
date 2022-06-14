package benchmarks;

import org.openjdk.jmh.annotations.*;
import picocli.CommandLine;
import websitebuilder.App;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(1)
public class BenchmarkMdCompiler {
    @Param({ "100", "200", "300", "500", "1000" })
    private int iterations;

    /**
     *
     * @param args
     */
    public static void main(final String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }



    public void init() {
        File buildFolder = new File("." + File.separator + "test" + File.separator + "build");
        if (buildFolder.exists()) {
            try {
                deleteDir(buildFolder);
            } catch (Exception e) {
                System.out.println("problème ici");
            }

        }
    }

    public static void deleteDir(final File dirFile) {


        if (dirFile.isDirectory()) {
            File[] dirs = dirFile.listFiles();
            for (File dir: dirs) {
                deleteDir(dir);
            }
        }
        dirFile.delete();
    }

    /**
     *
     */
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Warmup(iterations = 3)
    @Measurement(iterations = 10)
    public void buildBenchmark() {
        init();

        App app = new App();
        CommandLine cmd = new CommandLine(app);
        final String testFolder = "." + File.separator + "test";

        int exitCode = cmd.execute("build", testFolder);
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Warmup(iterations = 3)
    @Measurement(iterations = 10)
    public void initBenchmark() {
        final String testPath = "." + File.separator + "test";

        App app = new App();
        CommandLine cmd = new CommandLine(app);
        int exitCode = cmd.execute("init", testPath.toString());
    }
}
