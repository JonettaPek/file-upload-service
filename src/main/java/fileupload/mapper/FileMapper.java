package fileupload.mapper;

import fileupload.entity.FileRecord;
import fileupload.model.FileStatistics;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface FileMapper {

    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    FileRecord modelToEntity(FileStatistics fileStatistics);

    @AfterMapping
    default void setUploadTime(@MappingTarget FileRecord fileRecord) {
        if (fileRecord.getUploadTimestamp() == null) {
            fileRecord.setUploadTimestamp(LocalDateTime.now());
        }
    }
}
