package fileupload.controller;

import fileupload.model.ApiResponse;
import fileupload.model.FileStatistics;
import fileupload.service.FileProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

         FileStatistics fileStatistics = fileProcessingService.handleFile(file);

        logger.info("Completed file upload request for file '{}'", file.getOriginalFilename());
        return ResponseEntity
                .ok()
                .body(new ApiResponse<>(
                        true,
                        "Success",
                        fileStatistics
                ));
    }
}
