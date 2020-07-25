package cn.az.code.annotation;

import java.lang.reflect.Field;

/**
 * @author az
 * @date 2020-03-06
 */
public class FruitInfoUtil {

    protected FruitInfoUtil() {
    }

    public static void getFruitInfo(Class<?> clazz) {
        StringBuilder strFruitProvider = new StringBuilder("provider: ");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FruitProvider.class)) {
                FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class);
                strFruitProvider.append(fruitProvider.id()).append(",").append(fruitProvider.name()).append(",").append(fruitProvider.address());
                System.out.println("strFruitProvider = " + strFruitProvider);
            }
        }
    }
}
