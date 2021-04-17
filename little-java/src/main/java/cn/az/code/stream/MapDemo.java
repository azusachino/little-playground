package cn.az.code.stream;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Map;

/**
 * @author Liz
 */
public class MapDemo {

    public static void main(String[] args) {
        Map<String, String> sqlMap = MapUtil.newHashMap();
        sqlMap.put("9991", "wa");
        sqlMap.put("9992", "wa");
        Map<String, String> fileNameMap = MapUtil.newHashMap();
        fileNameMap.put("999", "www");
        fileNameMap.put("888", "eee");

        sqlMap.keySet().forEach(k -> fileNameMap.keySet().removeIf(k1 -> !k1.equals(StrUtil.subPre(k, k.length() - 1))));

        System.out.println(fileNameMap);
    }
}
