package Nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NonBlockingPDFReaderNIO {
    private static final int BUFFER_SIZE = 1024; // Read 1KB at a time
    private static final int SLEEP_TIME = 2000; // 2 seconds delay between processing chunks

    public static void main(String[] args) {
        File file = new File("C:\\Users\\EliabIshimwe\\Downloads\\Rich Dad Poor Dad.pdf");

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        try (FileInputStream fileInputStream = new FileInputStream(file);
             FileChannel fileChannel = fileInputStream.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            while (fileChannel.read(buffer) > 0) {
                buffer.flip(); // Prepare the buffer for reading

                // Copy buffer contents to a string and submit the processing task
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                String chunk = new String(bytes, StandardCharsets.UTF_8);

                // Submit the chunk processing task to the executor
                executorService.submit(() -> processChunk(chunk));

                // Sleep for 2 seconds to simulate non-blocking I/O
                Thread.sleep(SLEEP_TIME);

                buffer.clear(); // Prepare the buffer for the next read
            }

            // Shut down the executor after processing all chunks
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.MINUTES);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Process the chunk asynchronously
    private static void processChunk(String chunk) {
        System.out.println("Processing chunk:");
        System.out.println(chunk);
        System.out.println("----- End of chunk -----\n");
    }
}
