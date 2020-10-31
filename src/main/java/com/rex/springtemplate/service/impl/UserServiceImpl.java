package com.rex.springtemplate.service.impl;

import com.rex.springtemplate.entity.User;
import com.rex.springtemplate.mapper.UserMapper;
import com.rex.springtemplate.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public String createUser(User user) {
        user.setCreateTime(LocalDateTime.now());
        userMapper.insert(user);
        return "success";
    }

    @Override
    public User getUser(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
