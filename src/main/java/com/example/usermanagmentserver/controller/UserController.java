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

    @GetMapping("/user/{id}")
    public ResponseEntity get(@PathVariable long id){
        try {
            AbstractUser abstractUser = userService.get(id);
            return ResponseEntity.ok(abstractUser);
        } catch (AbstractException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/user/create")
    public ResponseEntity create(@RequestBody AbstractUser abstractUser){
        try {
            AbstractUser result = userService.create(abstractUser);
            return ResponseEntity.status(201).body(result);
        } catch (AbstractException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/user/{id}/update")
    public ResponseEntity update(@PathVariable long id , @RequestBody AbstractUser user){
        try {
            AbstractUser result = userService.update(id, user);
            return ResponseEntity.status(200).body(result);
        }catch (AbstractException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/user/{id}/delete")
    public ResponseEntity delete(@PathVariable long id){
        try {
            AbstractUser delete = userService.delete(id);
            return ResponseEntity.ok(delete);
        } catch (AbstractException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
