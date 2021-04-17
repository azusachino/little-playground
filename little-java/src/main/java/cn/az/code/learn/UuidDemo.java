package cn.az.code.learn;

import org.apache.commons.codec.binary.Base64;

import java.util.UUID;

/**
 * @author Liz
 */
public class UuidDemo {

    public static void main(String[] args) {
        UUID uuid = UUID.fromString("afac7d90-d1e1-468d-9835-aa5ccde086a8");
        System.out.println("uuid = " + uuid);
        String cU = compressUuid(uuid);
        System.out.println("compressedUUID = " + cU);
        System.out.println("uncompressedUUID = " + uncompress(cU));
    }

    private static String compressUuid(UUID uuid) {
        byte[] byUuid = new byte[16];
        long least = uuid.getLeastSignificantBits();
        long most = uuid.getMostSignificantBits();
        long2bytes(most, byUuid, 0);
        long2bytes(least, byUuid, 8);
        // LE5ZZzh7SWyzkc0bx-q3xQ (no '==')
        return Base64.encodeBase64String(byUuid);
    }

    private static void long2bytes(long value, byte[] bytes, int offset) {
        for (int i = 7; i > -1; i--) {
            bytes[offset++] = (byte) ((value >> 8 * i) & 0xFF);
        }
    }

    public static String uncompress(String compressedUuid) {
        if (compressedUuid.length() != 22) {
            throw new IllegalArgumentException("Invalid uuid!");
        }
        byte[] byUuid = Base64.decodeBase64(compressedUuid);
        long most = bytes2long(byUuid, 0);
        long least = bytes2long(byUuid, 8);
        UUID uuid = new UUID(most, least);
        return uuid.toString();
    }

    private static long bytes2long(byte[] bytes, int offset) {
        long value = 0;
        for (int i = 7; i > -1; i--) {
            value |= (((long) bytes[offset++]) & 0xFF) << 8 * i;
        }
        return value;
    }
}
