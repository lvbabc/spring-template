package com.rex.springtemplate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rex.springtemplate.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testNotNull() {
        Assertions.assertThat(userController).isNotNull();
    }

    @Test
    @Rollback
    void createUser() throws Exception {
        User user = new User("testName", "12345678", "123@qq.com", "");
        String requestJson = new ObjectMapper().writeValueAsString(user);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = post("/user/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson);

        this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Rollback
    void getUser() {
    }
}