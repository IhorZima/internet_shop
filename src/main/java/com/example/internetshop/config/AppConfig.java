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
    private String name;
    @PostConstruct
    private void init() {
        log.info("user.action.log.file.path={}", path);
    }
}
