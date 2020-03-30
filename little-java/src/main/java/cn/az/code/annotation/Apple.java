package cn.az.code.annotation;

import lombok.Data;

/**
 * @author az
 * @date 2020-03-06
 */
@Data
public class Apple {

    @FruitProvider(id = 1, name = "a", address = "beijing")
    private String appleProvider;
}
