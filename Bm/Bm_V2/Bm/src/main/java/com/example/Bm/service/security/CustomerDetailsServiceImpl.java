package com.example.Bm.service.security;

import com.example.Bm.model.Customer;
import com.example.Bm.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Customer customer = customerRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found with email: " + email));

        return CustomerDetailsImpl.builder()
                .email(customer.getEmail())
                .password(customer.getPassword())
                .name(customer.getName())
                .phoneNumber(customer.getPhoneNumber()) // Set phone number
                .build();
    }
}
