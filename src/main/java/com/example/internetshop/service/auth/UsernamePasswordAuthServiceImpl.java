package com.example.internetshop.service.auth;

import com.example.internetshop.model.User;
import com.example.internetshop.service.logging.LogOnTheFly;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@NoArgsConstructor
@Slf4j
public class UsernamePasswordAuthServiceImpl implements UsernamePasswordAuthenticationService {

    private Map<String, User> users = Map.of("igor", new User(1L, "igor", "emFsdXBh"));
    private Map<String, String> tokens = new HashMap<>();

    @Override
    @LogOnTheFly
    public User authenticate(String username, String password) {
        log.info("Authenticating user: {}", username);
        return Optional.of(users.get(username))
                .filter(user -> Objects.equals(password, user.getPassword()))
                .orElseThrow(() -> new RuntimeException("Information is wrong"));
    }

    @Override
    @LogOnTheFly
    public User authenticate(String token) {
        return Optional.ofNullable(tokens.get(token))
                .map(name -> users.get(name))
                .orElse(null);
    }

    @Override
    @LogOnTheFly
    public String generateToken(User user) {
        log.info("Generating token...");
        String token = "AfdqsbdhdbvasDKHadJHasdSAFGWEHFJ245YH2Q234HG13G";
        log.info("Storing token...");
        tokens.put(token, user.getName());
        log.info("Storing has been stored successfully...");
        return token;
    }
}
