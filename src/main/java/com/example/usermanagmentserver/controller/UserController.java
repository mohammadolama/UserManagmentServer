package com.example.usermanagmentserver.controller;

import com.example.usermanagmentserver.entity.AbstractUser;
import com.example.usermanagmentserver.exception.AbstractException;
import com.example.usermanagmentserver.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable long id){
        try {
            AbstractUser abstractUser = userService.get(id);
            return ResponseEntity.ok(abstractUser);
        } catch (AbstractException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody AbstractUser abstractUser){
        try {
            AbstractUser result = userService.create(abstractUser);
            return ResponseEntity.status(201).body(result);
        } catch (AbstractException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
