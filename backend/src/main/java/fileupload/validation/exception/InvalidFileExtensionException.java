package fileupload.validation.exception;

public class InvalidFileExtensionException extends BaseException {
    public InvalidFileExtensionException(String developerMessage, String userMessage, String throwingClassName) {
        super(developerMessage, userMessage, throwingClassName);
    }
}
