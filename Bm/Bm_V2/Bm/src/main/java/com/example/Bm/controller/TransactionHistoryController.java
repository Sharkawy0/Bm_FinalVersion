package com.example.Bm.controller;

import com.example.Bm.dto.CreateTransactionHistoryDTO;
import com.example.Bm.dto.TransactionHistoryDTO;
import com.example.Bm.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Validated
public class TransactionHistoryController {

    private final TransactionHistoryService transactionHistoryService;

    @Autowired
    public TransactionHistoryController(TransactionHistoryService transactionHistoryService) {
        this.transactionHistoryService = transactionHistoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<TransactionHistoryDTO> addTransactionHistory(@RequestBody @Validated CreateTransactionHistoryDTO createTransactionHistoryDTO) {
        TransactionHistoryDTO transactionHistoryDTO = transactionHistoryService.addTransactionHistory(createTransactionHistoryDTO);
        return new ResponseEntity<>(transactionHistoryDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TransactionHistoryDTO>> getTransactionHistories() {
        List<TransactionHistoryDTO> transactionHistories = transactionHistoryService.getTransactionHistories();
        return new ResponseEntity<>(transactionHistories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionHistoryDTO> getTransactionHistoryById(@PathVariable Long id) {
        TransactionHistoryDTO transactionHistoryDTO = transactionHistoryService.getTransactionHistory(id);
        return new ResponseEntity<>(transactionHistoryDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TransactionHistoryDTO> updateTransactionHistory(@PathVariable Long id, @RequestBody @Validated CreateTransactionHistoryDTO createTransactionHistoryDTO) {
        TransactionHistoryDTO updatedTransactionHistory = transactionHistoryService.updateTransactionHistory(id, createTransactionHistoryDTO);
        return new ResponseEntity<>(updatedTransactionHistory, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTransactionHistory(@PathVariable Long id) {
        transactionHistoryService.deleteTransactionHistory(id);
        return new ResponseEntity<>("Transaction history deleted successfully", HttpStatus.NO_CONTENT);
    }
}
