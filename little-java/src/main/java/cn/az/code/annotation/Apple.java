package cn.az.code.annotation;


/**
 * @author az
 * @date 2020-03-06
 */
public class Apple {

    @FruitProvider(id = 1, name = "苹果", address = "北京")
    private String appleProvider;

    @FruitProvider(id = 2, name = "橘子", address = "合肥")
    private String orangeProvider;

}
