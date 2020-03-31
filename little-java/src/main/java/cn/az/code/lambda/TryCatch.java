package cn.az.code.lambda;

import cn.hutool.log.Log;

import java.util.Arrays;
import java.util.List;

/**
 * @author Liz
 * @date 1/7/2020
 */
public class TryCatch {

    private static Log log = Log.get();

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(3,9,4,2,0,41);
        // 1. try...catch
        integerList.forEach(i ->  {
            try {
                System.out.println(50 / i);
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            }
        });

        //2. lambdaWrapper
        integerList.forEach(LambdaWrapper.lambdaWrapper(i -> System.out.println(50 / i)));

        //3. specific Exception
        integerList.forEach(LambdaWrapper.lambdaWrapper(integer -> log.error(String.valueOf(integer)),ArithmeticException.class));
    }
}
