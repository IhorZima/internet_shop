package com.example.internetshop;

import com.example.internetshop.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;

@SpringBootApplication
public class InternetShopApplication {

    @Bean
    public Path logsDirPath(AppConfig appConfig) {
        if (appConfig.getPath() == null) {
            // create dir near .jar and get path to it
        } else {
            // get path from what was received as config path
        }
        return null;
    }

    public static void main(String[] args) {
        SpringApplication.run(InternetShopApplication.class, args);
    }
}
