package com.mihua.yangben.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleUtils {

    //获取当前系统时间并转化数据格式
    public static String getCurretTimes() {
        Date dt = new Date();
        SimpleDateFormat sim_day = new SimpleDateFormat("yyyy-MM-dd");
        String str_day = sim_day.format(dt);
        return str_day;
    }

    /**
     * 获取源数据和验证码的组合byte数组
     *
     * @param aa 字节数组
     * @return
     */
    public static byte[] appendCrc16(byte[] aa) {
        byte[] bb = getCrc16(aa);
        byte[] cc = new byte[aa.length + bb.length];
        System.arraycopy(aa, 0, cc, 0, aa.length);
        System.arraycopy(bb, 0, cc, aa.length, bb.length);
        return cc;
    }

    /**
     * 获取验证码byte数组，基于Modbus CRC16的校验算法
     */
    public static byte[] getCrc16(byte[] arr_buff) {
        int len = arr_buff.length;

        // 预置 1 个 16 位的寄存器为十六进制FFFF, 称此寄存器为 CRC寄存器。
        int crc = 0xA1EC;
        int i, j;
        for (i = 0; i < len; i++) {
            // 把第一个 8 位二进制数据 与 16 位的 CRC寄存器的低 8 位相异或, 把结果放于 CRC寄存器
            crc = ((crc & 0xFF00) | (crc & 0x00FF) ^ (arr_buff[i] & 0xFF));
            for (j = 0; j < 8; j++) {
                // 把 CRC 寄存器的内容右移一位( 朝低位)用 0 填补最高位, 并检查右移后的移出位
                if ((crc & 0x0001) > 0) {
                    // 如果移出位为 1, CRC寄存器与多项式A001进行异或
                    crc = crc >> 1;
                    crc = crc ^ 0xA001;
                } else
                    // 如果移出位为 0,再次右移一位
                    crc = crc >> 1;
            }
        }
        return intToBytes(crc);
    }

    /**
     * 将int转换成byte数组，低位在前，高位在后
     * 改变高低位顺序只需调换数组序号
     */
    private static byte[] intToBytes(int value) {
        byte[] src = new byte[2];
        src[1] = (byte) ((value >> 8) & 0xFF);
        src[0] = (byte) (value & 0xFF);
        return src;
    }


    /**
     * 取反
     */
    public static String parseHex2Opposite(String str) {
        String hex;
        //十六进制转成二进制
        byte[] er = parseHexStr2Byte(str);

        //取反
        byte[] erBefore = new byte[er.length];
        for (int i = 0; i < er.length; i++) {
            erBefore[i] = (byte) ~er[i];
        }

        //二进制转成十六进制
        hex = parseByte2HexStr(erBefore);

        // 如果不够校验位的长度，补0,这里用的是两位校验
        hex = (hex.length() < 2 ? "0" + hex : hex);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(hex);
        Log.e("parseHex2Opposite", "parseHex2Opposite: " + stringBuilder);

        return stringBuilder.toString();
    }

    /**
     * 将二进制转换成十六进制
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将十六进制转换为二进制
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


    //根据Json数据转换
    public static byte jsonTobyteToff(String datas, int jsonflag) {
        String hexjson = null;
        byte result_byte = 0;
        int bbb = 0;
        JSONArray jsonarray = null;
        try {
            jsonarray = new JSONArray(datas);
            JSONObject info = jsonarray.getJSONObject(jsonflag);
//            Log.e("json", "json: "+info.toString() );
            int result = info.getInt("solven_num");
            hexjson = Integer.toHexString(result);
            result_byte = (byte) Integer.parseInt(hexjson, 16);
//            Log.e("jsonTobyteToff", "jsonTobyteToff: "+result_byte );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result_byte;
    }


    //根据Json数据转换为16进制
    public static String jsonTohex(String datas, int flag) {
        StringBuilder stringBuilder = new StringBuilder();
        String hexjson = null;
        JSONArray jsonarray = null;
        try {
            jsonarray = new JSONArray(datas);
            JSONObject info = jsonarray.getJSONObject(flag);
            int result = info.getInt("solven_num");
            hexjson = Integer.toHexString(result);
//            String hh = "0x";
//            if (hexjson.length() < 2) {
//                stringBuilder.append(hh).append(0).append(hexjson);
//            }else {
//                stringBuilder.append(hh).append(hexjson);
//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hexjson;
    }
}
