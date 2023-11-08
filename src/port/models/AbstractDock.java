package port.models;
import port.exceptions.ContainerOverflowException;
import port.exceptions.ContainerUnderflowException;

/**
 * This abstract class represents a dock within a port.
 * It holds information about whether the dock is currently in use (busy)
 * and provides an abstract method for handling a ship at the dock.
 */
public abstract class AbstractDock {
    private volatile boolean busy; // indicates if the dock is currently being used

    /**
     * Checks if the dock is busy.
     * @return true if the dock is in use, false otherwise.
     */
    public boolean isBusy() {
        return busy;
    }

    /**
     * Sets the dock's busy status.
     * @param busy the new busy status to be set.
     */
    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    /**
     * An abstract method to be implemented by subclasses to define how a ship is handled
     * when it uses the dock. This method could involve unloading or loading containers,
     * refueling the ship, or any other process relevant to the dock's function.
     *
     * @param ship The ship to be handled at the dock.
     * @throws ContainerUnderflowException If the operation would result in fewer than zero containers.
     * @throws ContainerOverflowException If the operation would exceed the maximum number of containers.
     */
    public abstract void using(Ship ship) throws ContainerUnderflowException, ContainerOverflowException;
}
