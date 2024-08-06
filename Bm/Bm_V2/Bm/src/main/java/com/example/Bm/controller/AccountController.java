package com.example.Bm.controller;

import com.example.Bm.dto.CustomerDTO;
import com.example.Bm.exception.response.ErrorDetails;
import com.example.Bm.service.security.CustomerDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AccountController {

    @GetMapping("/balance")
    @Operation(summary = "Retrieves the account balance and details for the authenticated user")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = CustomerDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    public ResponseEntity<Map<String, Object>> getBalance() {
        // Get authenticated user's details
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof CustomerDetailsImpl) {
            CustomerDetailsImpl userDetails = (CustomerDetailsImpl) principal;
            String username = userDetails.getUsername();
            String name = userDetails.getName();
            String lastName = userDetails.getLastName();
            String phoneNumber = userDetails.getPhoneNumber();
            int balance = 1000;

            Map<String, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("name", name);
            response.put("balance", balance);
            response.put("lastName", lastName);
            response.put("phoneNumber", phoneNumber);

            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
}
