package cn.az.webflux.aeron;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Replicated State Machine
 */
public class RSM {

    private static final Logger LOGGER = LoggerFactory.getLogger(RSM.class);
    private final AtomicInteger currentValue = new AtomicInteger(0);

    public void add(long correlation, int value) {
        this.currentValue.getAndAdd(value);
        LOGGER.info("adding {}, value is now {}; correlation = {}", value, currentValue, correlation);
    }

    public void setCurrentValue(final long correlation, final int newValue) {
        this.currentValue.set(newValue);
        LOGGER.info("setting value to {}; correlation = {}", newValue, correlation);
    }

    public void loadFromSnapshot(final int currentValue) {
        this.currentValue.set(currentValue);
        LOGGER.info("reading snapshot with current value at {}", currentValue);
    }

    public int getCurrentValue() {
        return this.currentValue.get();
    }

}
