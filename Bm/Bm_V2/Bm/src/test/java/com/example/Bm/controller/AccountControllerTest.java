package com.example.Bm.controller;

import com.example.Bm.service.security.CustomerDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @Mock
    private CustomerDetailsImpl customerDetails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testGetBalance_Success() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(customerDetails);

        when(customerDetails.getUsername()).thenReturn("maryamk@gmail.com");
        when(customerDetails.getName()).thenReturn("Maryam");
        when(customerDetails.getLastName()).thenReturn("Khaled");
        when(customerDetails.getPhoneNumber()).thenReturn("1234567890");

        ResponseEntity<Map<String, Object>> responseEntity = accountController.getBalance();

        assertEquals(200, responseEntity.getStatusCodeValue());
        Map<String, Object> responseBody = responseEntity.getBody();
        assertEquals("maryamk@gmail.com", responseBody.get("username"));
        assertEquals("Maryam", responseBody.get("name"));
        assertEquals("Khaled", responseBody.get("lastName"));
        assertEquals("1234567890", responseBody.get("phoneNumber"));
        assertEquals(1000, responseBody.get("balance"));
    }

    @Test
    public void testGetBalance_Unauthorized() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn("anonymousUser");

        ResponseEntity<Map<String, Object>> responseEntity = accountController.getBalance();

        assertEquals(401, responseEntity.getStatusCodeValue());
        assertEquals(null, responseEntity.getBody());
    }
}
