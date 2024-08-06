package com.example.Bm.controller;

import com.example.Bm.dto.CreateTransferMoneyDTO;
import com.example.Bm.dto.TransferMoneyDTO;
import com.example.Bm.service.TransferMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer")
@Validated
public class TransferMoneyController {

    private final TransferMoneyService transferMoneyService;

    @Autowired
    public TransferMoneyController(TransferMoneyService transferMoneyService) {
        this.transferMoneyService = transferMoneyService;
    }

    @PostMapping
    public ResponseEntity<TransferMoneyDTO> transferMoney(@RequestBody @Validated CreateTransferMoneyDTO createTransferMoneyDTO) {
        TransferMoneyDTO transferMoneyDTO = transferMoneyService.transferMoney(createTransferMoneyDTO);
        return new ResponseEntity<>(transferMoneyDTO, HttpStatus.CREATED);
    }
}
