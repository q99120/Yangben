package com.mihua.yangben.usb.util;

public class HexUtils {

    /**
     * Hex字符串转byte
     *
     * @param inHex 待转换的Hex字符串
     * @return 转换后的byte
     */
    public static byte hexToByte(String inHex) {
        int hex_int = Integer.parseInt(inHex);
        String hex_str = Integer.toHexString(hex_int);
        return (byte) Integer.parseInt(hex_str, 16);
    }


    /**
     * Hex字符串转byte
     *
     * @param 待转换的int整形
     * @return 转换后的byte
     */
    public static byte hexToByte(int int10) {
        String hex_str = Integer.toHexString(int10);
        return (byte) Integer.parseInt(hex_str, 16);
    }


    /**
     * hex字符串转byte数组
     *
     * @param inHex 待转换的Hex字符串
     * @return 转换后的byte数组结果
     */
    public static byte[] hexToByteArray(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            //奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {
            //偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }

}
