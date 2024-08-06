package com.example.Bm.controller;

import com.example.Bm.dto.CreateFavoriteDTO;
import com.example.Bm.dto.FavoriteDTO;
import com.example.Bm.exception.custom.FavoriteNotFoundException;
import com.example.Bm.exception.custom.UserAlreadyExistsException;
import com.example.Bm.model.Favorite;
import com.example.Bm.repository.FavoriteRepository;
import com.example.Bm.service.FavoriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FavoriteServiceTest {

    @Mock
    private FavoriteRepository favoriteRepository;

    @InjectMocks
    private FavoriteService favoriteService;

    private Favorite favorite;
    private CreateFavoriteDTO createFavoriteDTO;

    @BeforeEach
    void setUp() {
        createFavoriteDTO = new CreateFavoriteDTO();
        createFavoriteDTO.setRecipientName("Mohamed");
        createFavoriteDTO.setRecipientAccountId("1234567890123456");

        favorite = new Favorite();
        favorite.setId(1L);
        favorite.setRecipientName("Mohamed");
        favorite.setRecipientAccountId("1234567890123456");
    }

    @Test
    void testAddFavoriteSuccess() {
        CreateFavoriteDTO createFavoriteDTO = new CreateFavoriteDTO();
        createFavoriteDTO.setRecipientName("Mohamed");
        createFavoriteDTO.setRecipientAccountId("1234567890123456");

        when(favoriteRepository.findByRecipientAccountId(createFavoriteDTO.getRecipientAccountId())).thenReturn(Optional.empty());
        when(favoriteRepository.save(any(Favorite.class))).thenReturn(favorite);

        FavoriteDTO result = favoriteService.addFavorite(createFavoriteDTO);

        assertEquals("Mohamed", result.getRecipientName());
        assertEquals("1234567890123456", result.getRecipientAccountId());
    }


    @Test
    void testAddFavoriteAlreadyExists() {
        when(favoriteRepository.findByRecipientAccountId("1234567890123456")).thenReturn(Optional.of(favorite));

        assertThrows(UserAlreadyExistsException.class, () -> {
            favoriteService.addFavorite(createFavoriteDTO);
        });
    }

    @Test
    void testGetFavorites() {
        List<Favorite> favorites = Arrays.asList(favorite);
        when(favoriteRepository.findAll()).thenReturn(favorites);

        List<FavoriteDTO> result = favoriteService.getFavorites();

        assertEquals(1, result.size());
        assertEquals("Mohamed", result.get(0).getRecipientName());
        assertEquals("1234567890123456", result.get(0).getRecipientAccountId());
    }

    @Test
    void testGetFavoriteById() {
        when(favoriteRepository.findById(1L)).thenReturn(Optional.of(favorite));

        FavoriteDTO result = favoriteService.getFavorite(1L);

        assertEquals("Mohamed", result.getRecipientName());
        assertEquals("1234567890123456", result.getRecipientAccountId());
    }


    @Test
    void testGetFavoriteByIdNotFound() {
        when(favoriteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(FavoriteNotFoundException.class, () -> {
            favoriteService.getFavorite(1L);
        });
    }

    @Test
    void testDeleteFavoriteSuccess() {
        when(favoriteRepository.existsById(1L)).thenReturn(true);

        favoriteService.deleteFavorite(1L);

        verify(favoriteRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteFavoriteNotFound() {
        when(favoriteRepository.existsById(1L)).thenReturn(false);

        assertThrows(FavoriteNotFoundException.class, () -> {
            favoriteService.deleteFavorite(1L);
        });
    }

    @Test
    void testUpdateFavoriteSuccess() {
        when(favoriteRepository.findById(1L)).thenReturn(Optional.of(favorite));
        when(favoriteRepository.save(any(Favorite.class))).thenReturn(favorite);

        CreateFavoriteDTO updateDTO = new CreateFavoriteDTO();
        updateDTO.setRecipientName("Mohamed");
        updateDTO.setRecipientAccountId("6543210987654321");

        FavoriteDTO result = favoriteService.updateFavorite(1L, updateDTO);

        assertEquals("Mohamed", result.getRecipientName());
        assertEquals("6543210987654321", result.getRecipientAccountId());
    }

    @Test
    void testUpdateFavoriteNotFound() {
        when(favoriteRepository.findById(1L)).thenReturn(Optional.empty());

        CreateFavoriteDTO updateDTO = new CreateFavoriteDTO();
        updateDTO.setRecipientName("Mohamed");
        updateDTO.setRecipientAccountId("6543210987654321");

        assertThrows(FavoriteNotFoundException.class, () -> {
            favoriteService.updateFavorite(1L, updateDTO);
        });
    }

}
