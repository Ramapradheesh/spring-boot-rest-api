
package com.rabtech.api.service;

import com.rabtech.api.entity.AppUser;
import com.rabtech.api.entity.BankAccount;
import com.rabtech.api.repository.BankAccountRepository;
import com.rabtech.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class BankAccountService {

    private final BankAccountRepository accountRepository;
    private final UserRepository userRepository;

    public BankAccountService(
            BankAccountRepository accountRepository,
            UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public BankAccount createAccount(String username) {

        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (accountRepository.findByUserId(user.getId()).isPresent()) {
            throw new RuntimeException("Account already exists");
        }

        BankAccount account = new BankAccount();

        account.setAccountNumber(
                "RP" + UUID.randomUUID().toString()
                        .replace("-", "").substring(0, 10).toUpperCase()
        );

        account.setBalance(BigDecimal.ZERO);
        account.setUser(user);

        return accountRepository.save(account);
    }

    public BankAccount getAccount(String username) {

        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return accountRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Bank account not found"));
    }


public BankAccount deposit(String username, BigDecimal amount) {

    if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
        throw new IllegalArgumentException("Deposit amount must be positive");
    }

    BankAccount account = getAccount(username);
    account.setBalance(account.getBalance().add(amount));

    return accountRepository.save(account);
}

}
