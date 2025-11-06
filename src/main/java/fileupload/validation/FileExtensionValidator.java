package fileupload.validation;

import fileupload.validation.exception.EmptyFileException;
import fileupload.validation.exception.InvalidFileExtensionException;
import fileupload.validation.exception.MissingFileExtensionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.stream.Collectors;

public class FileExtensionValidator implements FileValidator {

    private static final Logger logger = LoggerFactory.getLogger(FileExtensionValidator.class.getName());

    private final Set<String> ALLOWED_EXTENSIONS;

    public FileExtensionValidator(final Set<String> ALLOWED_EXTENSIONS) {
        this.ALLOWED_EXTENSIONS = ALLOWED_EXTENSIONS.stream()
                .map(String::toLowerCase)
                .map(ext -> ext.startsWith(".") ? ext : "." + ext)
                .collect(Collectors.toSet());
    }

    @Override
    public void validate(MultipartFile file) throws RuntimeException {
        logger.info("Starting file extension validation");
        if (file == null || file.isEmpty()) {
            throw new EmptyFileException(
                    "File extension validation failed: file is null or file content is empty.",
                    "Please provide a file with content.",
                    FileExtensionValidator.class.getName()
            );
        }

        String filename = file.getOriginalFilename();
        if (filename.trim().isEmpty()) {
            throw new EmptyFileException(
                "File extension validation failed: filename is empty.",
                "Please provide a file with valid filename.",
                FileExtensionValidator.class.getName()
            );
        }

        String fileExtension = parseFileExtension(filename);
        if (fileExtension.isEmpty()) {
            throw new MissingFileExtensionException(
                String.format("File extension validation failed: filename '%s' has no extension.", filename),
                "Please provide a filename with file extension.",
                FileExtensionValidator.class.getName()
            );
        }

        boolean isValid = ALLOWED_EXTENSIONS.contains(fileExtension.toLowerCase());

        if (!isValid) {
            String errorMessage = String.format(
                "Unsupported file extension '%s'. Allowed extensions are: %s",
                fileExtension.toLowerCase(), String.join(", ", ALLOWED_EXTENSIONS)
            );
            throw new InvalidFileExtensionException(
                "File extension validation failed: " + errorMessage,
                errorMessage,
                FileExtensionValidator.class.getName()
            );
        } else {
            logger.info("File extension validation passed: file '{}' has a valid extension '{}'.",
                filename, fileExtension.toLowerCase()
            );
        }
        logger.info("Completed file extension validation");
    }

    private String parseFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < filename.length() - 1) return filename.substring(dotIndex);
        return "";
    }
}
