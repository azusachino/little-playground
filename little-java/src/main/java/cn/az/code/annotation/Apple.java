package cn.az.code.annotation;


/**
 * @author az
 * @date 2020-03-06
 */
public class Apple {

    @FruitProvider(id = 1, name = "a", address = "beijing")
    private String appleProvider;

    public String getAppleProvider() {
        return appleProvider;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }
}
