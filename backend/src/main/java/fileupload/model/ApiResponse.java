package fileupload.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private LocalDateTime timestamp;
    private int status;
    private T data;
    private String error;

    /**
     * Success case constructor
     */
    public ApiResponse(int status, T data) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.data = data;
        this.error = null;
    }

    /**
     * Error case constructor
     */
    public ApiResponse(int status, String error) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.data = null;
        this.error = error;
    }
}
