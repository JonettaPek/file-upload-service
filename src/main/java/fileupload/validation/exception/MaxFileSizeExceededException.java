package fileupload.validation.exception;

public class MaxFileSizeExceededException extends BaseException {
    public MaxFileSizeExceededException(String developerMessage, String userMessage, String throwingClassName) {
        super(developerMessage, userMessage, throwingClassName);
    }
}
