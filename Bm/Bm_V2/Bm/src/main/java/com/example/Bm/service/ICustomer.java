package com.example.Bm.service;

import com.example.Bm.dto.CustomerDTO;
import com.example.Bm.dto.UpdateCustomerDTO;
import com.example.Bm.dto.ViewCustomerDTO;
import com.example.Bm.exception.custom.CustomerNotFoundException;
import com.example.Bm.model.Customer;

import java.util.List;

public interface ICustomer {
    /**
     * update customer details
     *
     * @param email             customer email
     * @param updateCustomerDTO customer details
     * @return updated customer
     * @throws CustomerNotFoundException if customer not found
     */


    String updateCustomer(String email , UpdateCustomerDTO updateCustomerDTO ) throws CustomerNotFoundException;


    /**
     * Delete customer
     *
     * @param id
     * @throws CustomerNotFoundException
     */
    void deleteCustomer(Long id) throws CustomerNotFoundException;

    /**
     * get customer by id
     *
     * @param id
     * @return customer details
     * @throws CustomerNotFoundException
     */
    CustomerDTO getCustomerById(Long id) throws CustomerNotFoundException;

//    CustomerDTO getCustomerByUserName(String username);

    /**
     * get last customer added
     *
     * @return customer details
     */
    ViewCustomerDTO getLastCustomerAdded();


    /**
     * get all customers
     * @return
     */
    List<Customer> getCustomerList();

    CustomerDTO getCustomerByEmail(String email) throws CustomerNotFoundException;
}
