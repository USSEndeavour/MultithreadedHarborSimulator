package port.exceptions;

// Custom exception class for situations where an underflow of containers occurs.
public class ContainerUnderflowException extends Exception {

    // Default constructor - creates a ContainerUnderflowException without any message or cause.
    public ContainerUnderflowException() {
        super(); // Invoke the superclass constructor.
    }

    // Constructor that accepts a message - creates an exception with a detailed message.
    public ContainerUnderflowException(String message) {
        super(message); // Call the superclass constructor with the provided message.
    }

    // Constructor that accepts a cause - creates an exception with a cause but no detailed message.
    public ContainerUnderflowException(Throwable cause) {
        super(cause); // Call the superclass constructor with the provided cause.
    }

    // Constructor that accepts both a message and a cause.
    public ContainerUnderflowException(String message, Throwable cause) {
        super(message, cause); // Call the superclass constructor with the provided message and cause.
    }
}
