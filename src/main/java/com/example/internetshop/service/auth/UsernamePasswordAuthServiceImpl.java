package com.example.internetshop.service.auth;

import com.example.internetshop.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UsernamePasswordAuthServiceImpl implements UsernamePasswordAuthenticationService {

    private Map<String, User> users = Map.of("igor", new User(1L, "igor", "emFsdXBh"));
    private Map<String, String> tokens = new HashMap<>();

    @Override
    public User authenticate(String username, String password) {
        return Optional.of(users.get(username))
                .filter(user -> Objects.equals(password, user.getPassword()))
                .orElseThrow(() -> new RuntimeException("Information is wrong"));
    }

    @Override
    public User authenticate(String token) {
        return Optional.ofNullable(tokens.get(token))
                .map(name -> users.get(name))
                .orElseThrow(() -> new RuntimeException("Information is wrong"));
    }

    @Override
    public String generateToken(User user) {
        String token = "AfdqsbdhdbvasDKHadJHasdSAFGWEHFJ245YH2Q234HG13G";
        tokens.put(token, user.getName());
        return token;
    }
}
