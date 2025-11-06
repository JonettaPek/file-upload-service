package fileupload.processor;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileProcessor<T> {
    T processFile(MultipartFile file) throws IOException;
}
