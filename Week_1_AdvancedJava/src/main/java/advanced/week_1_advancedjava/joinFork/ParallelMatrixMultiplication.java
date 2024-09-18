package advanced.week_1_advancedjava.joinFork;


import java.util.concurrent.ForkJoinPool;
import java.util.Random;
import java.util.concurrent.RecursiveTask;

public class ParallelMatrixMultiplication {
    static class MultiplyTask extends RecursiveTask<int[][]> {
        private final int[][] A;
        private final int[][] B;
        private final int rowStart;
        private final int rowEnd;
        private final int colStart;
        private final int colEnd;
        private static final int THRESHOLD = 64;

        MultiplyTask(int[][] A, int[][] B, int rowStart, int rowEnd, int colStart, int colEnd) {
            this.A = A;
            this.B = B;
            this.rowStart = rowStart;
            this.rowEnd = rowEnd;
            this.colStart = colStart;
            this.colEnd = colEnd;
        }

        @Override
        protected int[][] compute() {
            if (rowEnd - rowStart <= THRESHOLD || colEnd - colStart <= THRESHOLD) {
                return multiplySequentially();
            } else {
                int rowMid = (rowStart + rowEnd) / 2;
                int colMid = (colStart + colEnd) / 2;

                MultiplyTask topLeft = new MultiplyTask(A, B, rowStart, rowMid, colStart, colMid);
                MultiplyTask topRight = new MultiplyTask(A, B, rowStart, rowMid, colMid, colEnd);
                MultiplyTask bottomLeft = new MultiplyTask(A, B, rowMid, rowEnd, colStart, colMid);
                MultiplyTask bottomRight = new MultiplyTask(A, B, rowMid, rowEnd, colMid, colEnd);

                invokeAll(topLeft, topRight, bottomLeft, bottomRight);

                return combineResults(topLeft.join(), topRight.join(), bottomLeft.join(), bottomRight.join());
            }
        }

        private int[][] multiplySequentially() {
            int[][] result = new int[rowEnd - rowStart][colEnd - colStart];
            for (int i = rowStart; i < rowEnd; i++) {
                for (int j = colStart; j < colEnd; j++) {
                    int sum = 0;
                    for (int k = 0; k < A[0].length; k++) {
                        sum += A[i][k] * B[k][j];
                    }
                    result[i - rowStart][j - colStart] = sum;
                }
            }
            return result;
        }

        private int[][] combineResults(int[][] topLeft, int[][] topRight, int[][] bottomLeft, int[][] bottomRight) {
            int rows = rowEnd - rowStart;
            int cols = colEnd - colStart;
            int[][] result = new int[rows][cols];

            for (int i = 0; i < topLeft.length; i++) {
                System.arraycopy(topLeft[i], 0, result[i], 0, topLeft[i].length);
                System.arraycopy(topRight[i], 0, result[i], topLeft[i].length, topRight[i].length);
            }
            for (int i = 0; i < bottomLeft.length; i++) {
                System.arraycopy(bottomLeft[i], 0, result[i + topLeft.length], 0, bottomLeft[i].length);
                System.arraycopy(bottomRight[i], 0, result[i + topLeft.length], bottomLeft[i].length, bottomRight[i].length);
            }

            return result;
        }
    }

    public static void main(String[] args) {
        int size = 1000;
        int[][] A = generateRandomMatrix(size, size);
        int[][] B = generateRandomMatrix(size, size);

        ForkJoinPool pool = new ForkJoinPool();
        MultiplyTask task = new MultiplyTask(A, B, 0, A.length, 0, B[0].length);
        int[][] result = pool.invoke(task);

        System.out.println("Matrix multiplication completed.");
        // Print a small portion of the result to verify
        for (int i = 0; i < 5 && i < result.length; i++) {
            for (int j = 0; j < 5 && j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] generateRandomMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }
        return matrix;
    }
}