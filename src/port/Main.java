package port;
import port.concurrent.DockPool;
import port.concurrent.DockClient;
import port.models.AbstractDock;
import port.models.Port;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int NUM_OF_CLIENTS = 20; // Define the number of dock clients

    public static void main(String[] args) {


        // Prepare the port with its docks
        Port port = new Port();
        DockPool<AbstractDock> pool = port.getDockPool(); // Retrieve the pool of docks

        // Create an ExecutorService to manage threads for dock clients
        ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_CLIENTS);

        // Start dock clients as separate tasks
        for (int i = 0; i < NUM_OF_CLIENTS; i++) {
            executor.execute(new DockClient(pool)); // Execute a new DockClient task
        }

        // Initiating shutdown sequence for the ExecutorService
        executor.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait for existing tasks to terminate
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Force shutdown if tasks did not finish in time
            }
        } catch (InterruptedException ie) {
            // In case the current thread is interrupted while waiting
            executor.shutdownNow(); // Force shutdown
            // Preserve interrupt status of the thread
            Thread.currentThread().interrupt();
        }
    }
}
