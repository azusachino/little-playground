package cn.az.webflux.currency;

import cn.az.webflux.currency.dto.CurrencyRate;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DelegateCurrencyRateHandler {

    private final CurrencyRateHandlerChain chain;

    public DelegateCurrencyRateHandler(List<CurrencyRateHandler> handlers) {
        this.chain = new CurrencyRateHandlerChain(handlers);
    }

    public Pair<CurrencyRate, Boolean> getCurrencyRate() {
        return this.chain.getCurrencyRate();
    }
}
