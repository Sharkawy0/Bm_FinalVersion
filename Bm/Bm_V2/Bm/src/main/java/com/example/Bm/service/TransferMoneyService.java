package com.example.Bm.service;

import com.example.Bm.dto.CreateTransferMoneyDTO;
import com.example.Bm.dto.TransferMoneyDTO;
import com.example.Bm.exception.custom.TransferNotFoundException;
import com.example.Bm.model.TransferMoney;
import com.example.Bm.repository.TransferMoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferMoneyService {

    private final TransferMoneyRepository transferMoneyRepository;

    @Autowired
    public TransferMoneyService(TransferMoneyRepository transferMoneyRepository) {
        this.transferMoneyRepository = transferMoneyRepository;
    }

    public TransferMoneyDTO addTransferMoney(CreateTransferMoneyDTO createTransferMoneyDTO) {
        TransferMoney transferMoney = new TransferMoney();
        transferMoney.setSenderAccountId(createTransferMoneyDTO.getSenderAccountId());
        transferMoney.setRecipientAccountId(createTransferMoneyDTO.getRecipientAccountId());
        transferMoney.setAmount(createTransferMoneyDTO.getAmount());
        transferMoney.setTransferDate(createTransferMoneyDTO.getTransferDate());
        transferMoney.setTransferStatus(createTransferMoneyDTO.getTransferStatus());

        TransferMoney savedTransferMoney = transferMoneyRepository.save(transferMoney);

        return new TransferMoneyDTO(
                savedTransferMoney.getId(),
                savedTransferMoney.getSenderAccountId(),
                savedTransferMoney.getRecipientAccountId(),
                savedTransferMoney.getAmount(),
                savedTransferMoney.getTransferDate(),
                savedTransferMoney.getTransferStatus()
        );
    }

    public List<TransferMoneyDTO> getTransferMoney() {
        return transferMoneyRepository.findAll().stream()
                .map(transferMoney -> new TransferMoneyDTO(
                        transferMoney.getId(),
                        transferMoney.getSenderAccountId(),
                        transferMoney.getRecipientAccountId(),
                        transferMoney.getAmount(),
                        transferMoney.getTransferDate(),
                        transferMoney.getTransferStatus()
                ))
                .collect(Collectors.toList());
    }

    public TransferMoneyDTO getTransferMoneyById(Long id) {
        TransferMoney transferMoney = transferMoneyRepository.findById(id)
                .orElseThrow(() -> new TransferNotFoundException("Transfer not found with id: " + id));

        return new TransferMoneyDTO(
                transferMoney.getId(),
                transferMoney.getSenderAccountId(),
                transferMoney.getRecipientAccountId(),
                transferMoney.getAmount(),
                transferMoney.getTransferDate(),
                transferMoney.getTransferStatus()
        );
    }

    public void deleteTransferMoney(Long id) {
        if (!transferMoneyRepository.existsById(id)) {
            throw new TransferNotFoundException("Transfer not found with id: " + id);
        }
        transferMoneyRepository.deleteById(id);
    }

    public TransferMoneyDTO updateTransferMoney(Long id, CreateTransferMoneyDTO createTransferMoneyDTO) {
        TransferMoney transferMoney = transferMoneyRepository.findById(id)
                .orElseThrow(() -> new TransferNotFoundException("Transfer not found with id: " + id));

        transferMoney.setSenderAccountId(createTransferMoneyDTO.getSenderAccountId());
        transferMoney.setRecipientAccountId(createTransferMoneyDTO.getRecipientAccountId());
        transferMoney.setAmount(createTransferMoneyDTO.getAmount());
        transferMoney.setTransferDate(createTransferMoneyDTO.getTransferDate());
        transferMoney.setTransferStatus(createTransferMoneyDTO.getTransferStatus());

        TransferMoney updatedTransferMoney = transferMoneyRepository.save(transferMoney);

        return new TransferMoneyDTO(
                updatedTransferMoney.getId(),
                updatedTransferMoney.getSenderAccountId(),
                updatedTransferMoney.getRecipientAccountId(),
                updatedTransferMoney.getAmount(),
                updatedTransferMoney.getTransferDate(),
                updatedTransferMoney.getTransferStatus()
        );
    }

    public TransferMoneyDTO transferMoney(CreateTransferMoneyDTO createTransferMoneyDTO) {
        TransferMoney transferMoney = new TransferMoney();
        transferMoney.setSenderAccountId(createTransferMoneyDTO.getSenderAccountId());
        transferMoney.setRecipientAccountId(createTransferMoneyDTO.getRecipientAccountId());
        transferMoney.setAmount(createTransferMoneyDTO.getAmount());
        transferMoney.setTransferDate(createTransferMoneyDTO.getTransferDate());
        transferMoney.setTransferStatus(createTransferMoneyDTO.getTransferStatus());

        TransferMoney savedTransferMoney = transferMoneyRepository.save(transferMoney);

        return new TransferMoneyDTO(
                savedTransferMoney.getId(),
                savedTransferMoney.getSenderAccountId(),
                savedTransferMoney.getRecipientAccountId(),
                savedTransferMoney.getAmount(),
                savedTransferMoney.getTransferDate(),
                savedTransferMoney.getTransferStatus()
        );
    }
}
