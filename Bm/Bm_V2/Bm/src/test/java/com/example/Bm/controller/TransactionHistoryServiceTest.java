package com.example.Bm.controller;

import com.example.Bm.dto.CreateTransactionHistoryDTO;
import com.example.Bm.dto.TransactionHistoryDTO;
import com.example.Bm.exception.custom.TransactionHistoryNotFoundException;
import com.example.Bm.model.TransactionHistory;
import com.example.Bm.repository.TransactionHistoryRepository;
import com.example.Bm.service.TransactionHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionHistoryServiceTest {

    @Mock
    private TransactionHistoryRepository transactionHistoryRepository;

    @InjectMocks
    private TransactionHistoryService transactionHistoryService;

    private TransactionHistory transactionHistory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionHistory = new TransactionHistory();
        transactionHistory.setId(1L);
        transactionHistory.setTransactionType("type");
        transactionHistory.setTransactionStatus("status");
        transactionHistory.setTransactionDate("date");
        transactionHistory.setRecipientAccountId("1234567890123456");
    }

    @Test
    void testAddTransactionHistorySuccess() {
        CreateTransactionHistoryDTO createTransactionHistoryDTO = new CreateTransactionHistoryDTO();
        createTransactionHistoryDTO.setTransactionType("type");
        createTransactionHistoryDTO.setTransactionStatus("status");
        createTransactionHistoryDTO.setTransactionDate("date");
        createTransactionHistoryDTO.setRecipientAccountId("1234567890123456");

        when(transactionHistoryRepository.save(any(TransactionHistory.class))).thenReturn(transactionHistory);

        TransactionHistoryDTO result = transactionHistoryService.addTransactionHistory(createTransactionHistoryDTO);

        assertEquals("type", result.getTransactionType());
        assertEquals("status", result.getTransactionStatus());
        assertEquals("date", result.getTransactionDate());
        assertEquals("1234567890123456", result.getRecipientAccountId());
    }

    @Test
    void testGetTransactionHistoriesSuccess() {
        when(transactionHistoryRepository.findAll()).thenReturn(Arrays.asList(transactionHistory));

        List<TransactionHistoryDTO> result = transactionHistoryService.getTransactionHistories();

        assertEquals(1, result.size());
        assertEquals("type", result.get(0).getTransactionType());
    }

    @Test
    void testGetTransactionHistorySuccess() {
        when(transactionHistoryRepository.findById(anyLong())).thenReturn(Optional.of(transactionHistory));

        TransactionHistoryDTO result = transactionHistoryService.getTransactionHistory(1L);

        assertEquals("type", result.getTransactionType());
        assertEquals("status", result.getTransactionStatus());
        assertEquals("date", result.getTransactionDate());
        assertEquals("1234567890123456", result.getRecipientAccountId());
    }

    @Test
    void testGetTransactionHistoryNotFound() {
        when(transactionHistoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            transactionHistoryService.getTransactionHistory(1L);
        } catch (TransactionHistoryNotFoundException ex) {
            assertEquals("Transaction history not found with id: 1", ex.getMessage());
        }
    }

    @Test
    void testDeleteTransactionHistorySuccess() {
        when(transactionHistoryRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(transactionHistoryRepository).deleteById(anyLong());

        transactionHistoryService.deleteTransactionHistory(1L);

        verify(transactionHistoryRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteTransactionHistoryNotFound() {
        when(transactionHistoryRepository.existsById(anyLong())).thenReturn(false);

        try {
            transactionHistoryService.deleteTransactionHistory(1L);
        } catch (TransactionHistoryNotFoundException ex) {
            assertEquals("Transaction history not found with id: 1", ex.getMessage());
        }
    }

    @Test
    void testUpdateTransactionHistorySuccess() {
        CreateTransactionHistoryDTO createTransactionHistoryDTO = new CreateTransactionHistoryDTO();
        createTransactionHistoryDTO.setTransactionType("newType");
        createTransactionHistoryDTO.setTransactionStatus("newStatus");
        createTransactionHistoryDTO.setTransactionDate("newDate");
        createTransactionHistoryDTO.setRecipientAccountId("1234567890123456");

        when(transactionHistoryRepository.findById(anyLong())).thenReturn(Optional.of(transactionHistory));
        when(transactionHistoryRepository.save(any(TransactionHistory.class))).thenReturn(transactionHistory);

        TransactionHistoryDTO result = transactionHistoryService.updateTransactionHistory(1L, createTransactionHistoryDTO);

        assertEquals("newType", result.getTransactionType());
        assertEquals("newStatus", result.getTransactionStatus());
        assertEquals("newDate", result.getTransactionDate());
        assertEquals("1234567890123456", result.getRecipientAccountId());
    }

    @Test
    void testUpdateTransactionHistoryNotFound() {
        CreateTransactionHistoryDTO createTransactionHistoryDTO = new CreateTransactionHistoryDTO();
        createTransactionHistoryDTO.setTransactionType("newType");
        createTransactionHistoryDTO.setTransactionStatus("newStatus");
        createTransactionHistoryDTO.setTransactionDate("newDate");
        createTransactionHistoryDTO.setRecipientAccountId("1234567890123456");

        when(transactionHistoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            transactionHistoryService.updateTransactionHistory(1L, createTransactionHistoryDTO);
        } catch (TransactionHistoryNotFoundException ex) {
            assertEquals("Transaction history not found with id: 1", ex.getMessage());
        }
    }
}
