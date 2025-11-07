package fileupload.validation.exception;

public class EmptyFileException extends BaseException {
    public EmptyFileException(String developerMessage, String userMessage, String throwingClassName) {
        super(developerMessage, userMessage, throwingClassName);
    }
}
