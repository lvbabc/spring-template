package com.rex.springtemplate.service;

import com.rex.springtemplate.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    @Transactional
    String createUser(User user);

    User getUser(Long id);
}
