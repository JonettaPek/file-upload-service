package fileupload.validation.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {
    private final String userMessage;
    private final String throwingClassName;

    public BaseException(String developerMessage, String userMessage, String throwingClassName) {
        super(developerMessage);
        this.userMessage = userMessage;
        this.throwingClassName = throwingClassName;
    }
}
