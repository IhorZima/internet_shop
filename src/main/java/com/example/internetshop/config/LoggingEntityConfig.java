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

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@ConfigurationProperties(prefix = "logging.user.action")
@Slf4j
@Configuration
@Data
public class LoggingEntityConfig {

    private Path filePath;
    private static final String LOG_DIR_NAME = "logs";

    @Bean
    public Path loggingFilePath() throws IOException, URISyntaxException {
        if (Objects.isNull(filePath)) {
            Path loggingDirPath = Path.of(getClass().getProtectionDomain().getCodeSource().getLocation().toURI())
                    .resolve(LOG_DIR_NAME);
            log.info("Property logging.user.action.file.path not found. Using logs directory: {}", loggingDirPath);
            return Files.exists(loggingDirPath) ? loggingDirPath : Files.createDirectory(loggingDirPath);
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

