package commands;

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
    private static final String DIR_PATH = "." + File.separator + "test" + File.separator;

    private static final int NB_WARMUP_IT = 3;
    private static final int NB_MEASURE_IT = 10;

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

    /**
     * Deletes the build folder.
     */
    public static void deleteBuildFolder() {
        File buildFolder = new File(DIR_PATH + "build");
        if (buildFolder.exists()) {
            deleteDir(buildFolder);
        }
    }

    /**
     * Recursively delets a given file or directory.
     * @param dirFile the file or directory to delete
     */
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
     * Benchmark for build command.
     */
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Warmup(iterations = NB_WARMUP_IT)
    @Measurement(iterations = NB_MEASURE_IT)
    public void buildBenchmark() {
        deleteBuildFolder();

        CommandLine cmd = new CommandLine(app);
        final String testFolder = DIR_PATH;

        int exitCode = cmd.execute("build", testFolder);
    }

    /**
     * Benchmark for init command.
     */
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Warmup(iterations = NB_WARMUP_IT)
    @Measurement(iterations = NB_MEASURE_IT)
    public void initBenchmark() {
        final String testPath = DIR_PATH;

        CommandLine cmd = new CommandLine(app);
        int exitCode = cmd.execute("init", testPath);
    }
}
