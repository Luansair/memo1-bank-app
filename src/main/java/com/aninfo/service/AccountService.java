package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    final double MIN_BALANCE_FOR_PROMO = 2000;
    final double MAX_BENEFIT_FROM_PROMO = 500;
    final double EXTRA_PERCENT = 0.1;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long cbu) {
        return accountRepository.findById(cbu);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void deleteById(Long cbu) {
        accountRepository.deleteById(cbu);
    }

    @Transactional
    public Account withdraw(Long cbu, Double sum) {
        Account account = accountRepository.findAccountByCbu(cbu);

        if (account.getBalance() < sum) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - sum);
        accountRepository.save(account);

        return account;
    }

    @Transactional
    public Account deposit(Long cbu, Double sum) {

        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }
        Account account = accountRepository.findAccountByCbu(cbu);

        double profit = 0.0;
        if (sum >= MIN_BALANCE_FOR_PROMO) {
            double potential_profit = sum * EXTRA_PERCENT;
            double profit_limit = MAX_BENEFIT_FROM_PROMO - account.getPromoProfit();
            if (potential_profit > profit_limit) {
                profit = profit_limit;
            } else {
                profit = potential_profit;
            }
        }
        account.setPromoProfit(account.getPromoProfit() + profit);
        account.setBalance(account.getBalance() + sum + profit);
        accountRepository.save(account);

        return account;
    }

}
