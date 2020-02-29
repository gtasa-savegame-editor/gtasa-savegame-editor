package nl.paulinternet.libsavegame.exceptions;

/**
 * Exception that is thrown in case a value supplied to a
 * {@link nl.paulinternet.libsavegame.variables.Variable} is not valid.
 */
public class InvalidValueException extends RuntimeException {

    /**
     * Constructor featuring a message and a {@link Throwable} cause.
     *
     * @param message a message explaining why the value is invalid
     * @param cause   any {@link Throwable} connected to the value being invalid
     */
    public InvalidValueException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor featuring a message.
     *
     * @param message a message explaining why the value is invalid
     */
    public InvalidValueException(String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @deprecated it's better to give a reason why the value is invalid
     */
    @Deprecated
    public InvalidValueException() {
        super("Invalid value!");
    }
}
