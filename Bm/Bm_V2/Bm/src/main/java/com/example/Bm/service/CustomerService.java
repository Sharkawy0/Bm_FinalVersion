package com.example.Bm.service;

import com.example.Bm.dto.CustomerDTO;
import com.example.Bm.dto.UpdateCustomerDTO;
import com.example.Bm.dto.ViewCustomerDTO;
import com.example.Bm.exception.custom.CustomerNotFoundException;
import com.example.Bm.model.Customer;
import com.example.Bm.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomer {


    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;


    @Override
    public void deleteCustomer(Long id) throws CustomerNotFoundException {

        Customer customer = getCustomerByCustomerId(id);

        log.info("Deleting customer with id:{} " , customer.getId());

        this.customerRepository.delete(customer);

    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String updateCustomer(String email, UpdateCustomerDTO updateCustomerDTO) throws CustomerNotFoundException {
        // Find the customer by email
        Customer customer = customerRepository.findUserByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with email: " + email));

        // Update the customer's phone number
        customer.setPhoneNumber(updateCustomerDTO.getPhoneNumber());
        customer.setName(updateCustomerDTO.getName());
        customer.setLastName(updateCustomerDTO.getLastName());
        customer.setPhoneNumber(updateCustomerDTO.getPhoneNumber());


        // Save the updated customer back to the repository
        customerRepository.save(customer);

        // Return success message
        return "Saved successfully";
    }



    private Customer getCustomerByCustomerId(Long id) throws CustomerNotFoundException {
        Customer customer = this.customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException(String.format("Customer with Id %s not found" , id)));
        return customer;
    }

    @Override
    public CustomerDTO getCustomerById(Long id) throws CustomerNotFoundException{
        Customer customer = getCustomerByCustomerId(id);

        return customer.toDTO();


    }



    @Override
    public ViewCustomerDTO getLastCustomerAdded() {
        return this.customerRepository.findFirstByOrderByCreateTimeStampDesc();
    }

    @Override
    public List<Customer> getCustomerList() {
        return this.customerRepository.findAll();
    }

    @Override
    public CustomerDTO getCustomerByEmail(String email) throws CustomerNotFoundException {
        Customer customer = customerRepository.getCustomerByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with email: " + email));

        return customer.toDTO();
    }

}
