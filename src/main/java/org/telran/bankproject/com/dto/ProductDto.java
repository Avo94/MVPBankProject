package org.telran.bankproject.com.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import org.telran.bankproject.com.enums.CurrencyCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    private long id;
    private ManagerDto manager;
    private AgreementDto agreement;
    private String name;
    @Schema(description = "USD, UER or CHF",defaultValue = "USD")
    private CurrencyCode currencyCode;
    @Schema(description = "8 or 13", defaultValue = "8")
    private double interestRate;
    private int productLimit;

    public ProductDto(long id, ManagerDto manager, AgreementDto agreement, String name,
                      CurrencyCode currencyCode, double interestRate, int productLimit) {
        this.id = id;
        this.manager = manager;
        this.agreement = agreement;
        this.name = name;
        this.currencyCode = currencyCode;
        this.interestRate = interestRate;
        this.productLimit = productLimit;
    }

    public ProductDto() {
        //
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ManagerDto getManager() {
        return manager;
    }

    public void setManager(ManagerDto manager) {
        this.manager = manager;
    }

    public AgreementDto getAgreement() {
        return agreement;
    }

    public void setAgreement(AgreementDto agreement) {
        this.agreement = agreement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getProductLimit() {
        return productLimit;
    }

    public void setProductLimit(int productLimit) {
        this.productLimit = productLimit;
    }
}