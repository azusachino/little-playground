package cn.az.webflux.currency.impl;

import cn.az.webflux.currency.AbstractCurrencyRateHandler;
import cn.az.webflux.currency.dto.CurrencyRate;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NoopCurrencyRateHandler extends AbstractCurrencyRateHandler {

    public NoopCurrencyRateHandler(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public CurrencyRate fetchCurrencyRate() {
        return null;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
