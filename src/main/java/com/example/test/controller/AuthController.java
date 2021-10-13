package com.example.test.controller;

import com.example.test.entity.Admin;
import com.example.test.entity.template.User;
import com.example.test.payload.LoginPayload;
import com.example.test.repository.AdminRepository;
import com.example.test.repository.UserRepository;
import com.example.test.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AdminRepository adminRepository;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;


  @PostMapping("/login")
  public ResponseEntity login(@RequestBody LoginPayload payload) {
    System.out.println(payload);

    User user = userRepository.findByUsername(payload.getUserName());
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getUserName(), payload.getPassword()));
    String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());

    if (token == null) {
      return new ResponseEntity("Xatolik", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    Map<String, Object> login = new HashMap<>();
    login.put("token", token);
    login.put("username", user.getUsername());
    login.put("success", true);
    return ResponseEntity.ok(login);
  }
}
