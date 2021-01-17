package cn.az.code.converter;

import cn.hutool.core.convert.Converter;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author az
 * @since 09/01/20
 */
public class LocalDateTimeStringConverter implements Converter<LocalDateTime> {

    @Override
    public LocalDateTime convert(Object value, LocalDateTime defaultValue) throws IllegalArgumentException {
        return Objects.nonNull(value) ? LocalDateTime.parse(value.toString()) : LocalDateTime.now();
    }
}
