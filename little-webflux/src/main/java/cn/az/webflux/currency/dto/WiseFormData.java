package cn.az.webflux.currency.dto;

public class WiseFormData {
    private Integer sourceAmount;
    private String sourceCurrency;
    private String targetCurrency;
    private Boolean guaranteedTargetAmount;
    private String type;

    public static WiseFormData getDefault() {
        var formData = new WiseFormData();
        formData.setSourceAmount(1);
        formData.setSourceCurrency("USD");
        formData.setTargetCurrency("JPY");
        formData.setGuaranteedTargetAmount(Boolean.FALSE);
        formData.setType("REGULAR");
        return formData;
    }

    public Integer getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(Integer sourceAmount) {
        this.sourceAmount = sourceAmount;
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

    public Boolean getGuaranteedTargetAmount() {
        return guaranteedTargetAmount;
    }

    public void setGuaranteedTargetAmount(Boolean guaranteedTargetAmount) {
        this.guaranteedTargetAmount = guaranteedTargetAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
