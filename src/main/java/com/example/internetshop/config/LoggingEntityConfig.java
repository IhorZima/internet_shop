package com.example.internetshop.config;

import com.example.internetshop.model.Order;
import com.example.internetshop.service.LoggingService;
import com.example.internetshop.service.NoOpOrderLoggingService;
import com.example.internetshop.service.OrderFileLoggingService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

@ConfigurationProperties(prefix = "logging.user.action")
@Slf4j
@Configuration
@Data
public class LoggingEntityConfig {

    private Path filePath;
    private static final String LOG_DIR_NAME = "entity_logs";

    @Bean
    public Path loggingFilePath() throws IOException, URISyntaxException {
        if (Objects.isNull(filePath)) {
            URI uri = LoggingEntityConfig.class.getProtectionDomain().getCodeSource().getLocation().toURI();

            Path dirPath = Optional.of(uri)
                    .map(URI::toString)
                    .filter(uriString -> uriString.contains("!"))
                    .map(uriString -> uriString.split("!"))
                    .filter(array -> array.length > 0)
                    .map(array -> array[0])
                    .map(URI::create)
                    .map(Path::of)
                    .map(path -> path.resolve(LOG_DIR_NAME))
                    .orElse(Path.of(uri).resolve(LOG_DIR_NAME));

            log.info("Property logging.user.action.file.path not found. Using logs directory: {}", dirPath);
            return Files.exists(dirPath) ? dirPath : Files.createDirectory(dirPath);
        }

        if (Files.exists(filePath) && Files.isDirectory(filePath)) {
            return filePath;
        }

        throw new RuntimeException("Bad file path: " + filePath.toString());
    }

    @Bean
    @ConditionalOnProperty(value = "logging.user.action.enabled", havingValue = "true")
    public LoggingService<Order> orderFileLoggingService() {
        return new OrderFileLoggingService();
    }

    @Bean
    @ConditionalOnProperty(value = "logging.user.action.enabled", havingValue = "false", matchIfMissing = true)
    public LoggingService<Order> noOpLoggingService() {
        log.warn("Could not found logging.user.action.enabled property. Creating no-op logging service...");
        return new NoOpOrderLoggingService();
    }
}

