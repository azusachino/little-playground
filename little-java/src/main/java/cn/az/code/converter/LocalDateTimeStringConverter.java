package cn.az.code.converter;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.core.convert.converter.Converter;

/**
 * @author az
 * @since 09/01/20
 */
public class LocalDateTimeStringConverter implements Converter<LocalDateTime, String> {

    @Override
    public String convert(LocalDateTime source) {
        return Objects.isNull(source) ? null : source.toString();
    }
}
