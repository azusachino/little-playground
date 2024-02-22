package cn.az.webflux.currency.impl;

import cn.az.webflux.currency.AbstractCurrencyRateHandler;
import cn.az.webflux.currency.dto.CurrencyRate;
import cn.az.webflux.currency.dto.WiseCurrencyRateResponse;
import cn.az.webflux.currency.dto.WiseFormData;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class WiseCurrencyRateHandler extends AbstractCurrencyRateHandler {

    private static final String ENDPOINT = "https://wise.com/gateway/v3/quotes";

    public WiseCurrencyRateHandler(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public CurrencyRate fetchCurrencyRate() {
        var defaultFormData = WiseFormData.getDefault();
        var httpEntity = new HttpEntity<>(defaultFormData);
        var result = this.restTemplate.exchange(ENDPOINT, HttpMethod.POST, httpEntity, WiseCurrencyRateResponse.class);
        if (result.getStatusCode().is2xxSuccessful()) {
            var rate = new CurrencyRate();
            rate.setBase(defaultFormData.getSourceCurrency());
            rate.setQuote(defaultFormData.getTargetCurrency());
            rate.setValue(Objects.requireNonNull(result.getBody()).getRate());
            rate.setLocalDateTime(LocalDateTime.now());
            return rate;
        }
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
