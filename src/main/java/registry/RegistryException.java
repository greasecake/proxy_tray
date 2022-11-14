package registry;

public class RegistryException extends RuntimeException {
    public RegistryException(String message) {
        super(message);
    }
    public RegistryException(Exception e) {
        super(e);
    }
    public RegistryException(String message, Exception e) {
        super(message, e);
    }
}
