package com.sanctaultras.encryptedchat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestDocs
public class ITSecurityTest {

    @Autowired
    WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    @Rule
    public JUnitRestDocumentation restDocumentation =
            new JUnitRestDocumentation("target/generated-snippets");

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(documentationConfiguration(this.restDocumentation))
                .apply(springSecurity())
                .build();
        SecurityContextHolder.clearContext();
    }

    @Test
    public void login() throws Exception {
        mockMvc.perform(post("/login").with(getRequestPostProcessorInvalidLogin()))
                .andDo(print()).andExpect(SecurityMockMvcResultMatchers.unauthenticated())
                .andDo(MockMvcRestDocumentation.document("login"));
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