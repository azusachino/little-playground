package cn.az.webflux.currency;

import cn.az.webflux.currency.dto.CurrencyRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Objects;

/**
 * Currency Handler Chain
 */
public class CurrencyRateHandlerChain {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyRateHandlerChain.class);

    private final int index;
    private final List<CurrencyRateHandler> handlers;

    public CurrencyRateHandlerChain(List<CurrencyRateHandler> handlers) {
        this.handlers = handlers;
        // ensure handler order
        AnnotationAwareOrderComparator.sort(this.handlers);
        this.index = 0;
    }

    private CurrencyRateHandlerChain(CurrencyRateHandlerChain parent, int index) {
        this.handlers = parent.getHandlers();
        this.index = index;
    }

    public List<CurrencyRateHandler> getHandlers() {
        return this.handlers;
    }

    public Pair<CurrencyRate, Boolean> getCurrencyRate() {
        if (this.index < this.handlers.size()) {
            var handler = this.handlers.get(this.index);
            CurrencyRate result = null;
            String simpleName = handler.getClass().getSimpleName();

            LOGGER.info("handler {} started", simpleName);
            try {
                result = handler.fetchCurrencyRate();
            } catch (Exception e) {
                LOGGER.error("error", e);
            }
            LOGGER.info("handler {} finished", simpleName);

            if (Objects.nonNull(result)) {
                LOGGER.info("we got the data from {}", handler.getClass().getSimpleName());
                return Pair.of(result, Boolean.TRUE);
            } else {
                var next = new CurrencyRateHandlerChain(this, this.index + 1);
                return next.getCurrencyRate();
            }
        } else {
            LOGGER.warn("we got no data this round");
            return Pair.of(CurrencyRate.getInstance(), Boolean.FALSE);
        }
    }
}
