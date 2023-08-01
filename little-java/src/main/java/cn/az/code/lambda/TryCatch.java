package cn.az.code.lambda;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Liz
 */
@Slf4j
public class TryCatch {

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(3, 9, 4, 2, 0, 41);
        // 1. try...catch
        integerList.forEach(i -> {
            try {
                System.out.println(50 / i);
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            }
        });

        // 2. lambdaWrapper
        integerList.forEach(LambdaWrapper.lambdaWrapper(i -> System.out.println(50 / i)));

        // 3. specific Exception
        integerList.forEach(
                LambdaWrapper.lambdaWrapper(integer -> log.error(String.valueOf(integer)), ArithmeticException.class));
    }
}
