package port.concurrent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import port.models.AbstractDock;
import port.models.Port;
import port.exceptions.ResourceException;

public class DockPool<T extends AbstractDock> {
    // The maximum number of docks that can be simultaneously allocated.
    private static final int MAX_AVAILABLE = Port.DOCK_NUMBER;
    // Semaphore to control access to the docks.
    private Semaphore semaphore = new Semaphore(MAX_AVAILABLE, true);
    // The list of dock resources managed by this pool.
    private List<T> resources;

    // Constructor to create a pool with a list of dock resources.
    public DockPool(List<T> source) {
        resources = new ArrayList<>(source); // Initialize the resources list with the given source list.
    }

    // Method to get a resource from the pool, waiting for a maximum time if necessary.
    public T getResource(DockClient dockClient, long maxWaitMillis) throws ResourceException {
        try {
            // Try to acquire a permit from the semaphore within the given waiting time.
            if (semaphore.tryAcquire(maxWaitMillis, TimeUnit.SECONDS)) {
                for (T resource : resources) {
                    // Check if the current resource is not busy.
                    if (!resource.isBusy()) {
                        resource.setBusy(true); // Mark the resource as busy.
                        System.out.println("DockClient #" + dockClient.getId() + " took dock " + resource);
                        return resource; // Return the acquired resource.
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Handle the InterruptedException.
        }
        // If a resource is not acquired, throw a ResourceException.
        throw new ResourceException("Could not acquire a dock within " + maxWaitMillis + " milliseconds.");
    }

    // Method to release a resource back to the pool.
    public void releaseResource(DockClient dockClient, T resource) {
        resource.setBusy(false); // Mark the resource as not busy.
        System.out.println("DockClient #" + dockClient.getId() + ": " + resource + " --> released");
        semaphore.release(); // Release the permit back to the semaphore.
    }

    // Static method to get the maximum number of docks available in the pool.
    public static int getMaxAvailable() {
        return MAX_AVAILABLE;
    }
}
