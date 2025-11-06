package fileupload.beanconfig;

import fileupload.processor.FileStatisticsProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorConfig {
    @Bean
    public FileStatisticsProcessor fileStatisticsProcessor() {
        return new FileStatisticsProcessor();
    }
}
