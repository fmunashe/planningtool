package za.co.sabs.planningtool.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordExistsException extends RuntimeException {
    private String message;

    public RecordExistsException(String message) {
        this.message = message;
    }

    public RecordExistsException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public RecordExistsException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public RecordExistsException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    public RecordExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
    }
}
