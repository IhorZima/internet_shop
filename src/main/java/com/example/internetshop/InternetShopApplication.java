package com.example.internetshop;

import com.example.internetshop.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class InternetShopApplication {
    @Bean
    public String initApp(AppConfig appConfig) {
        return "123";
    }

    public static void main(String[] args) {
        SpringApplication.run(InternetShopApplication.class, args);
    }
}
