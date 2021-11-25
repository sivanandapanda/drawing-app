package com.assignment.draw;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class RandomTest {

    @Test
    void test() throws ExecutionException, InterruptedException {
        var integers = List.of(1, 2, 3);

        /*var answer = integers.parallelStream()
                .map(i -> i * i)
                .mapToInt(i -> i)
                .sum();*/

        /*var forkJoinPool = new ForkJoinPool(2);

        ForkJoinTask<Integer> squareRootTask = new ForkJoinTask<>() {

            int result;

            @Override
            public Integer getRawResult() {
                return result;
            }

            @Override
            protected void setRawResult(Integer value) {
                result = value;
            }

            @Override
            protected boolean exec() {
                result = result * result;
                return true;
            }
        };

        forkJoinPool.*/

        var executorService = Executors.newFixedThreadPool(4);

        var futures = integers.stream().map(i -> executorService.submit(() -> {
            var sleepTime = new Random().nextInt(10);
            System.out.printf("Sleep time for %s iteration is %s\n", i, sleepTime);
            TimeUnit.SECONDS.sleep(sleepTime);
          return i * i;
        } )).collect(Collectors.toList());

        int ans = 0;

        for (Future<Integer> future : futures) {
            ans += future.get();
        }

        System.out.println(ans);
    }

}
