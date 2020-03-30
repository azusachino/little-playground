package cn.az.code.trick;

/**
 * @author azusachino
 * @version 2019/12/07
 */
public class OtherTrick {

    public static void main(String[] args) {
        int n = 100;
        // Multiply n with 2
        n = n << 1;
        // Divide n by 2
        n = n >> 1;
    }

    public static boolean checkEven(int num) {
        return (num & 1) == 0;
    }
}
