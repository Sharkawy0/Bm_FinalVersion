package com.example.Bm.controller;

import com.example.Bm.dto.CreateTransferMoneyDTO;
import com.example.Bm.dto.TransferMoneyDTO;
import com.example.Bm.exception.custom.TransferNotFoundException;
import com.example.Bm.model.TransferMoney;
import com.example.Bm.repository.TransferMoneyRepository;
import com.example.Bm.service.TransferMoneyService;
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

class TransferMoneyServiceTest {

    @Mock
    private TransferMoneyRepository transferMoneyRepository;

    @InjectMocks
    private TransferMoneyService transferMoneyService;

    private TransferMoney transferMoney;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transferMoney = new TransferMoney();
        transferMoney.setId(1L);
        transferMoney.setSenderAccountId("1234567890123456");
        transferMoney.setRecipientAccountId("6543210987654321");
        transferMoney.setAmount(1000);
        transferMoney.setTransferDate("date");
        transferMoney.setTransferStatus("status");
    }

    @Test
    void testAddTransferMoneySuccess() {
        CreateTransferMoneyDTO createTransferMoneyDTO = new CreateTransferMoneyDTO();
        createTransferMoneyDTO.setSenderAccountId("1234567890123456");
        createTransferMoneyDTO.setRecipientAccountId("6543210987654321");
        createTransferMoneyDTO.setAmount(1000);

        when(transferMoneyRepository.save(any(TransferMoney.class))).thenReturn(transferMoney);

        TransferMoneyDTO result = transferMoneyService.addTransferMoney(createTransferMoneyDTO);

        assertEquals("1234567890123456", result.getSenderAccountId());
        assertEquals("6543210987654321", result.getRecipientAccountId());
        assertEquals(1000, result.getAmount());
        assertEquals("date", result.getTransferDate());
        assertEquals("status", result.getTransferStatus());
    }

    @Test
    void testGetTransferMoneySuccess() {
        when(transferMoneyRepository.findAll()).thenReturn(Arrays.asList(transferMoney));

        List<TransferMoneyDTO> result = transferMoneyService.getTransferMoney();

        assertEquals(1, result.size());
        assertEquals("1234567890123456", result.get(0).getSenderAccountId());
    }

    @Test
    void testGetTransferMoneyByIdSuccess() {
        when(transferMoneyRepository.findById(anyLong())).thenReturn(Optional.of(transferMoney));

        TransferMoneyDTO result = transferMoneyService.getTransferMoneyById(1L);

        assertEquals("1234567890123456", result.getSenderAccountId());
        assertEquals("6543210987654321", result.getRecipientAccountId());
        assertEquals(1000, result.getAmount());
        assertEquals("date", result.getTransferDate());
        assertEquals("status", result.getTransferStatus());
    }

    @Test
    void testGetTransferMoneyByIdNotFound() {
        when(transferMoneyRepository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            transferMoneyService.getTransferMoneyById(1L);
        } catch (TransferNotFoundException ex) {
            assertEquals("Transfer not found with id: 1", ex.getMessage());
        }
    }

    @Test
    void testDeleteTransferMoneySuccess() {
        when(transferMoneyRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(transferMoneyRepository).deleteById(anyLong());

        transferMoneyService.deleteTransferMoney(1L);

        verify(transferMoneyRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteTransferMoneyNotFound() {
        when(transferMoneyRepository.existsById(anyLong())).thenReturn(false);

        try {
            transferMoneyService.deleteTransferMoney(1L);
        } catch (TransferNotFoundException ex) {
            assertEquals("Transfer not found with id: 1", ex.getMessage());
        }
    }

    @Test
    void testUpdateTransferMoneySuccess() {
        CreateTransferMoneyDTO createTransferMoneyDTO = new CreateTransferMoneyDTO();
        createTransferMoneyDTO.setSenderAccountId("newSender");
        createTransferMoneyDTO.setRecipientAccountId("newRecipient");
        createTransferMoneyDTO.setAmount(2000);

        when(transferMoneyRepository.findById(anyLong())).thenReturn(Optional.of(transferMoney));
        when(transferMoneyRepository.save(any(TransferMoney.class))).thenReturn(transferMoney);

        TransferMoneyDTO result = transferMoneyService.updateTransferMoney(1L, createTransferMoneyDTO);

        assertEquals("newSender", result.getSenderAccountId());
        assertEquals("newRecipient", result.getRecipientAccountId());
        assertEquals(2000, result.getAmount());

    }

    @Test
    void testUpdateTransferMoneyNotFound() {
        CreateTransferMoneyDTO createTransferMoneyDTO = new CreateTransferMoneyDTO();
        createTransferMoneyDTO.setSenderAccountId("newSender");
        createTransferMoneyDTO.setRecipientAccountId("newRecipient");
        createTransferMoneyDTO.setAmount(2000);

        when(transferMoneyRepository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            transferMoneyService.updateTransferMoney(1L, createTransferMoneyDTO);
        } catch (TransferNotFoundException ex) {
            assertEquals("Transfer not found with id: 1", ex.getMessage());
        }
    }
}
