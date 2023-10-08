package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/adminApi")
public class RestAdminController {

    private final UserService userService;

    @Autowired
    public RestAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        User user = userService.findOne(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("user/{id}")
    public ResponseEntity<HttpStatus> editUser(@PathVariable int id, @RequestBody User user) {
        userService.update(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
