package com.example.Bm.service.security;

import com.example.Bm.dto.CreateCustomerDTO;
import com.example.Bm.dto.CustomerDTO;
import com.example.Bm.dto.LoginRequestDTO;
import com.example.Bm.dto.LoginResponseDTO;
import com.example.Bm.exception.custom.CustomerAlreadyExistsException;

public interface IAuthenticator {
    /**
     * Account Creation
     * @param createCustomerDTO customer details
     * @return registered customer
     * @throws CustomerAlreadyExistsException if customer already exist
     */
    CustomerDTO register(CreateCustomerDTO createCustomerDTO) throws CustomerAlreadyExistsException;

    /**
     * login a customer
     *
     * @param loginRequestDTO login details
     * @return login response
     */
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO) ;

    void logout(String token);
}
