package com.rex.springtemplate.controller;

import com.rex.springtemplate.entity.User;
import com.rex.springtemplate.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("添加用户")
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @ApiOperation("查询用户")
    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
