package cn.az.code.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.CharacterIterator;
import java.text.DecimalFormat;
import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author az
 * @since 2021-06-17
 */
public class CommonUtil {

    public static String getPrintSize(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        double value = (double) size;
        if (value < 1024) {
            return String.valueOf(value) + "B";
        } else {
            value = new BigDecimal(value / 1024).setScale(2, RoundingMode.DOWN).doubleValue();
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (value < 1024) {
            return String.valueOf(value) + "KB";
        } else {
            value = new BigDecimal(value / 1024).setScale(2, RoundingMode.DOWN).doubleValue();
        }
        if (value < 1024) {
            return String.valueOf(value) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            value = new BigDecimal(value / 1024).setScale(2, RoundingMode.DOWN).doubleValue();
            return String.valueOf(value) + "GB";
        }
    }

    public static String humanReadableByteCountSI(long bytes) {
        if (-1000 < bytes && bytes < 1000) {
            return bytes + " B";
        }
        CharacterIterator ci = new StringCharacterIterator("kMGTPE");
        while (bytes <= -999_950 || bytes >= 999_950) {
            bytes /= 1000;
            ci.next();
        }
        return String.format("%.1f %cB", bytes / 1000.0, ci.current());
    }

    public static String humanReadableByteCountBin(long bytes) {
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if (absB < 1024) {
            return bytes + " B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator("KMGTPE");
        // eg 2^50
        for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }
        value *= Long.signum(bytes);
        return String.format("%.2f %cB", value / 1024.0, ci.current());
    }

    /**
     * 当对一个类或接口使用 strictfp 关键字时，该类中的所有代码，包括嵌套类型中的初始设定值和代码，都将严格地进行计算。
     * 
     * @param bytes 字节大小
     * @param si    是否MBi
     * @return 可读的文件大小
     */
    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        long absBytes = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);

        if (absBytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(absBytes) / Math.log(unit));
        long th = (long) Math.ceil(Math.pow(unit, exp) * (unit - 0.05));
        if (exp < 6 && absBytes >= th - ((th & 0xFFF) == 0xD00 ? 51 : 0)) {
            exp++;
        }
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        if (exp > 4) {
            bytes /= unit;
            exp -= 1;
        }
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    /**
     * 通过反射转换成map
     *
     * @param obj 对象
     * @return map
     */
    public static Map<String, Object> convertToMap(Object obj) {
        Map<String, Object> map = new HashMap<>(8);
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                String varName = field.getName();
                boolean accessFlag = field.canAccess(obj);
                field.setAccessible(true);
                Object o = field.get(obj);
                if (o != null) {
                    map.put(varName, o);
                }
                field.setAccessible(accessFlag);
            }
        } catch (Exception e) {
            return map;
        }
        return map;
    }

    /**
     * 格式化时间区间
     *
     * @param milliSeconds 毫秒
     * @return readable时间单位
     */
    public static String formatDuration(long milliSeconds) {

        DecimalFormat df = new DecimalFormat("#.00");
        StringBuilder sb = new StringBuilder();
        int thousand = 1000;

        if (milliSeconds < thousand) {
            sb.append(milliSeconds).append("毫秒");
        } else if (milliSeconds < (60 * thousand)) {
            sb.append(df.format((double) milliSeconds / thousand)).append("秒");
        } else if (milliSeconds < (60 * 60 * thousand)) {
            sb.append(df.format((double) milliSeconds / (60 * thousand))).append("分钟");
        } else {
            sb.append(df.format((double) milliSeconds / (60 * 60 * thousand))).append("小时");
        }
        return sb.toString();
    }

}
