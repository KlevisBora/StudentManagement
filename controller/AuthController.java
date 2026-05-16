package com.studentmanagementspringboot.controller;

import com.studentmanagementspringboot.dto.LoginRequestDTO;
import com.studentmanagementspringboot.dto.LoginResponseDTO;
import com.studentmanagementspringboot.security.JwtUtil;
import com.studentmanagementspringboot.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//`POST /api/auth/login` → merr `LoginRequestDTO`, therret `UserService.login()`, nqs e suksesshme gjeneron nje token dhe kthen `LoginResponseDTO`
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO credentials) {
        if (userService.login(credentials)) {
            String token = jwtUtil.generateToken(credentials.getUsername());
            return ResponseEntity.ok(new LoginResponseDTO(token, credentials.getUsername()));
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}