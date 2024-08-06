package com.example.Bm.repository;


import com.example.Bm.model.TransferMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferMoneyRepository extends JpaRepository<TransferMoney, Long> {
}
