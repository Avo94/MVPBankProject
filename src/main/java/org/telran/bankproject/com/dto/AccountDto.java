package org.telran.bankproject.com.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import org.telran.bankproject.com.enums.CurrencyCode;
import org.telran.bankproject.com.enums.Status;
import org.telran.bankproject.com.enums.Type;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {

    private long id;
    private ClientDto client;
    private AgreementDto agreement;
    private List<TransactionDto> debitTransactions;
    private List<TransactionDto> creditTransactions;
    private String name;
    private String iban;
    @Schema(description = "STANDARD of PREMIUM",defaultValue = "STANDARD")
    private Type type;
    @Schema(description = "ACTIVE of INACTIVE", defaultValue = "ACTIVE")
    private Status status;
    private BigDecimal balance;
    @Schema(description = "USD, UER or CHF",defaultValue = "USD")
    private CurrencyCode currencyCode;

    public AccountDto(long id, ClientDto client, AgreementDto agreement, List<TransactionDto> debitTransactions,
                      List<TransactionDto> creditTransactions, String name, String iban, Type type, Status status,
                      BigDecimal balance, CurrencyCode currencyCode) {
        this.id = id;
        this.client = client;
        this.agreement = agreement;
        this.debitTransactions = debitTransactions;
        this.creditTransactions = creditTransactions;
        this.name = name;
        this.iban = iban;
        this.type = type;
        this.status = status;
        this.balance = balance;
        this.currencyCode = currencyCode;
    }

    public AccountDto() {
        //
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public AgreementDto getAgreement() {
        return agreement;
    }

    public void setAgreement(AgreementDto agreement) {
        this.agreement = agreement;
    }

    public List<TransactionDto> getDebitTransactions() {
        return debitTransactions;
    }

    public void setDebitTransactions(List<TransactionDto> debitTransactions) {
        this.debitTransactions = debitTransactions;
    }

    public List<TransactionDto> getCreditTransactions() {
        return creditTransactions;
    }

    public void setCreditTransactions(List<TransactionDto> creditTransactions) {
        this.creditTransactions = creditTransactions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }
}