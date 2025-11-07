package fileupload.handler;

import fileupload.controller.FileUploadController;
import fileupload.model.ApiResponse;
import fileupload.processor.FileStatisticsProcessor;
import fileupload.repository.exception.ResourceNotFoundException;
import fileupload.validation.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import java.io.IOException;

@RestControllerAdvice
public class FileUploadControllerExceptionHandler {

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<String>> handleMultipartException(MultipartException exception) {
        Logger fileUploadController = LoggerFactory.getLogger(FileUploadController.class);
        fileUploadController.error("File upload request is not a multipart request");

        return new ResponseEntity<>(
            new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Please provide a file"
            ),
            HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<String>> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception) {
        Logger fileUploadController = LoggerFactory.getLogger(FileUploadController.class);
        fileUploadController.error(exception.getMessage());

        return new ResponseEntity<>(
            new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Please upload a file smaller than 10MB."
            ),
            HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
        EmptyFileException.class,
        MissingFileExtensionException.class,
        InvalidFileExtensionException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<String>> handleValidationExceptions(BaseException exception) {
        Logger throwingClassLogger = LoggerFactory.getLogger(exception.getThrowingClassName());
        throwingClassLogger.error(exception.getMessage());

        return new ResponseEntity<>(
            new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                exception.getUserMessage()
            ),
        HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResponse<String>> handleIOExceptions(IOException exception) {
        Logger fileStatisticsProcessorLogger = LoggerFactory.getLogger(FileStatisticsProcessor.class);
        fileStatisticsProcessorLogger.error("File processing failed: {}", exception.getMessage());

        return new ResponseEntity<>(
            new ApiResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage()
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<String>> handleFileNotFoundException(ResourceNotFoundException exception) {
        Logger throwingClassLogger = LoggerFactory.getLogger(exception.getThrowingClassName());
        throwingClassLogger.error("Failed to find resource id {}", exception.getResource());

        return new ResponseEntity<>(
            new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                exception.getUserMessage()
            ),
            HttpStatus.BAD_REQUEST
        );
    }
}
