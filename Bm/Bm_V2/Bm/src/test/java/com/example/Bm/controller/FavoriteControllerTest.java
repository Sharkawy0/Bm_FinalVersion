package com.example.Bm.controller;

import com.example.Bm.dto.CreateFavoriteDTO;
import com.example.Bm.dto.FavoriteDTO;
import com.example.Bm.service.FavoriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class FavoriteControllerTest {

    @Mock
    private FavoriteService favoriteService;

    @InjectMocks
    private FavoriteController favoriteController;

    private MockMvc mockMvc;

    private FavoriteDTO favoriteDTO;
    private CreateFavoriteDTO createFavoriteDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(favoriteController).build();

        favoriteDTO = new FavoriteDTO(1L, "Mohamed", "3456");

        createFavoriteDTO = new CreateFavoriteDTO();
        createFavoriteDTO.setRecipientName("Mohamed");
        createFavoriteDTO.setRecipientAccountId("1234567890123456");
    }

    @Test
    void testAddFavorite() throws Exception {
        when(favoriteService.addFavorite(any(CreateFavoriteDTO.class))).thenReturn(favoriteDTO);

        mockMvc.perform(post("/api/favorites/addFavorite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"recipientName\":\"Mohamed\", \"recipientAccountId\":\"1234567890123456\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recipientName").value("Mohamed"))
                .andExpect(jsonPath("$.recipientAccountId").value("3456"));
    }

    @Test
    void testGetFavorites() throws Exception {
        when(favoriteService.getFavorites()).thenReturn(List.of(favoriteDTO));

        mockMvc.perform(get("/api/favorites/getFavorites"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].recipientName").value("Mohamed"))
                .andExpect(jsonPath("$[0].recipientAccountId").value("3456"));
    }

    @Test
    void testGetFavoriteById() throws Exception {
        when(favoriteService.getFavorite(1L)).thenReturn(favoriteDTO);

        mockMvc.perform(get("/api/favorites/getFavorite/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recipientName").value("Mohamed"))
                .andExpect(jsonPath("$.recipientAccountId").value("3456"));
    }

    @Test
    void testDeleteFavorite() throws Exception {
        mockMvc.perform(delete("/api/favorites/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("User deleted successfully"));
    }

    @Test
    void testUpdateFavorite() throws Exception {
        long favoriteId = 1L;
        when(favoriteService.updateFavorite(favoriteId, any(CreateFavoriteDTO.class))).thenReturn(favoriteDTO);

        mockMvc.perform(put("/api/favorites/updateFavorite/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"recipientName\":\"Ahmed\", \"recipientAccountId\":\"6543210987654321\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recipientName").value("Ahmed"))
                .andExpect(jsonPath("$.recipientAccountId").value("4321"));
    }

}
