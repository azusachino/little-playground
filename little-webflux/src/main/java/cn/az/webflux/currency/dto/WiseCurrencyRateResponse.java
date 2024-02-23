package cn.az.webflux.currency.dto;

import java.math.BigDecimal;

public class WiseCurrencyRateResponse {
    private float sourceAmount;
    private boolean guaranteedTargetAmountAllowed;
    private boolean targetAmountAllowed;
    private String rateTimestamp;
    private String clientId;
    private String expirationTime;
    private String type;
    private String status;
    private BigDecimal rate;
    private String sourceCurrency;
    private String targetCurrency;
    private String createdTime;
    private String rateType;
    private String rateExpirationTime;
    private String payOut;
    private Boolean guaranteedTargetAmount;
    private String providedAmountType;
    private String funding;

    public float getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(float sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public boolean isGuaranteedTargetAmountAllowed() {
        return guaranteedTargetAmountAllowed;
    }

    public void setGuaranteedTargetAmountAllowed(boolean guaranteedTargetAmountAllowed) {
        this.guaranteedTargetAmountAllowed = guaranteedTargetAmountAllowed;
    }

    public boolean isTargetAmountAllowed() {
        return targetAmountAllowed;
    }

    public void setTargetAmountAllowed(boolean targetAmountAllowed) {
        this.targetAmountAllowed = targetAmountAllowed;
    }

    public String getRateTimestamp() {
        return rateTimestamp;
    }

    public void setRateTimestamp(String rateTimestamp) {
        this.rateTimestamp = rateTimestamp;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getRateExpirationTime() {
        return rateExpirationTime;
    }

    public void setRateExpirationTime(String rateExpirationTime) {
        this.rateExpirationTime = rateExpirationTime;
    }

    public String getPayOut() {
        return payOut;
    }

    public void setPayOut(String payOut) {
        this.payOut = payOut;
    }

    public Boolean getGuaranteedTargetAmount() {
        return guaranteedTargetAmount;
    }

    public void setGuaranteedTargetAmount(Boolean guaranteedTargetAmount) {
        this.guaranteedTargetAmount = guaranteedTargetAmount;
    }

    public String getProvidedAmountType() {
        return providedAmountType;
    }

    public void setProvidedAmountType(String providedAmountType) {
        this.providedAmountType = providedAmountType;
    }

    public String getFunding() {
        return funding;
    }

    public void setFunding(String funding) {
        this.funding = funding;
    }
}
