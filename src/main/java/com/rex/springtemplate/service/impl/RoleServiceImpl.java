package com.rex.springtemplate.service.impl;

import com.rex.springtemplate.entity.Role;
import com.rex.springtemplate.mapper.RoleMapper;
import com.rex.springtemplate.service.RoleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public String createRole(Role role) {
        role.setCreateTime(LocalDateTime.now());
        roleMapper.insert(role);

        return "success";
    }

    @Override
    public Role getRole(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }
}
