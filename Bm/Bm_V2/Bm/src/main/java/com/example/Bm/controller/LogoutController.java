package com.example.Bm.controller;

import com.example.Bm.dto.CustomerDTO;
import com.example.Bm.exception.response.ErrorDetails;
import com.example.Bm.service.security.TokenBlacklistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
@Tag(name = "Logout Management", description = "API for handling user logout")
public class LogoutController {

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @PostMapping("/logout")
    @Operation(summary = "user logout")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = CustomerDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    public ResponseEntity<Map<String, String>> logout(@RequestHeader(value = "Authorization", required = false) String tokenAuthHeader) {
        Map<String, String> response = new HashMap<>();

        if (tokenAuthHeader == null || !tokenAuthHeader.startsWith("Bearer ")) {
            response.put("message", "Invalid or missing tokenAuth header");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        String token = tokenAuthHeader.substring(7);

        if (token.isEmpty() || tokenBlacklistService.isTokenBlacklisted(token)) {
            response.put("message", "Invalid or already logged out token");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        tokenBlacklistService.blacklistToken(token);

        // Clear SecurityContext
        SecurityContextHolder.clearContext();

        response.put("message", "Logout successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

