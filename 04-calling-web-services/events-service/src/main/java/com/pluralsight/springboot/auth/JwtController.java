package com.pluralsight.springboot.auth;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class JwtController {
        private final JwtService jwtService = new JwtService();

    @GetMapping("/token")
    public String generateToken() {
        return jwtService.generateToken("admin");
    }
}
