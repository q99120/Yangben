package com.mihua.yangben.usb;

import android.content.Context;

import com.orhanobut.logger.Logger;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2018/02/07
 *     desc   :
 * </pre>
 */
public class UsbDataUtils {


    /**
     * 控制格式：0x6B+ Data[10]
     *
     * @param b  一个操作对应数据的字节
     * @param index 操作的事件
     * <p>
     * 统一格式 0x00：关 0x01 开 0xFF忽略默认字符
     * <p>
     * Data[0]: 开关   关: 0x00，开: 0x01
     * Data[1]: 风速   关: 0x00， 一档:  0x01， 二挡:  0x02，三挡: 0x03
     * Data[2]: 负离子 关: 0x00，开: 0x01
     * Data[3]: 睡眠   关: 0x00，开: 0x01
     * Data[4]: 加湿   关: 0x00，开: 0x01，
     * Data[5]: 自动   关: 0x00，开: 0x01
     * Data[6]: 杀菌   关: 0x00，开: 0x01
     * Data[7]: 定时   关: 0x00，开:0x06 ---1H: 0x01，2H:  0x02，4H:  0x03，8H: 0x04，12H:  0x05(红外遥控器使用)
     * Data[8]: 童锁   关: 0x00，开: 0x01(红外遥控器使用)
     * Data[9]: 自动加湿   关: 0x00，开: 0x01
     */

    //  标识符
    public static final byte FLAG = 0X6B;

    //  默认字符
    public static final byte DEFAULT_BYTE = (byte) 0XFF;

    //  开使用的字节
    public static final byte POWER_ON = 0X01;

    //  关使用的字节
    public static final byte POWER_OFF = 0X00;

    //


    // 通过蓝牙发送相关的数据
    private static void sendMessage(byte[] data) {

        // 发送数据
        UsbSerialUtils instance = UsbSerialUtils.getInstance();
        if (instance != null) {
            instance.sendMessage(data);
        }

    }


    // 打印相关的数据
    public static StringBuffer printData(byte[] data) {
        StringBuffer sb = new StringBuffer();
        for (byte b : data) {
            int v = b & 0xFF;
            sb.append(Integer.toHexString(v))
                    .append(" ");
        }
        return sb;
    }


    /**
     * 控制格式：0x6B+ Data[10]
     *
     * @param b     一个操作对应数据的字节
     * @param index 操作的事件
     *              <p>
     *              统一格式 0x00：关 0x01 开 0xFF忽略默认字符
     *              <p>
     *              Data[0]: 开关   关: 0x00，开: 0x01
     *              Data[1]: 风速   关: 0x00， 一档:  0x01， 二挡:  0x02，三挡: 0x03
     *              Data[2]: 负离子 关: 0x00，开: 0x01
     *              Data[3]: 睡眠   关: 0x00，开: 0x01
     *              Data[4]: 加湿   关: 0x00，开: 0x01，
     *              Data[5]: 自动   关: 0x00，开: 0x01
     *              Data[6]: 杀菌   关: 0x00，开: 0x01
     *              Data[7]: 定时   关: 0x00，开:0x06 ---1H: 0x01，2H:  0x02，4H:  0x03，8H: 0x04，12H:  0x05(红外遥控器使用)
     *              Data[8]: 童锁   关: 0x00，开: 0x01(红外遥控器使用)
     *              Data[9]: 自动加湿   关: 0x00，开: 0x01
     * @param index index 范围为 0————9 ，标志位加上
     * @return 数据 ： 需要发给硬件的字节数组
     */


    private static byte[] createData(byte b, int index) {
        if (index < 0 && index > 9) {
            throw new IndexOutOfBoundsException("index 超过范围");
        }
        byte[] data = new byte[11];
        for (int i = 0; i < data.length; i++) {
            if (i == 0) {
                data[i] = FLAG;
            } else if (i == index + 1) {
                data[i] = b;
            } else {
                data[i] = DEFAULT_BYTE;
            }
        }

        return data;
    }


    private static byte[] createTwoByteData(byte b1, int index1, byte b2, int index2) {
        if (index1 < 0 && index1 > 9) {
            throw new IndexOutOfBoundsException("index1 超过范围");
        }
        if (index2 < 0 && index2 > 9) {
            throw new IndexOutOfBoundsException("index2 超过范围");

        }
        byte[] data = new byte[11];
        for (int i = 0; i < data.length; i++) {
            if (i == 0) {
                data[i] = FLAG;
            } else if (i == index1 + 1) {
                data[i] = b1;
            } else if (i == index2 + 1) {
                data[i] = b2;
            } else {
                data[i] = DEFAULT_BYTE;
            }
        }

        return data;
    }


    /**
     * 总开关的状态
     *
     * @param status TRUE 开  默认是风速2档，负离子开，自动加湿
     */
    public static void sendDataForSwitch(boolean status) {
        byte[] data = null;
        if (status) {
            data = new byte[]{0x6B, (byte) 0x01, (byte) 0x02, (byte) 0x01,
                    (byte) 0xFF, (byte) 0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x01};
        } else {
            data = new byte[]{0x6B, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};

        }
        Logger.d("总开关状态" + printData(data));

        // 发送开关数据
        sendMessage(data);

    }


