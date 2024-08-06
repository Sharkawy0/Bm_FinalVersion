package com.example.Bm.controller;

import com.example.Bm.dto.CustomerDTO;
import com.example.Bm.dto.UpdateCustomerDTO;
import com.example.Bm.dto.ViewCustomerDTO;
import com.example.Bm.exception.custom.CustomerNotFoundException;
import com.example.Bm.exception.response.ErrorDetails;
import com.example.Bm.model.Customer;
import com.example.Bm.service.ICustomer;
import com.example.Bm.service.security.JWTUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Validated
@Tag(name = "Customer controller", description = "Customer controller")
public class CustomerController {

    private final ICustomer customerService;

    @Autowired
    private JWTUtils jwtUtils;

//    @Operation(summary = "Get customer by ID")
//    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = CustomerDTO.class), mediaType = "application/json")})
//    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
//    @GetMapping("/{id}")
//    public CustomerDTO getCustomerById(@PathVariable long id) throws CustomerNotFoundException {
//        return customerService.getCustomerById(id);
//    }


    @Operation(summary = "Get last customer")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = CustomerDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @GetMapping("/last")
    public ViewCustomerDTO getLastCustomer(){
        return customerService.getLastCustomerAdded();

    }

    @Operation(summary = "Get all customers")

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getCustomerList();
    }



//    @Operation(summary = "Delete Customer by ID")
//    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = CustomerDTO.class), mediaType = "application/json")})
//    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
//    @DeleteMapping("/{id}")
//    public void deleteCustomer(@PathVariable long id) throws CustomerNotFoundException {
//        customerService.deleteCustomer(id);
//    }


    @Operation(summary = "Update Customer by email")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Customer.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @PutMapping("/update/{email}")
    public ResponseEntity<Map<String, String>> updateCustomer(@PathVariable String email, @RequestBody UpdateCustomerDTO updateCustomerDTO) throws CustomerNotFoundException {
        String responseMessage = customerService.updateCustomer(email, updateCustomerDTO);

        Map<String, String> response = new HashMap<>();
        response.put("message", responseMessage);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


//    @GetMapping("/email/{email}")
//    public CustomerDTO getCustomerByEmail(@PathVariable String email) throws CustomerNotFoundException {
//        return customerService.getCustomerByEmail(email);
//    }



}
