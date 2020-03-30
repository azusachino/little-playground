package cn.az.code.optional;

import lombok.Data;

import java.util.Optional;

/**
 * @author Liz
 * @date 1/9/2020
 */
@Data
public class Car {

    private String name;
    private String val;

    private Optional<Insurance> insurance;
}
