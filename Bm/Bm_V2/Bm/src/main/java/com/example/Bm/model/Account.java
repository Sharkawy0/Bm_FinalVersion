package com.example.Bm.model;

import com.example.Bm.dto.AccountDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int balance=1000;

    public AccountDTO toDTO() {
        return AccountDTO.builder()
                .id(this.id)
                .balance(this.balance)
                .build();
    }

}
