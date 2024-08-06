package com.example.Bm.controller;

import com.example.Bm.dto.CreateFavoriteDTO;
import com.example.Bm.dto.FavoriteDTO;
import com.example.Bm.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@Validated
public class FavoriteController {

    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/addFavorite")
    public ResponseEntity<FavoriteDTO> addFavorite(@RequestBody @Validated CreateFavoriteDTO createFavoriteDTO) {
        FavoriteDTO favoriteDTO = favoriteService.addFavorite(createFavoriteDTO);
        return new ResponseEntity<>(favoriteDTO, HttpStatus.OK);
    }

    @GetMapping("/getFavorites")
    public ResponseEntity<List<FavoriteDTO>> getFavorites() {
        List<FavoriteDTO> favorites = favoriteService.getFavorites();
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @GetMapping("/getFavorite/{id}")
    public ResponseEntity<FavoriteDTO> getFavorite(@PathVariable Long id) {
        FavoriteDTO favorite = favoriteService.getFavorite(id);
        return new ResponseEntity<>(favorite, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFavorite(@PathVariable Long id) {
        favoriteService.deleteFavorite(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/updateFavorite/{id}")
    public ResponseEntity<FavoriteDTO> updateFavorite(@PathVariable Long id, @RequestBody CreateFavoriteDTO createFavoriteDTO) {
        FavoriteDTO favoriteDTO = favoriteService.updateFavorite(id, createFavoriteDTO);
        return new ResponseEntity<>(favoriteDTO, HttpStatus.OK);
    }
}
