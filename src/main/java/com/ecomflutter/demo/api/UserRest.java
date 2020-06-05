package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.User;
import com.ecomflutter.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("ecomflutter/user")
public class UserRest {

    @Autowired
    public UserService userService;

    @GetMapping("/")
    public List<User> findAll() {
        return this.userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    @GetMapping("/username/{username}")
    public User findById(@PathVariable String username) {
        return this.userService.findByUsername(username);
    }

    @PostMapping("/")
    public int save(@RequestBody User user) {
        return this.userService.save(user);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return this.userService.deleteById(id);
    }
}
