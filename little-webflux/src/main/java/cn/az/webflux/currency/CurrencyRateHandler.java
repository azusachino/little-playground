package cn.az.webflux.currency;

import cn.az.webflux.currency.dto.CurrencyRate;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;

public interface CurrencyRateHandler extends Ordered {

    @Nullable
    CurrencyRate fetchCurrencyRate();
}
