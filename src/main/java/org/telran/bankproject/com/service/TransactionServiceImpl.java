package org.telran.bankproject.com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telran.bankproject.com.entity.Account;
import org.telran.bankproject.com.entity.Transaction;
import org.telran.bankproject.com.enums.Type;
import org.telran.bankproject.com.repository.TransactionRepository;
import org.telran.bankproject.com.service.converter.currency.CurrencyConverter;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountService accountService;

    @Override
    public List<Transaction> getAll() {
        log.debug("Call method findAll");
        List<Transaction> all = transactionRepository.findAll();

        log.debug("Method findAll returned List with size {}", all.size());
        return all;
    }

    @Override
    public Transaction getById(long id) {
        transactionRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Transaction with id %d not found", id)));

        log.debug("Call method getReferenceById with id {}", id);
        return transactionRepository.getReferenceById(id);
    }

    @Override
    public Transaction add(Transaction transaction) {
        log.debug("Call method save with transaction {}", transaction);
        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public Transaction transfer(String iban1, String iban2, double amount) {
        Account debitAccount = accountService.getByIban(iban1);
        Account creditAccount = accountService.getByIban(iban2);

        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!login.equals(debitAccount.getClient().getLogin()))
            throw new UnsupportedOperationException("The operation is allowed to be carried out only on own accounts");

        if (debitAccount.getBalance().compareTo(BigDecimal.valueOf(amount)) < 0) {
            Transaction transaction = new Transaction(0, debitAccount, creditAccount, Type.FAILED,
                    amount, "Failed", new Timestamp(System.currentTimeMillis()));
            log.debug("Call method save with transaction {}", transaction);
            return transactionRepository.save(transaction);
        }

        debitAccount.setBalance(debitAccount.getBalance().subtract(BigDecimal.valueOf(amount)));
        creditAccount.setBalance(creditAccount.getBalance().add(CurrencyConverter
                .convert(debitAccount, creditAccount, BigDecimal.valueOf(amount))));
        debitAccount.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        creditAccount.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        log.debug("Call method add with account {}", debitAccount);
        accountService.add(debitAccount);
        log.debug("Call method add with account {}", creditAccount);
        accountService.add(creditAccount);

        Transaction transaction = new Transaction(0, debitAccount, creditAccount, Type.SUCCESSFUL,
                amount, "Successful", new Timestamp(System.currentTimeMillis()));
        log.debug("Call method save with transaction {}", transaction);
        return transactionRepository.save(transaction);
    }

    @Override
    public void remove(Transaction transaction) {
        Transaction entity = getById(transaction.getId());
        log.debug("Call method deleteAllByIdInBatch with transaction id {}", transaction.getId());
        transactionRepository.delete(entity);
    }
}