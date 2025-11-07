package fileupload.validation.exception;

public class MissingFileExtensionException extends BaseException {
    public MissingFileExtensionException(String developerMessage, String userMessage, String throwingClassName) {
        super(developerMessage, userMessage, throwingClassName);
    }
}
