package com.rex.springtemplate.service;

import com.rex.springtemplate.entity.Role;
import org.springframework.transaction.annotation.Transactional;

public interface RoleService {
    @Transactional
    String createRole(Role role);

    Role getRole(Long id);
}
