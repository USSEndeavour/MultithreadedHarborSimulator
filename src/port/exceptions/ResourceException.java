package port.exceptions;

// Custom exception class to indicate problems related to resource allocation and management.
public class ResourceException extends Exception {

    // Default constructor - creates a ResourceException with no message or cause.
    public ResourceException() {
        super(); // Invoke the superclass constructor.
    }

    // Constructor that accepts a message - allows for the creation of an exception with a descriptive message.
    public ResourceException(String message) {
        super(message); // Call the superclass constructor with the provided message.
    }

    // Constructor that accepts a cause - enables the creation of an exception that passes on the underlying reason for the exception.
    public ResourceException(Throwable cause) {
        super(cause); // Call the superclass constructor with the provided cause.
    }

    // Constructor that accepts both a message and a cause - allows for detailed messages and chaining exceptions for better context.
    public ResourceException(String message, Throwable cause) {
        super(message, cause); // Call the superclass constructor with both the provided message and cause.
    }
}
