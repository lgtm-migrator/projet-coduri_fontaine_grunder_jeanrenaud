package benchmarks;

import org.openjdk.jmh.annotations.*;
import picocli.CommandLine;
import websitebuilder.App;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Benchmark for the init and build commands.
 *
 * @author Chloé Fontaine
 * @version 1.0
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(1)
public class BenchmarkMdCompiler {
    private App app;

    /**
     * Run the benchmark.
     * @param args
     */
    public static void main(final String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    /**
     * Benchmark setup.
     */
    @Setup
    public void setUp() {
        app = new App();
    }

    private void clearDirs() {
        File buildFolder = new File("." + File.separator + "test" + File.separator + "build");
        if (buildFolder.exists()) {
            try {
                deleteDir(buildFolder);
            } catch (Exception e) {
                System.out.println("problème ici");
            }

        }
    }

    private static void deleteDir(final File dirFile) {
        if (dirFile.isDirectory()) {
            File[] dirs = dirFile.listFiles();
            for (File dir: dirs) {
                deleteDir(dir);
            }
        }
        dirFile.delete();
    }

    /**
     * Benchmark for build command.
     */
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Warmup(iterations = 3)
    @Measurement(iterations = 10)
    public void buildBenchmark() {
        clearDirs();

        CommandLine cmd = new CommandLine(app);
        final String testFolder = "." + File.separator + "test";

        int exitCode = cmd.execute("build", testFolder);
    }

    /**
     * Benchmark for init command.
     */
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Warmup(iterations = 3)
    @Measurement(iterations = 10)
    public void initBenchmark() {
        final String testPath = "." + File.separator + "test";

        CommandLine cmd = new CommandLine(app);
        int exitCode = cmd.execute("init", testPath);
    }
}
