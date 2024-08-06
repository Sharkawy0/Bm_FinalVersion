package com.example.Bm.service;

import com.example.Bm.dto.CreateCustomerDTO;
import com.example.Bm.dto.CustomerDTO;
import com.example.Bm.dto.LoginRequestDTO;
import com.example.Bm.dto.LoginResponseDTO;
import com.example.Bm.exception.custom.CustomerAlreadyExistsException;
import com.example.Bm.model.Customer;
import com.example.Bm.repository.AccountRepository;
import com.example.Bm.repository.CustomerRepository;
import com.example.Bm.service.security.CustomerDetailsImpl;
import com.example.Bm.service.security.IAuthenticator;
import com.example.Bm.service.security.JWTUtils;
import com.example.Bm.service.security.TokenBlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticatorService implements IAuthenticator {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;
    private final TokenBlacklistService tokenBlacklistService;

    @Override
    @Transactional
    public CustomerDTO register(CreateCustomerDTO createCustomerDTO) throws CustomerAlreadyExistsException {
        if (this.customerRepository.existsByEmail(createCustomerDTO.getEmail())) {
            throw new CustomerAlreadyExistsException(String.format("Customer with email %s already exists", createCustomerDTO.getEmail()));
        }

        Customer customer = Customer.builder()
                .email(createCustomerDTO.getEmail())
                .name(createCustomerDTO.getName())
                .password(this.encoder.encode(createCustomerDTO.getPassword()))
                .build();

        Customer savedCustomer = this.customerRepository.save(customer);

        CustomerDTO customerDTO = savedCustomer.toDTO();
        customerDTO.setMessage("Register successful");

        return customerDTO;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomerDetailsImpl customerDetails = (CustomerDetailsImpl) authentication.getPrincipal();
        return LoginResponseDTO.builder()
                .token(jwt)
                .message("Login successful")
                .status(HttpStatus.ACCEPTED)
                .tokenType("Bearer")
                .build();
    }

    @Override
    public void logout(String token) {
        jwtUtils.invalidateJwtToken(token);
        SecurityContextHolder.clearContext();
    }



}
