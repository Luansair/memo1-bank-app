package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {
 // solo va a crear las transacciones y guardarlas en la base de datos. Nada mas
    @Autowired TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Collection<Transaction> getTransactionsByCbu(Long cbu) {
        return transactionRepository.findTransactionByCbu(cbu);
    }

    public Transaction getTransaction(Long id) {
        return transactionRepository.findTransactionById(id);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
