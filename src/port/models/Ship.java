package port.models;
import port.exceptions.ContainerUnderflowException;
import port.exceptions.ContainerOverflowException;

/**
 * This class represents a ship that can carry a number of containers.
 * It provides methods to load and unload these containers,
 * ensuring that the capacity limits are adhered to.
 */
public class Ship {
    private int id; // Unique identifier for the ship
    private final int MAX_CAPACITY = 5; // Maximum number of containers the ship can carry
    private int currentCapacity; // Current number of containers on the ship

    /**
     * Constructor for creating a new Ship.
     * @param id the unique identifier for the ship
     * @param currentCapacity the initial number of containers on the ship
     */
    public Ship(int id, int currentCapacity) {
        this.id = id;
        this.currentCapacity = currentCapacity;
    }

    /**
     * Gets the unique identifier of the ship.
     * @return the ship's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the current number of containers on the ship.
     * @return the current capacity of the ship
     */
    public int getCurrentCapacity() {
        return currentCapacity;
    }

    /**
     * Gets the maximum capacity of containers that the ship can carry.
     * @return the maximum capacity of the ship
     */
    public int getMAX_CAPACITY() {
        return MAX_CAPACITY;
    }

    /**
     * Loads a single container onto the ship.
     * @throws ContainerOverflowException if the ship is already at maximum capacity
     */
    public void loadContainer() throws ContainerOverflowException {
        if (currentCapacity >= MAX_CAPACITY)
            throw new ContainerOverflowException("the Ship#" + id +
                    " is fully loaded: MAX_CAPACITY = " + MAX_CAPACITY);

        this.currentCapacity += 1;
    }

    /**
     * Unloads a single container from the ship.
     * @throws ContainerUnderflowException if the ship has no containers to unload
     */
    public void unloadContainer() throws ContainerUnderflowException {
        if (currentCapacity <= 0)
            throw new ContainerUnderflowException("the Ship#" + id + " has no containers.");

        this.currentCapacity -= 1;
    }

    /**
     * Unloads all containers from the ship.
     * @throws ContainerUnderflowException if the ship has no containers to unload
     */
    public void unloadShip() throws ContainerUnderflowException {
        if (currentCapacity <= 0)
            throw new ContainerUnderflowException("the Ship#" + id + " has no containers.");
        this.currentCapacity = 0;
    }

    /**
     * Provides a string representation of the ship and its current container capacity.
     * @return a string detailing the ship's ID and its current number of containers
     */
    public String toString() {
        return "Ship #" + id + " has " + currentCapacity + " containers";
    }
}
