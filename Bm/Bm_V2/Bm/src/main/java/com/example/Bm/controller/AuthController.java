package com.example.Bm.controller;


import com.example.Bm.dto.*;
import com.example.Bm.exception.custom.CustomerAlreadyExistsException;
import com.example.Bm.exception.response.ErrorDetails;
import com.example.Bm.service.security.IAuthenticator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
@Tag(name= "Customer Auth Controller", description = "Customer Auth controller")
public class AuthController {

    private final IAuthenticator authenticatorService;


    @Operation(summary = "Account Creation")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = CustomerDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @PostMapping("/register")

    public RegisterResponseDTO register(@RequestBody @Valid CreateCustomerDTO createCustomerDTO) throws CustomerAlreadyExistsException {
        CustomerDTO customerDTO = this.authenticatorService.register(createCustomerDTO);

        RegisterResponseDTO response = new RegisterResponseDTO();
        response.setSuccess(true);
        response.setMessage("Register successful");

        response.setCustomer(customerDTO);

        return response;
    }


    @Operation(summary = "Account Login")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = LoginResponseDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO loginRequestDTO)  {
        return this.authenticatorService.login(loginRequestDTO);
    }



}
