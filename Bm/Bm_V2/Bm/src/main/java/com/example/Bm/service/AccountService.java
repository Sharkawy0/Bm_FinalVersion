package com.example.Bm.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountService {

    private static final int MIN_BALANCE = 0;
    private static final int MAX_BALANCE = 10000000;
    private String accountId;


//    @Autowired
//    private AccountRepository accountRepository;
//
//    public Account findByUsername(String username) {
//        return accountRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("Account not found for username: " + username));
//    }


    public int getRandomBalance() {
        Random random = new Random();
        return MIN_BALANCE + random.nextInt(MAX_BALANCE - MIN_BALANCE + 1);
    }


//    public String generateAccountId() {
//        Random random = new Random();
//        // Generate an 8-digit number
//        int id = 10000000 + random.nextInt(90000000);
//        return String.valueOf(id);
//    }

//
//
//    public String generateRandomId() {
//        Random random = new Random();
//        int randomNumber = 10000000 + random.nextInt(90000000);
//        return String.valueOf(randomNumber);
//    }

}