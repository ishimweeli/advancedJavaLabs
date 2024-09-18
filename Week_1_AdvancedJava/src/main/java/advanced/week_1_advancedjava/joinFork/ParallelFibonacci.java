package advanced.week_1_advancedjava.joinFork;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class ParallelFibonacci {
    static class Fibonacci extends RecursiveTask<Integer> {
        final int n;

        Fibonacci(int n) {
            this.n = n;
        }
        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }

            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);

            return f2.compute() + f1.join();
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int n = 40; // Random Fibonacci number to calculate
        Fibonacci task = new Fibonacci(n);
        int result = pool.invoke(task);
        System.out.println("Fibonacci(" + n + ") = " + result);
    }
}
