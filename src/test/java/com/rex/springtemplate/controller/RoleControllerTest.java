package com.rex.springtemplate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rex.springtemplate.entity.Role;
import com.rex.springtemplate.service.RoleService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class RoleControllerTest {
    @Autowired
    private RoleController roleController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RoleService roleService;

    @Test
    public void testNotNull() {
        Assertions.assertThat(roleController).isNotNull();
    }

    @Test
    void createRole() throws Exception {
        Mockito.when(roleService.createRole(Mockito.any())).thenReturn("Mock Role");
        Role role = new Role();
        role.setRoleName("test");

        String requestJson = new ObjectMapper().writeValueAsString(role);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = post("/role/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson);

        this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getRole() throws Exception {
        Mockito.when(roleService.createRole(Mockito.any())).thenReturn("Mock Role");

        this.mockMvc.perform(MockMvcRequestBuilders.get("/role/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}