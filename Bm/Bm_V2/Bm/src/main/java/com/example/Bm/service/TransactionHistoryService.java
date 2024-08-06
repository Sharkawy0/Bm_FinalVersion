package com.example.Bm.service;

import com.example.Bm.dto.CreateTransactionHistoryDTO;
import com.example.Bm.dto.TransactionHistoryDTO;
import com.example.Bm.exception.custom.TransactionHistoryNotFoundException;
import com.example.Bm.model.TransactionHistory;
import com.example.Bm.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionHistoryService {

    private final TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    public TransactionHistoryService(TransactionHistoryRepository transactionHistoryRepository) {
        this.transactionHistoryRepository = transactionHistoryRepository;
    }

    public TransactionHistoryDTO addTransactionHistory(CreateTransactionHistoryDTO createTransactionHistoryDTO) {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setTransactionType(createTransactionHistoryDTO.getTransactionType());
        transactionHistory.setTransactionStatus(createTransactionHistoryDTO.getTransactionStatus());
        transactionHistory.setTransactionDate(createTransactionHistoryDTO.getTransactionDate());
        transactionHistory.setRecipientAccountId(createTransactionHistoryDTO.getRecipientAccountId());

        TransactionHistory savedTransactionHistory = transactionHistoryRepository.save(transactionHistory);

        return new TransactionHistoryDTO(
                savedTransactionHistory.getId(),
                savedTransactionHistory.getTransactionType(),
                savedTransactionHistory.getTransactionStatus(),
                savedTransactionHistory.getTransactionDate(),
                savedTransactionHistory.getRecipientAccountId()
        );
    }

    public List<TransactionHistoryDTO> getTransactionHistories() {
        return transactionHistoryRepository.findAll().stream()
                .map(transactionHistory -> new TransactionHistoryDTO(
                        transactionHistory.getId(),
                        transactionHistory.getTransactionType(),
                        transactionHistory.getTransactionStatus(),
                        transactionHistory.getTransactionDate(),
                        transactionHistory.getRecipientAccountId()
                ))
                .collect(Collectors.toList());
    }

    public TransactionHistoryDTO getTransactionHistory(Long id) {
        TransactionHistory transactionHistory = transactionHistoryRepository.findById(id)
                .orElseThrow(() -> new TransactionHistoryNotFoundException("Transaction history not found with id: " + id));

        return new TransactionHistoryDTO(
                transactionHistory.getId(),
                transactionHistory.getTransactionType(),
                transactionHistory.getTransactionStatus(),
                transactionHistory.getTransactionDate(),
                transactionHistory.getRecipientAccountId()
        );
    }

    public void deleteTransactionHistory(Long id) {
        if (!transactionHistoryRepository.existsById(id)) {
            throw new TransactionHistoryNotFoundException("Transaction history not found with id: " + id);
        }
        transactionHistoryRepository.deleteById(id);
    }

    public TransactionHistoryDTO updateTransactionHistory(Long id, CreateTransactionHistoryDTO createTransactionHistoryDTO) {
        TransactionHistory transactionHistory = transactionHistoryRepository.findById(id)
                .orElseThrow(() -> new TransactionHistoryNotFoundException("Transaction history not found with id: " + id));

        transactionHistory.setTransactionType(createTransactionHistoryDTO.getTransactionType());
        transactionHistory.setTransactionStatus(createTransactionHistoryDTO.getTransactionStatus());
        transactionHistory.setTransactionDate(createTransactionHistoryDTO.getTransactionDate());
        transactionHistory.setRecipientAccountId(createTransactionHistoryDTO.getRecipientAccountId());

        TransactionHistory updatedTransactionHistory = transactionHistoryRepository.save(transactionHistory);

        return new TransactionHistoryDTO(
                updatedTransactionHistory.getId(),
                updatedTransactionHistory.getTransactionType(),
                updatedTransactionHistory.getTransactionStatus(),
                updatedTransactionHistory.getTransactionDate(),
                updatedTransactionHistory.getRecipientAccountId()
        );
    }
}
