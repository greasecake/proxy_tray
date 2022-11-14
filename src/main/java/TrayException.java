public class TrayException extends RuntimeException {
    public TrayException(String message) {
        super(message);
    }
    public TrayException(Exception e) {
        super(e);
    }
    public TrayException(String message, Exception e) {
        super(message, e);
    }
}
