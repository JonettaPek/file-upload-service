package fileupload.validation;

import fileupload.validation.exception.EmptyFileException;
import fileupload.validation.exception.MaxFileSizeExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

// TODO: To delete as not needed since Spring Boot handles it by default
public class FileSizeValidator implements FileValidator{

    private static final Logger logger = LoggerFactory.getLogger(FileSizeValidator.class.getName());

    private final long MAX_FILE_SIZE;

    public FileSizeValidator(final long MAX_FILE_SIZE) {
        this.MAX_FILE_SIZE = MAX_FILE_SIZE;
    }

    @Override
    public void validate(MultipartFile file) throws RuntimeException {
        logger.info("Starting file size validation");
        if (file == null || file.isEmpty()) {
            throw new EmptyFileException(
                    "File size validation failed: file is null or file content is empty.",
                    "Please provide a file with content.",
                    FileSizeValidator.class.getName()
            );
        }

        long fileSize = file.getSize();
        if (fileSize > MAX_FILE_SIZE) {
            String errorMessage = String.format(
                    "File size validation failed: File size %d bytes exceeds maximum allowed size of %d bytes.",
                    fileSize, MAX_FILE_SIZE
            );
            throw new MaxFileSizeExceededException(
                errorMessage,
                String.format("Please provide a file size smaller than %d bytes.", MAX_FILE_SIZE),
                FileSizeValidator.class.getName()
            );
        }

        logger.info("File size validation passed: file size {} bytes is within the allowed limit.", fileSize);
        logger.info("Completed file size validation");
    }
}