package com.example.Bm.model;

import com.example.Bm.dto.CustomerDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Column(nullable = false)
    private  String name;

    @Column
    private  String lastName;

    @Column(nullable = false , unique = true)
    private   String email;

    @Column(nullable = true , unique = true)
    @Size(max=12)
    private  String phoneNumber;

    @Column
    private  String password;

    @CreationTimestamp
    private LocalDateTime createTimeStamp;

    @OneToOne(fetch = FetchType.EAGER)
    private Account account;

    public CustomerDTO toDTO() {
        return CustomerDTO.builder()
                .name(this.name)
                .email(this.email)
                .phoneNumber(this.phoneNumber)

//                .account(this.account.toDTO())
                .build();

    }


}
