package fileupload.validation;

import org.springframework.web.multipart.MultipartFile;

public interface FileValidator {
    void validate(MultipartFile file) throws RuntimeException;
}
