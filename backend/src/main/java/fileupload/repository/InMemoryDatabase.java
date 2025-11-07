package fileupload.repository;

import fileupload.entity.FileRecord;
import fileupload.repository.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryDatabase {

    private final Map<String, FileRecord> database = new ConcurrentHashMap<>();

    public void save(FileRecord record) {
        database.put(record.getFileId(), record);
    }

    public FileRecord findByFileId(String fileId) throws ResourceNotFoundException {
        FileRecord fileRecord = database.get(fileId);
        if (fileRecord == null) {
            throw new ResourceNotFoundException(
                String.format("Failed to fetch file id %s from database %s", fileId, InMemoryDatabase.class.getName()),
                "Please provide a valid file ID.",
                InMemoryDatabase.class.getName(),
                fileId
            );
        }
        return fileRecord;
    }
}
