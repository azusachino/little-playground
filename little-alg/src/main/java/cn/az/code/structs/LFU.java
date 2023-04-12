package cn.az.code.structs;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFU {
    public Map<Integer, Integer> keyToVal;
    public Map<Integer, Integer> keyToFreq;
    public Map<Integer, LinkedHashSet<Integer>> freqToKey;

    public int minFreq;

    public LFU() {
        this.keyToVal = new HashMap<>();
        this.keyToFreq = new HashMap<>();
        this.freqToKey = new HashMap<>();
        this.minFreq = 0;
    }

    public void put(int key, int val) {
        if (this.keyToVal.containsKey(key)) {
            this.keyToFreq.compute(key,(k,v) -> v+1);
        }

    }

    public Integer get(int key) {

        return null;
    }

}
