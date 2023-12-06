package org.system.onlineelection.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.system.onlineelection.infrastructure.adapter.payload.JwtResponse;
import org.system.onlineelection.infrastructure.adapter.payload.LoginRequest;
import org.system.onlineelection.infrastructure.adapter.payload.RegisterRequest;
import org.system.onlineelection.infrastructure.mapper.AuthResponse;
import org.system.onlineelection.application.service.AuthService;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest)
    {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
}
