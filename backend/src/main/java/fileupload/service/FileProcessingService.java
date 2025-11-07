package fileupload.service;

import fileupload.mapper.FileMapper;
import fileupload.model.FileStatistics;
import fileupload.processor.FileStatisticsProcessor;
import fileupload.repository.InMemoryDatabase;
import fileupload.validation.FileExtensionValidator;
import fileupload.validation.FileSizeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileProcessingService {

    private static final Logger logger = LoggerFactory.getLogger(FileProcessingService.class);

    @Autowired
    private FileExtensionValidator fileExtensionValidator;

    @Autowired
    private FileSizeValidator fileSizeValidator;

    @Autowired
    private FileStatisticsProcessor fileStatisticsProcessor;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private InMemoryDatabase inMemoryDatabase;

    public FileStatistics uploadFile(MultipartFile file) throws RuntimeException, IOException {
        this.validateFile(file);
        FileStatistics fileStatistics = this.processFile(file);
        this.saveFileStatistics(fileStatistics);
        return fileStatistics;
    }

    private void validateFile(MultipartFile file) throws RuntimeException {
        this.fileExtensionValidator.validate(file);
    }

    private FileStatistics processFile(MultipartFile file) throws IOException {
        return this.fileStatisticsProcessor.processFile(file);
    }

    private void saveFileStatistics(FileStatistics fileStatistics) {
        this.inMemoryDatabase.save(this.fileMapper.modelToEntity(fileStatistics));
    }

    public FileStatistics fetchFileStatistics(String fileId) {
        return this.fileMapper.entityToModel(this.inMemoryDatabase.findByFileId(fileId));
    }
}
