package cn.az.webflux.currency.impl;

import cn.az.webflux.currency.AbstractCurrencyRateHandler;
import cn.az.webflux.currency.dto.CurrencyRate;
import cn.az.webflux.currency.dto.WiseCurrencyRateResponse;
import cn.az.webflux.currency.dto.WiseFormData;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class AnotherWiseCurrencyRateHandler extends AbstractCurrencyRateHandler {

    private static final String ENDPOINT = "https://wise.com/gateway/v3/quotes";

    private final WebClient webClient;

    public AnotherWiseCurrencyRateHandler(RestTemplate restTemplate, WebClient webClient) {
        super(restTemplate);
        this.webClient = webClient;
    }

    @Override
    public CurrencyRate fetchCurrencyRate() {
        var defaultFormData = WiseFormData.getDefault();
        var optional = this.webClient.post()
            .uri(ENDPOINT)
            .bodyValue(defaultFormData)
            .exchangeToMono(cr -> {
                if (cr.statusCode().is2xxSuccessful()) {
                    return cr.bodyToMono(WiseCurrencyRateResponse.class);
                } else {
                    return Mono.empty();
                }
            }).blockOptional();

        if (optional.isPresent()) {
            var rate = new CurrencyRate();
            rate.setBase(defaultFormData.getSourceCurrency());
            rate.setQuote(defaultFormData.getTargetCurrency());
            rate.setValue(optional.get().getRate());
            rate.setLocalDateTime(LocalDateTime.now());
            return rate;
        }
        return null;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