    /**
     * 发送电风扇的数据设置风扇的等级
     *
     * @param level
     */
    public static void sendDataFanChange(int level) {
        byte b = POWER_OFF;
        switch (level) {
            case 0:
                b = POWER_OFF;
                break;
            case 1:
                b = 0x01;
                break;
            case 2:
                b = 0x02;
                break;
            case 3:
                b = 0x03;
                break;
        }
        byte[] data = createTwoByteData(b, 1, POWER_OFF, 5);
        Logger.d("发送的调整风速level" + printData(data));

        if (data != null) {
            // 发送数据
            sendMessage(data);
        }

    }


    /**
     * 负氧离子  Data[2]
     *
     * @param status 开关
     */
    public static void sendDataForFuLiZi(boolean status) {

        byte b = POWER_OFF;
        if (status) {
            b = POWER_ON;
        } else {
            b = POWER_OFF;
        }
        byte[] data = createData(b, 2);
        Logger.d("发送的负氧离子机开关状态" + printData(data));
        if (data != null) {
            // 发送开关数据
            sendMessage(data);
        }

    }

    /**
     * 睡眠   Data[3]
     *
     * @param status 开关
     */
    public static void sendDataForSleep(boolean status) {

        byte b = POWER_OFF;
        if (status) {
            b = POWER_ON;
        } else {
            b = POWER_OFF;
        }
        byte[] data = createData(b, 3);
        Logger.d("发送睡眠开关状态" + printData(data));
        if (data != null) {
            // 发送开关数据
            sendMessage(data);
        }
    }

    /**
     * 加湿   Data[4]
     *
     * @param status
     */
    public static void sendDataForHumidity(boolean status) {

        byte b = POWER_OFF;
        byte[] data;
        if (status) {
            b = POWER_ON;
            data = new byte[]{0x6B, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
                    (byte) 0xFF, (byte) 0x01, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x00};
        } else {
            b = POWER_OFF;
            data = createData(b, 4);
        }

        Logger.d("发送加湿开关状态" + printData(data));
        if (data != null) {
            // 发送开关数据
            sendMessage(data);
        }
    }

    /**
     * 自动   Data[5]
     *
     * @param status
     */
    public static void sendDataForAuto(boolean status) {

        byte b = POWER_OFF;
        if (status) {
            b = POWER_ON;
        } else {
            b = POWER_OFF;
        }
        byte[] data = createData(b, 5);
        Logger.d("发送自动开关状态" + printData(data));
        if (data != null) {
            // 发送开关数据
            sendMessage(data);
        }

    }

    /**
     * 杀菌  Data[6]
     *
     * @param status
     */
    public static void sendDataForDisinfect(boolean status, Context context) {

        byte b = POWER_OFF;
        if (status) {
            b = POWER_ON;
        } else {
            b = POWER_OFF;
        }
        byte[] data = createData(b, 6);
        Logger.e("发送杀菌开关状态" + printData(data));
        //Log.e("发送杀菌开关状态",printData(data).toString());
        //   Logger.d("发送杀菌开关状态---->"+printData(data));
        StringBuffer stringBuffer = printData(data);
        byte[] bytes = stringToByte("6bffffffffffff01ffffff");//6b ff ff ff ff ff ff 1 ff ff ff

        //  Logger.e("新的发送杀菌开关状态---->"+Arrays.toString(bytes));
        //  Toast.makeText(context, "新的发送杀菌开关状态---->"+Arrays.toString(bytes), Toast.LENGTH_SHORT).show();
        //    Logger.e("旧的发送杀菌开关状态"+Arrays.toString(data));
        if (data != null) {
            // 发送开关数据
            sendMessage(data);
        }
    }


    /**
     * 童锁
     *
     * @param status
     */
    public static void sendDataForChildLock(boolean status) {

        byte b = POWER_OFF;
        if (status) {
            b = POWER_ON;
        } else {
            b = POWER_OFF;
        }
        byte[] data = createData(b, 8);
        Logger.d("发送童锁命令" + printData(data));
        if (data != null) {
            // 发送开关数据
            sendMessage(data);
        }

    }

    /**
     * 自动加湿   自动加湿开则手动加湿关，手动加湿开则自动加湿关；
     *
     * @param status
     */
    public static void sendDataForAutoHumidity(boolean status) {

        byte b = POWER_OFF;
        byte[] data;
        if (status) {
            b = POWER_ON;
            data = new byte[]{0x6B, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
                    (byte) 0xFF, (byte) 0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x01};
        } else {
            b = POWER_OFF;
            data = createData(b, 9);
        }

        Logger.d("发送自动加湿开关状态" + printData(data));
        if (data != null) {
            // 发送开关数据
            sendMessage(data);
        }

    }

    /**
     * 16进制字符串转byte[]
     * @param
     * @return byte[]
     */
    /**
     * 十六进制字符串转化为数组
     */
    public static byte[] stringToByte(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length() / 2;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) ((Character.digit(str.charAt(i * 2), 16) << 4) |
                    Character.digit(str.charAt((i * 2) + 1), 16));
        }
        return bytes;
    }

}
