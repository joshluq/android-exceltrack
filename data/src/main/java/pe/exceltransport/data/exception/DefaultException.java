package pe.exceltransport.data.exception;

public class DefaultException extends Exception {

    private final int code;

    public DefaultException(int code) {
        this.code = code;
    }

    public DefaultException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public enum Codes {
        NO_INTERNET(0),
        UNAUTHORIZED(401),
        TIME_OUT(1000),
        DEFAULT_ERROR(1001),
        INVALID_CREDENTIALS(1002);

        private int code;

        Codes(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

}
