package za.co.sabs.planningtool.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordNotFoundException extends RuntimeException {
    private String message;

    public RecordNotFoundException(String message) {
        this.message = message;
    }

    public RecordNotFoundException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public RecordNotFoundException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public RecordNotFoundException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    public RecordNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
    }
}
