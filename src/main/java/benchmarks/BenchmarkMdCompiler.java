package benchmarks;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Setup;

@BenchmarkMode(Mode.AverageTime)
public class BenchmarkMdCompiler {

    public static void main(String[] args) {

    }

    @Setup
    public void init() {
        // Create file with md
        // Call init command
    }

    @Benchmark
    public void compileMarkdown {
        // Call build command
    }
}
