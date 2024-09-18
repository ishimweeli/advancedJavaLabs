package JMM;

public class VolatileFlagExample {
    static class Flag {
        private volatile boolean stop = false;

        public void setStop() {
            stop = true;
        }

        public boolean shouldStop() {
            return stop;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Flag flag = new Flag();

        Thread workerThread = new Thread(() -> {
            int count = 0;
            while (!flag.shouldStop()) {
                count++;
            }
            System.out.println("Worker thread stopped. Count: " + count);
        });

        workerThread.start();

        Thread.sleep(1000); // Let the worker thread run for 1 second

        flag.setStop();
        workerThread.join();

        System.out.println("Main thread finished.");
    }
}