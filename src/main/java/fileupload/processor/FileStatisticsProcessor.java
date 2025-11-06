package fileupload.processor;

import fileupload.model.FileStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class FileStatisticsProcessor implements FileProcessor<FileStatistics> {

    private static final Logger logger = LoggerFactory.getLogger(FileStatisticsProcessor.class);

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final String CRYPTOGRAPHIC_HASH_ALGORITHM = "SHA-256";

    @Override
    public FileStatistics processFile(MultipartFile file) throws IOException {
        logger.info("Starting file processing for file '{}'", file.getOriginalFilename());

        FileStatistics statistics = getFileStatistics(file);
        String fileId = generateFileId( file.getBytes());
        statistics.setFileId(fileId);

        logger.info("Completed file processing for file '{}'", file.getOriginalFilename());
        return statistics;
    }

    private FileStatistics getFileStatistics(MultipartFile file) throws IOException {
        int lineCount = 0;
        int wordCount = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                String[] words = line.trim().split("\\s+");
                if (words.length > 0 && !words[0].isEmpty()) {
                    wordCount += words.length;
                }
            }
        }

        return new FileStatistics(lineCount, wordCount);
    }

    private String generateFileId(byte[] content) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        try {
            MessageDigest md = MessageDigest.getInstance(CRYPTOGRAPHIC_HASH_ALGORITHM);
            byte[] hash = md.digest(content);
            String contentHash = bytesToHex(hash).substring(0, 16);

            return timestamp + "_" + contentHash;
        } catch (NoSuchAlgorithmException e) {
            logger.warn("{} algorithm not available. Falling back to UUID generation.", CRYPTOGRAPHIC_HASH_ALGORITHM);
            return timestamp + "_" + UUID.randomUUID();
        } catch (Exception e) {
            logger.warn("Error during file ID generation. Falling back to UUID generation.");
            return timestamp + "_" + UUID.randomUUID();
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
