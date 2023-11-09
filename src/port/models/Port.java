package port.models;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import port.concurrent.DockPool;
import port.exceptions.ContainerOverflowException;
import port.exceptions.ContainerUnderflowException;

/**
 * This class represents a port that contains docks for ships to unload containers.
 * It manages the docks and their availability for ships to use.
 * @param <T> The type of dock that the port manages, which must extend AbstractDock.
 */
public class Port<T extends AbstractDock> {
    private final int MAX_PORT_CAPACITY = 50; // The maximum number of containers the port can handle.
    public static final int DOCK_NUMBER = 4; // The number of docks available in the port.
    private volatile int currentCapacity; // The current number of containers in the port.
    private Random rand; // Random generator for initializing current capacity.
    private DockPool<Dock> dockPool; // The pool managing the docks.
    private List<Dock> docks; // The list of docks.

    /**
     * Constructs a port and initializes its current capacity randomly.
     */
    public Port() {
        rand = new Random();
        currentCapacity = rand.nextInt(20); // Initialize to a random capacity at startup.
    }

    /**
     * This inner class represents a dock in the port.
     */
    public class Dock extends AbstractDock {
        private int id; // The identifier for the dock.

        // Inherited method to check if the dock is busy.
        @java.lang.Override
        public boolean isBusy() {
            return super.isBusy();
        }

        /**
         * Constructs a dock with a specified identifier.
         * @param id The identifier for the dock.
         */
        public Dock(int id) {
            this.id = id;
        }

        // Gets the identifier of the dock.
        public int getId() {
            return id;
        }

        // Sets the identifier of the dock.
        public void setId(int id) {
            this.id = id;
        }

        // Handles the process of using the dock by a ship, specifically unloading containers.
        @java.lang.Override
        public void using(Ship ship) throws ContainerUnderflowException, ContainerOverflowException {
            System.out.println("Unloading Ship#" + ship.getId() + " ...");

            for (int i = 0; i < ship.getCurrentCapacity(); i++) {
                System.out.println("Unloading container # " + i + "\n");
                ship.unloadContainer();
                addContainer(); // Add the container to the port's capacity.

                try {
                    TimeUnit.SECONDS.sleep(1); // Simulate time taken to unload one container.
                } catch (InterruptedException e) {
                    e.printStackTrace(); // Handle the interrupted exception.
                }
            }

            System.out.println("Ship#" + ship.getId() + " unloaded.");
        }

        // Represents the dock information as a String.
        public String toString() {
            StringBuilder sb = new StringBuilder("Dock{");
            sb.append("id=").append(id).append(", busy=").append(isBusy()).append('}');
            return sb.toString();
        }
    }

    /**
     * Adds a container to the port's current capacity, ensuring the capacity does not exceed the maximum.
     * @throws ContainerOverflowException If adding a container exceeds the port's maximum capacity.
     */
    private synchronized void addContainer() throws ContainerOverflowException {
        if (currentCapacity == MAX_PORT_CAPACITY) {
            throw new ContainerOverflowException("The port storage is full of containers");
        }
        this.currentCapacity++; // Increment the port's container count.
    }

    /**
     * Initializes the dock pool with docks and returns it for use.
     * @return The initialized dock pool.
     */
    public DockPool getDockPool() {
        docks = new ArrayList<>();
        for (int i = 1; i <= DOCK_NUMBER; i++) {
            docks.add(new Dock(i)); // Add new docks to the list.
        }
        dockPool = new DockPool<>(docks); // Create a new DockPool with the list of docks.
        return dockPool;
    }
}
