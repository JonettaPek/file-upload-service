package fileupload.repository;

import fileupload.entity.FileRecord;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryDatabase {

    private final Map<String, FileRecord> database = new ConcurrentHashMap<>();

    public void save(FileRecord record) {
        database.put(record.getFileId(), record);
    }

    public Map<String, FileRecord> findAll() {
        return database;
    }
}
