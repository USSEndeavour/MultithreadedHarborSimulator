package port.exceptions;

// Custom exception class for situations where an overflow of containers occurs.
public class ContainerOverflowException extends Exception {

    // Default constructor - creates a ContainerOverflowException without any message or cause.
    public ContainerOverflowException() {
        super();
    }

    // Constructor that accepts a message - creates an exception with a detailed message.
    public ContainerOverflowException(String message) {
        super(message); // Call the superclass constructor with the provided message.
    }

    // Constructor that accepts a cause - creates an exception with a cause but no detailed message.
    public ContainerOverflowException(Throwable cause) {
        super(cause); // Call the superclass constructor with the provided cause.
    }

    // Constructor that accepts both a message and a cause.
    public ContainerOverflowException(String message, Throwable cause) {
        super(message, cause); // Call the superclass constructor with the provided message and cause.
    }
}
