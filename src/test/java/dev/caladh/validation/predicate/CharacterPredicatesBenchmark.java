package dev.caladh.validation.predicate;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

@BenchmarkMode(Mode.AverageTime)
@Fork(warmups = 1, value = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Threads(1)
@Warmup(iterations = 3, time = 10, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 10, timeUnit = TimeUnit.MILLISECONDS)
public class CharacterPredicatesBenchmark {

    @Param({"10", "1000", "1000000"})
    public int iterations;

    private Predicate<Character> predicate;

    @Setup(Level.Iteration)
    public void setup(final Params params) {
        predicate = CharacterPredicates.equalsTo(params.base).or(CharacterPredicates.equalsTo(params.alternate));
    }

    @Test
    public void benchmark() throws IOException {
        String[] argv = {};
        org.openjdk.jmh.Main.main(argv);
    }

    @Benchmark
    public void statement_correct(final Blackhole blackhole, final Params params) {
        char value = params.correct;
        boolean result = params.base == value || params.alternate == value;
        blackhole.consume(result);
    }

    @Benchmark
    public void predicate_correct(final Blackhole blackhole, final Params params) {
        boolean result = predicate.test(params.correct);
        blackhole.consume(result);
    }

    @Benchmark
    public void statement_alt_correct(final Blackhole blackhole, final Params params) {
        char value = params.correctAlt;
        boolean result = params.base == value || params.alternate == value;
        blackhole.consume(result);
    }

    @Benchmark
    public void predicate_alt_correct(final Blackhole blackhole, final Params params) {
        boolean result = predicate.test(params.correctAlt);
        blackhole.consume(result);
    }

    @Benchmark
    public void statement_incorrect(final Blackhole blackhole, final Params params) {
        char value = params.incorrect;
        boolean result = params.base == value || params.alternate == value;
        blackhole.consume(result);
    }

    @Benchmark
    public void predicate_incorrect(final Blackhole blackhole, final Params params) {
        boolean result = predicate.test(params.incorrect);
        blackhole.consume(result);
    }

    @State(Scope.Thread)
    public static class Params {

        public char base = 'a';
        public char alternate = (char)(base + 1);
        public char correct = base;
        public char correctAlt = alternate;
        public char incorrect = (char)(base + 2);
    }
}
