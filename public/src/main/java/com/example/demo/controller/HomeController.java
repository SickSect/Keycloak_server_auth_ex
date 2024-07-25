package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Hello to home");
    }

    @GetMapping("/home/admin")
    public ResponseEntity<String> homeAdmin() {
        return ResponseEntity.ok("Hello to admin home");
    }

    @GetMapping("/home/user")
    public ResponseEntity<String> homeUser() {
        return ResponseEntity.ok("Hello to user home");
    }

    @GetMapping("/home/admin-user")
    public ResponseEntity<String> homeUserAdmin() {
        return ResponseEntity.ok("Hello to user home");
    }

}
