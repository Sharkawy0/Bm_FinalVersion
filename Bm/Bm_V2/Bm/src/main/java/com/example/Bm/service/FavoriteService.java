package com.example.Bm.service;

import com.example.Bm.dto.CreateFavoriteDTO;
import com.example.Bm.dto.FavoriteDTO;
import com.example.Bm.exception.custom.FavoriteNotFoundException;
import com.example.Bm.exception.custom.UserAlreadyExistsException;
import com.example.Bm.model.Favorite;
import com.example.Bm.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public FavoriteDTO addFavorite(CreateFavoriteDTO createFavoriteDTO) {
        String recipientAccountId = createFavoriteDTO.getRecipientAccountId();
        if (favoriteRepository.findByRecipientAccountId(recipientAccountId).isPresent()) {
            throw new UserAlreadyExistsException("Favorite already exists for recipient account ID: " + recipientAccountId);
        }

        Favorite favorite = new Favorite();
        favorite.setRecipientName(createFavoriteDTO.getRecipientName());
        favorite.setRecipientAccountId(recipientAccountId);

        favoriteRepository.save(favorite);

        return new FavoriteDTO(favorite.getId(), favorite.getRecipientName(), maskAccountId(favorite.getRecipientAccountId()));
    }

    public List<FavoriteDTO> getFavorites() {
        return favoriteRepository.findAll().stream()
                .map(favorite -> new FavoriteDTO(favorite.getId(), favorite.getRecipientName(), maskAccountId(favorite.getRecipientAccountId())))
                .collect(Collectors.toList());
    }

    public FavoriteDTO getFavorite(Long id) {
        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() -> new FavoriteNotFoundException("Favorite not found with id: " + id));
        return new FavoriteDTO(favorite.getId(), favorite.getRecipientName(), maskAccountId(favorite.getRecipientAccountId()));
    }

    public void deleteFavorite(Long id) {
        if (!favoriteRepository.existsById(id)) {
            throw new FavoriteNotFoundException("Favorite not found with id: " + id);
        }
        favoriteRepository.deleteById(id);
    }

    private String maskAccountId(String accountId) {
        return accountId;
    }

    public FavoriteDTO updateFavorite(Long id, CreateFavoriteDTO createFavoriteDTO) {
        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() -> new FavoriteNotFoundException("Favorite not found with id: " + id));

        favorite.setRecipientName(createFavoriteDTO.getRecipientName());
        favorite.setRecipientAccountId(createFavoriteDTO.getRecipientAccountId());

        Favorite updatedFavorite = favoriteRepository.save(favorite);

        return new FavoriteDTO(updatedFavorite.getId(), updatedFavorite.getRecipientName(), updatedFavorite.getRecipientAccountId());
    }

  /*  private String getLast4Digits(String recipientAccountId) {
        return recipientAccountId != null && recipientAccountId.length() >= 4
                ? recipientAccountId.substring(recipientAccountId.length() - 4)
                : recipientAccountId;
    }*/
}
