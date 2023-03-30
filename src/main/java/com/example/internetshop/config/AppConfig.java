package com.example.internetshop.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@ConfigurationProperties(prefix = "file")
@Slf4j
@Configuration
@Data
public class AppConfig {
    private String path;

    private static final String FILE_PATH_ENV_NAME = "env.file.path";

    @PostConstruct
    private void init() {
        log.info("file.path ENV: {}, PROPERTY: {},  VALUE: {}",
                System.getenv(FILE_PATH_ENV_NAME),
                System.getProperty(FILE_PATH_ENV_NAME),
                path);
    }
}

