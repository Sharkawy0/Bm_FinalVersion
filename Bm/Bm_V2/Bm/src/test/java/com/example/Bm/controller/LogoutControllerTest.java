package com.example.Bm.controller;

import com.example.Bm.service.security.TokenBlacklistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@WebMvcTest(LogoutController.class)
public class LogoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TokenBlacklistService tokenBlacklistService;

    @InjectMocks
    private LogoutController logoutController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogout_Successful() throws Exception {
        String token = "validToken";

        when(tokenBlacklistService.isTokenBlacklisted(token)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/logout")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Logout successful"));

        verify(tokenBlacklistService).blacklistToken(token);
        verify(tokenBlacklistService).isTokenBlacklisted(token);
    }

    @Test
    public void testLogout_MissingToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/logout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid or missing tokenAuth header"));

        verify(tokenBlacklistService, never()).blacklistToken(anyString());
    }

    @Test
    public void testLogout_InvalidToken() throws Exception {
        String token = "invalidToken";

        when(tokenBlacklistService.isTokenBlacklisted(token)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/logout")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid or already logged out token"));

        verify(tokenBlacklistService, never()).blacklistToken(anyString());
    }

    @Test
    public void testLogout_EmptyToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/logout")
                        .header("Authorization", "Bearer ")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid or missing tokenAuth header"));

        verify(tokenBlacklistService, never()).blacklistToken(anyString());
    }
}
