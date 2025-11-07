package fileupload.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileStatistics {
    private long lineCount;
    private long wordCount;
    private String fileId;

    public FileStatistics(long lineCount, long wordCount) {
        this.lineCount = lineCount;
        this.wordCount = wordCount;
    }

}
