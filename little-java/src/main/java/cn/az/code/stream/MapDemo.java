package cn.az.code.stream;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Liz
 */
public class MapDemo {

    public static void main(String[] args) {
        Map<String, String> sqlMap = new HashMap<>();
        sqlMap.put("9991", "wa");
        sqlMap.put("9992", "wa");
        Map<String, String> fileNameMap = new HashMap<>();
        fileNameMap.put("999", "www");
        fileNameMap.put("888", "eee");

        sqlMap.keySet().forEach(
                k -> fileNameMap.keySet().removeIf(k1 -> !k1.equals(StringUtils.substring(k, k.length() - 1))));

        System.out.println(fileNameMap);
    }
}
