package cn.az.code.math;

import java.util.Arrays;

public class Prime {

    // n > 2
    public int primeCnt(int n) {
        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);

        // sqrt(n)
        for (int i = 2; i * i < n; i++) {
            if (primes[i]) {
                for (int j = i * i; j < n; j += i) {
                    primes[j] = false;
                }
            }
        }

        int v = 0;
        for (int i = 2; i < n; i++) {
            if (primes[i]) {
                v++;
            }
        }
        return v;

    }

}
