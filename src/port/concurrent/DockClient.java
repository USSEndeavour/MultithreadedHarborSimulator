package port.concurrent;
import java.util.Random;
import port.models.AbstractDock;
import port.models.Ship;

public class DockClient implements Runnable {
    private int id;
    private DockPool<AbstractDock> pool; // Dock pool from which this client will request docks.
    Ship ship;
    public Thread t;
    Random rand = new Random();

    // Constructor for creating a new dock client with a specific dock pool.
    public DockClient(DockPool<AbstractDock> pool) {
        id = rand.nextInt(1000); // Assign a random unique ID to this dock client.
        int currentCapacity = rand.nextInt(10); // Assign a random capacity to this dock client's ship.
        t = new Thread(this, "DockClient");
        ship = new Ship(id, currentCapacity);
        this.pool = pool;
    }

    // Getter method to retrieve the ID of the dock client.
    public int getId() {
        return id;
    }

    // The main execution method for the dock client thread.
    @Override
    public void run() {
        AbstractDock dock = null;
        try {
            dock = pool.getResource(this, 8); // Try to acquire a dock from the pool with a maximum wait time.
            dock.using(ship); // Utilize the dock for ship operations such as loading or unloading.
        } catch (IndexOutOfBoundsException e) {
            // This exception is thrown if the operation on the ship is not permissible (like unloading from an empty ship).
            System.out.println(e.getMessage());
        } catch (Exception e) {
            // General catch block for any other exceptions that occur during dock usage.
            System.err.println("DockClient #" + this.getId() + " encountered an issue -> " + e.getMessage());
        } finally {
            // Finally block ensures that the dock is released back to the pool even if an exception is thrown.
            if (dock != null) {
                pool.releaseResource(this, dock);
            }
        }
    }
}
