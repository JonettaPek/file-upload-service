package fileupload.entity;

import fileupload.constant.FileStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class FileRecord {
    private String fileId;
    private long lineCount;
    private long wordCount;
    private LocalDateTime uploadTimestamp;
    private FileStatus status;

    public FileRecord(String fileId, long lineCount, long wordCount, LocalDateTime uploadTimestamp) {
        this.fileId = fileId;
        this.lineCount = lineCount;
        this.wordCount = wordCount;
        this.uploadTimestamp = uploadTimestamp;
        this.status = FileStatus.PROCESSED;
    }

}
