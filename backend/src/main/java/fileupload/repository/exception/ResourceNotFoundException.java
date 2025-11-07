package fileupload.repository.exception;

import fileupload.validation.exception.BaseException;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends BaseException {
    private final Object resource;
    public ResourceNotFoundException(String developerMessage, String userMessage, String throwingClassName, Object resource) {
        super(developerMessage, userMessage, throwingClassName);
        this.resource = resource;
    }
}
