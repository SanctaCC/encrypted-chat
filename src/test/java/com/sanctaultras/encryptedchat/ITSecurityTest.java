package com.sanctaultras.encryptedchat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITSecurityTest {

    @Autowired
    WebApplicationContext applicationContext;

    MockMvc mockMvc;


    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
        SecurityContextHolder.clearContext();
    }

    @Test
    public void login() throws Exception {
        mockMvc.perform(post("/login").with(getRequestPostProcessorInvalidLogin()))
                .andDo(print()).andExpect(SecurityMockMvcResultMatchers.unauthenticated());

    }

    private RequestPostProcessor getRequestPostProcessorInvalidLogin() {
        return request -> {
            request.addHeader("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
            request.addParameter("username","invalid");
            request.addParameter("password","invalid");
            return request;
        };
    }
}