package fileupload.controller;

import fileupload.model.ApiResponse;
import fileupload.model.FileStatistics;
import fileupload.service.FileProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "*")
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class.getName());

    @Autowired
    private FileProcessingService fileProcessingService;

    @PostMapping(value = "/upload")
    public ResponseEntity<ApiResponse<FileStatistics>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        logger.info("Received file upload request for file '{}'", file.getOriginalFilename());

         FileStatistics fileStatistics = fileProcessingService.uploadFile(file);

        logger.info("Successfully uploaded file '{}'", file.getOriginalFilename());
        return ResponseEntity
            .created(URI.create(String.format("/upload/%s",fileStatistics.getFileId())))
            .body(new ApiResponse<>(
                HttpStatus.CREATED.value(),
                fileStatistics
            ));
    }

    @GetMapping(value = "/upload/{fileId}")
    public ResponseEntity<ApiResponse<FileStatistics>> getFileById(@PathVariable("fileId") String fileId) {
        logger.info("Received file statistics fetch request for file id '{}'", fileId);
        FileStatistics fileStatistics = this.fileProcessingService.fetchFileStatistics(fileId);
        logger.info("Successfully fetched file statistics for file id '{}'", fileId);
        return ResponseEntity
            .ok()
            .body(new ApiResponse<>(
                HttpStatus.OK.value(),
                fileStatistics
            ));
    }
}
