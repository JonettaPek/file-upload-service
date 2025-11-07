package fileupload.beanconfig;

import fileupload.validation.FileExtensionValidator;
import fileupload.validation.FileSizeValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class ValidatorConfig {

    @Bean
    public FileSizeValidator fileSizeValidator(@Value("${app.file-upload.max-file-size}") long MAX_FILE_SIZE) {
        return new FileSizeValidator(MAX_FILE_SIZE);
    }

    @Bean
    public FileExtensionValidator fileExtensionValidator(@Value("${app.file-upload.allowed-extensions}") Set<String> ALLOWED_EXTENSIONS) {
        return new FileExtensionValidator(ALLOWED_EXTENSIONS);
    }
}
