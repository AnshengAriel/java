package com.ariel.javabase.lambda;

import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    public void of() {
        Stream.of(1, 2, 3, 4, 5).forEach(this::print);
    }

    @Test
    public void generate1() {
        Stream.generate(this::random).limit(10).forEach(this::print);
    }

    @Test
    public void generate2() {
        LongStream.generate(new FibonacciSupplier()).limit(10).forEach(this::print);
        LongStream.generate(new FibonacciSupplier()).limit(10).forEach(this::print);
    }

    @Test
    public void generate3() throws URISyntaxException, IOException {
        Stream<String> lines = Files.lines(Paths.get(this.getClass().getResource("lambda-stream-test-generate3.txt").toURI()));
        lines.forEach(System.out::println);
    }

    @Test
    public void reduce() {
        OptionalInt optional = IntStream.generate(new NatureSupplier())
                .limit(100).reduce(NatureSupplier::sum);
        System.out.println(optional.orElse(0));
    }

    private void print(int i) {
        System.out.println(i);
    }

    private void print(long i) {
        System.out.println(i);
    }

    private Integer random() {
        return new Random().nextInt();
    }

}
