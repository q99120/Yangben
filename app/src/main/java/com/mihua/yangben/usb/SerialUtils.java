package com.mihua.yangben.usb;

import android.util.Log;

import com.google.gson.Gson;
import com.mihua.yangben.bean.SerialMessage;
import com.mihua.yangben.network.AppConfig;
import com.mihua.yangben.utils.TransformUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android_serialport_api.SerialPort;

import static com.mihua.yangben.usb.util.HexUtils.hexToByte;

/**
 * 串口工具类
 */

public class SerialUtils {
    //声明串口对象
    SerialPort mSerialPort;
    private OutputStream outputStream;
    private InputStream inputStream;
    public static boolean serialPortStatus = false; //是否打开串口标志
    String TAG = "SerialUtils";

    //将构造函数私有化
    private SerialUtils() {
    }

    public static SerialUtils getInstance() {
        return SerialLoader.INSTANCE;
    }

    //静态内部类
    public static class SerialLoader {
        private static final SerialUtils INSTANCE = new SerialUtils();
    }

    public void OpenSerial() {
        try {
            //创建串口3对象
            mSerialPort = new SerialPort(new File("/dev/ttyS3"), AppConfig.BAUDRATE, 0);
            serialPortStatus = true;
            Log.e(TAG, "OpenSerial: " + "打开串口成功");
            //获取输出流3、向外设输出数据
            if (outputStream == null) {
                outputStream = mSerialPort.getOutputStream();
            }
            //串口对象获取输入流3、接收到的数据
            if (inputStream == null) {
                inputStream = mSerialPort.getInputStream();
            }
            //开启接收线程
            new ReadThread().start(); // 启动读线程2
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析数据
     */
    public void ParseData(int sample_flag, int data_size, String datas) {
        if (sample_flag == 1) {
            Gson gson = new Gson();


//                Gson gson = new Gson();
//                Log.e(TAG, "ParseData11: "+fuNanJsonBean.toString() );
//            String resultss = "ff6b1a"+jsonTohex(datas,0)+jsonTohex(datas,1)
//                    +jsonTohex(datas,2)
//                    +jsonTohex(datas,3)+jsonTohex(datas,4)+jsonTohex(datas,5)
//                    +jsonTohex(datas,6)+jsonTohex(datas,7)+jsonTohex(datas,8)+jsonTohex(datas,9)
//                    +"00" +jsonTohex(datas,10)+jsonTohex(datas,7)+jsonTohex(datas,11)+"00"+jsonTohex(datas,12)
//                    +"0000"+jsonTohex(datas,13)+jsonTohex(datas,14)+"00"+jsonTohex(datas,15)+jsonTohex(datas,16)
//                    +"00"+jsonTohex(datas,17)+jsonTohex(datas,18)+jsonTohex(datas,19);
//            Log.e(TAG, "resultss: "+jsonTohex(datas,0) );

//                result[0] = (byte) 0xff;
//                result[1] = 0x6b;
//                result[2] = 0x1a;
////                Log.e(TAG, "ParseData: "+jsonTohex(datas) );
//                result[3] = jsonTobyteToff(datas, 0);
//                result[4] = jsonTobyteToff(datas, 1);
//                result[5] = jsonTobyteToff(datas, 2);
//                result[6] = jsonTobyteToff(datas, 3);
//                result[7] = jsonTobyteToff(datas, 4);
//                result[8] = jsonTobyteToff(datas, 5);
//                result[9] = jsonTobyteToff(datas, 6);
//                result[10] = jsonTobyteToff(datas, 7);
//                result[11] = jsonTobyteToff(datas, 8);
//                result[12] = jsonTobyteToff(datas, 9);
//                result[13] = 0;
//                result[14] = jsonTobyteToff(datas, 10);
//                result[15] = jsonTobyteToff(datas, 11);
//                result[16] = 0;
//                result[17] = jsonTobyteToff(datas, 12);
//                result[18] = 0;
//                result[19] = 0;
//                result[20] = jsonTobyteToff(datas, 13);
//                result[21] = jsonTobyteToff(datas, 14);
//                result[22] = 0;
//                result[23] = jsonTobyteToff(datas, 15);
//                result[24] = jsonTobyteToff(datas, 16);
//                result[25] = 0;
//                result[26] = jsonTobyteToff(datas, 17);
//                result[27] = jsonTobyteToff(datas, 24);
//                result[28] = jsonTobyteToff(datas, 25);
//                StringBuilder stringBuilder = new StringBuilder();
//                for (int i = 3; i < 29; i++) {
//                    Log.e(TAG, "ParseData555: " + jsonTohex(datas, i));
//                    Log.e("stringBuilder11",result[3]+"");
//                    stringBuilder.append(result[i]);
//                }
//                Log.e("stringBuilder222",stringBuilder.toString());
//                String check_str = SimpleUtils.parseHex2Opposite(stringBuilder.toString());
//                result[29] = Byte.parseByte(check_str);
//                WriteByte(result);
        } else {
            try {
                JSONObject jsonObject = new JSONObject(datas);
                byte[] result = new byte[22];
                result[0] = 0x6e;
                result[1] = hexToByte(jsonObject.getString("样本乙腈"));
                result[2] = 0x00;
                result[3] = 0x00;
                result[4] = 0x00;
                result[5] = hexToByte(jsonObject.getString("搅拌转速"));
                result[6] = hexToByte(jsonObject.getString("搅拌时间"));
                result[7] = hexToByte(jsonObject.getString("过柱乙腈"));
                result[8] = hexToByte(jsonObject.getString("活化正压"));
                result[9] = hexToByte(jsonObject.getString("活化时间"));
                result[10] = 0x00;
                result[11] = hexToByte(jsonObject.getString("上样样本液"));
                result[12] = hexToByte(jsonObject.getString("上样正压"));
                result[13] = hexToByte(jsonObject.getString("上样时间"));
                result[14] = hexToByte(jsonObject.getString("上样次数"));
                result[15] = hexToByte(jsonObject.getString("浓缩孵育温度"));
                result[16] = hexToByte(jsonObject.getString("浓缩吹气温度"));
                result[17] = hexToByte(jsonObject.getString("浓缩时间"));
                result[18] = hexToByte(jsonObject.getString("上样样本液吸取值"));
                result[19] = hexToByte(Integer.toHexString(data_size));
                result[20] = 0x0d;
                result[21] = 0x0a;
                WriteByte(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 发送数据的方法
     */
    public void WriteByte(byte[] result) {
        Log.e(TAG, "WriteByte: " + result.length);
//        try {
//            outputStream.write(result);
//            //刷新
//            outputStream.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private String[] split;

    class ReadThread extends Thread {
        //字符数组、接收数据
        byte[] buffer2 = new byte[2048];

        @Override
        public void run() {
            super.run();
            //死循环、保持接收状态
            while (serialPortStatus) {
                try {
//                    Log.e("tagtest", "等待接收串口3数据");
                    if (inputStream == null) {
                        return;
                    }
                    int size2 = inputStream.read(buffer2);
                    if (size2 > 0) {
                        String result2 = new String(buffer2, 0, size2).trim();
                        Log.i("tagtest", "接收到数据3长度：" + size2);
                        Log.i("tagtest", "接收到数据3：" + TransformUtils.bytesToHexStringWithOx(buffer2, size2));

                        //读到的数据建议设立缓存，不能保证一次完整发送一包，有时会2次发一个
                        //stringBuffer2.append(result2);
                        String s = TransformUtils.bytesToHexStringWithOx(buffer2, size2);
                        EventBus.getDefault().post(new SerialMessage(s));
                        split = s.split(" ");
                    } else {
                        Log.i("tagtest", "未读到串口3返回的数据");
                    }
                    Thread.sleep(400);

                } catch (Exception e) {
                    Log.i("tagtest", e.toString());
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 关闭串口
     */
    public void closeSerialPort() {
        try {
            inputStream.close();
            outputStream.close();
            serialPortStatus = false;
            mSerialPort.close();
        } catch (IOException e) {
            Log.e("关闭串口异常", "SerialPort: 关闭串口异常：" + e.toString());
            return;
        }
        Log.e("关闭串口成功", "SerialPort: 关闭串口成功");
    }


}
