package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.User;
import com.ecomflutter.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ecomflutter/user")
public class UserRest {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    private List<User> findAll() {
        return this.userService.findAll();
    }
}
