package cn.az.code.structs;

import java.util.LinkedHashMap;

public class LRUCache {

    int cap;
    LinkedHashMap<Integer, Integer> data = new LinkedHashMap<>();

    public Integer get(int key) {
        Integer r = this.data.get(key);
        if (r != null) {
            this.makeRecent(key);
        }
        return r;
    }

    public void put(int key, int val) {
        if (this.data.containsKey(key)) {
            this.data.put(key, val);
            this.makeRecent(key);
            return;
        }

        if (this.data.size() >= this.cap) {
            Integer oldKey = this.data.keySet().iterator().next();
            this.data.remove(oldKey);
        }

        this.data.put(key, val);
    }

    public void makeRecent(int key) {
        Integer v = this.data.get(key);
        this.data.remove(key);
        this.data.put(key, v);
    }

}
