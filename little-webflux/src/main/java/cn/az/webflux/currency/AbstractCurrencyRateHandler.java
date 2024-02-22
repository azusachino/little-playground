package cn.az.webflux.currency;

import org.springframework.web.client.RestTemplate;

public abstract class AbstractCurrencyRateHandler implements CurrencyRateHandler {

    protected RestTemplate restTemplate;

    protected AbstractCurrencyRateHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
