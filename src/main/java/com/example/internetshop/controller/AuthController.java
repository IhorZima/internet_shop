package com.example.internetshop.controller;

import com.example.internetshop.model.User;
import com.example.internetshop.model.dto.UserDTO;
import com.example.internetshop.service.auth.UsernamePasswordAuthenticationService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class  AuthController {

    // TODO: extract to constants
    private static final String TOKEN_HEADER_NAME = "TOKEN";

    private UsernamePasswordAuthenticationService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserDTO user) {
        User authenticatedUser = authService.authenticate(user.getUsername(),
                Base64.encodeBase64String(user.getPassword().getBytes()));

        String token = authService.generateToken(authenticatedUser);

        HttpCookie cookie = ResponseCookie.from(TOKEN_HEADER_NAME, token)
                .path("/")
                .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .header(TOKEN_HEADER_NAME, token)
                .build();
    }

    @GetMapping("/user")
    public User getUser(@CookieValue(TOKEN_HEADER_NAME) String token) {
        return authService.authenticate(token);
    }
}
