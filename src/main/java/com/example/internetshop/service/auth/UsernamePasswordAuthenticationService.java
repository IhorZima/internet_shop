package com.example.internetshop.service.auth;

import com.example.internetshop.model.User;

public interface UsernamePasswordAuthenticationService {

    User authenticate(String username, String password);

    User authenticate(String token);

    String generateToken(User user);
}
