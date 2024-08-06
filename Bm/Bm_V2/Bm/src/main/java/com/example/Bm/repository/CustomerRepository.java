package com.example.Bm.repository;

import com.example.Bm.dto.ViewCustomerDTO;
import com.example.Bm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    //    Optional<Customer> findCustomerByUserNameEquals(String username);
    ViewCustomerDTO findCustomerByName(String name);
    ViewCustomerDTO findFirstByOrderByCreateTimeStampDesc();

    boolean existsByEmail(String email);


    Optional<Customer> findUserByEmail(String email);

    Optional<Customer> getCustomerByEmail(String email);
}
