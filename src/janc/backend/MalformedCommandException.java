package janc.backend;

public class MalformedCommandException extends Exception {
    /**
     * The Exception constructor
     *
     * @param message the description of the error. Will passed to the super constructor.
     */
    public MalformedCommandException(String message) {
        super(message);
    }
}
