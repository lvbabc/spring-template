package com.rex.springtemplate.controller;

import com.rex.springtemplate.entity.Role;
import com.rex.springtemplate.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation("添加角色")
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createRole(@Valid @RequestBody Role role) {
        return roleService.createRole(role);
    }

    @ApiOperation("查询角色")
    @GetMapping(path = "/{id}")
    public Role getRole(@PathVariable Long id) {
        return roleService.getRole(id);
    }
}
