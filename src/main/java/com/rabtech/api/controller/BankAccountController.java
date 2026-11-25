
package com.rabtech.api.controller;

import com.rabtech.api.dto.DepositRequest;
import com.rabtech.api.entity.BankAccount;
import com.rabtech.api.service.BankAccountService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

    private final BankAccountService accountService;

    public BankAccountController(BankAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Map<String, Object> createAccount(Authentication authentication) {
        BankAccount account =
                accountService.createAccount(authentication.getName());

        return Map.of(
                "accountNumber", account.getAccountNumber(),
                "balance", account.getBalance(),
                "message", "Bank account created successfully"
        );
    }

    @GetMapping("/me")
    public Map<String, Object> getMyAccount(Authentication authentication) {
        BankAccount account =
                accountService.getAccount(authentication.getName());

        return Map.of(
                "accountNumber", account.getAccountNumber(),
                "balance", account.getBalance()
        );
    }

    @PostMapping("/deposit")
    public Map<String, Object> deposit(
            Authentication authentication,
            @Valid @RequestBody DepositRequest request) {

        BankAccount account = accountService.deposit(
                authentication.getName(),
                request.getAmount()
        );

        return Map.of(
                "message", "Deposit successful",
                "accountNumber", account.getAccountNumber(),
                "balance", account.getBalance()
        );
    }
}
