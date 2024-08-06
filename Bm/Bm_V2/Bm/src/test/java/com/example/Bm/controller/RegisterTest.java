package com.example.Bm.controller;


import com.example.Bm.dto.CreateCustomerDTO;
import com.example.Bm.dto.CustomerDTO;
import com.example.Bm.exception.custom.CustomerAlreadyExistsException;
import com.example.Bm.service.security.IAuthenticator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAuthenticator authenticator;


    @Test
    void testRegisterUserWithValidRequestBody() throws Exception {
        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Maryam\",  \"email\": \"maryam@gmail.com\", " +
                                " \"password\": \"maryamkh\"}")
                )
                .andExpect(status().isOk());
    }

    @Test
    void testRegisterUserWithInvalidRequestBody() throws Exception {
        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":  \"test@gmail.com \", \"password\":\"testPassword\" }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterWithAlreadyExistingUser() throws Exception{
        Mockito.when(this.authenticator.register(any(CreateCustomerDTO.class)))
                .thenThrow(new CustomerAlreadyExistsException("User already exists"));


        Mockito.when(this.authenticator.register(any(CreateCustomerDTO.class)))
                .thenReturn(new CustomerDTO());
        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"testUser@gmail.com\" , \"password\":\"testPassword\"}" ))

                .andExpect(status().isBadRequest());
    }

    @Test
    void testAuthenticate() throws Exception {
        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"testUser@gmail.com\", \"password\":\"testPassword\"}"))
                .andExpect(status().isOk());

    }



}

