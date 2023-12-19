package com.resident.residentialmanagement.auth;

import com.resident.residentialmanagement.repository.UserRepository;
import com.resident.residentialmanagement.response.GeneralResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class AuthController {

    private final UserRepository repository;
    private final AuthService service;

    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository repository, AuthService service, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public GeneralResponse<?> register(@RequestBody RegisterRequest request) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", service.register(request));
        }catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}