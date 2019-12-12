package com.mihua.yangben.usb;

import android.util.Log;

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

import static com.mihua.yangben.usb.util.HexUtils.hexToByteArray;
import static com.mihua.yangben.utils.SimpleUtils.json0;
import static com.mihua.yangben.utils.SimpleUtils.makeCheck16;
import static com.mihua.yangben.utils.SimpleUtils.makeCheckSum;

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
            try {
                JSONObject jt = new JSONObject(datas);
                String head = "ff6b1a";
                String _hcl = json0(jt.getString("盐酸用量(ml)"));
                String _fywd = json0(jt.getString("孵育温度(摄氏度)"));
                String _jbzs = json0(jt.getString("搅拌转速(RPM)"));
                String _jbsj = json0(jt.getString("搅拌时间(分钟)"));
                String _qyhn = json0(jt.getString("氢氧化钠用量(ml)"));
                String _pbs = json0(jt.getString("PBS缓冲液用量(ml)"));
                String _zcjbzs = json0(jt.getString("再次搅拌转速(RPM)"));
                String _zcjbsj = json0(jt.getString("再次搅拌时间(分钟)"));
                String _jc = json0(jt.getString("甲醇用量(ml)"));
                String _pyl1 = json0(jt.getString("排液量1(ml)"));
                String _oo1 = "00";
                String _syl1 = json0(jt.getString("水用量1(ml)"));
                String _pyl2 = json0(jt.getString("排液量2(ml)"));
                String _oo2 = "00";
                String _syyl = json0(jt.getString("上样用量(ml)"));
                String _oo3 = "0000";
                String _syl2 = json0(jt.getString("水用量2(ml)"));
                String _pyl4 = json0(jt.getString("排液量4(ml)"));
                String _oo4 = "00";
                String _ysyz = json0(jt.getString("乙酸乙酯用量(ml)"));
                String _pyl5 = json0(jt.getString("排液量5(ml)"));
                String _oo5 = "00";
                String _nswd = json0(jt.getString("浓缩温度(摄氏度)"));
                String _cqwd = json0(jt.getString("吹气温度(摄氏度)"));
                String _nssj = json0(jt.getString("浓缩时间(分钟)"));
                String check_sum = _hcl + _fywd + _jbzs + _jbsj + _qyhn + _pbs + _zcjbzs + _zcjbsj + _jc + _pyl1 + _oo1 + _syl1 + _pyl2 + _oo2 +
                        _syyl + _oo3 + _syl2 + _pyl4 + _oo4 + _ysyz + _pyl5 + _oo5 + _nswd + _cqwd + _nssj;
                String content_16 = makeCheck16(check_sum);
                String _check_sum = makeCheckSum(check_sum);
                String total = head + content_16 + _check_sum;
                WriteByte(hexToByteArray(total));
                Log.e(TAG, "获取总数据: " + total);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (sample_flag == 2) {
            try {
                JSONObject jt = new JSONObject(datas);
                String head = "ff6c1a";
                String yjyl = json0(jt.getString("乙腈用量(ml)"));
                String jbzs = json0(jt.getString("搅拌转速(RPM)"));
                String jbsj = json0(jt.getString("搅拌时间(分钟)"));
                String qyhn = json0(jt.getString("氢氧化钠用量(ml)"));
                String pyl = json0(jt.getString("排液量1(ml)"));
                String o1 = "00";
                String syyl = json0(jt.getString("上样用量(ml)"));
                String o2 = "0000";
                String nswd = json0(jt.getString("浓缩温度(摄氏度)"));
                String cqwd = json0(jt.getString("吹气温度(摄氏度)"));
                String nssj = json0(jt.getString("浓缩时间(分钟)"));
                String sum = yjyl + jbzs + jbsj + qyhn + pyl + o1 + syyl + o2 + nswd + cqwd + nssj;
                String _checkSum = makeCheckSum(sum);
                String content_16 = makeCheck16(sum);
                String total = head + content_16 + _checkSum;
                WriteByte(hexToByteArray(total));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            try {
                JSONObject jt = new JSONObject(datas);
                String head = "ff6d1a";
                String _hcl = json0(jt.getString("乙酸盐缓冲液用量(ml)"));
                String _fywd = json0(jt.getString("孵育温度(摄氏度)"));
                String _jbzs = json0(jt.getString("搅拌转速(RPM)"));
                String _jbsj = json0(jt.getString("搅拌时间(分钟)"));
                String _jc1 = json0(jt.getString("甲醇用量(ml)"));
                String _pyl1 = json0(jt.getString("排液量1(ml)"));
                String oo1 = "00";
                String _syl1 = json0(jt.getString("水用量1(ml)"));
                String _pyl2 = json0(jt.getString("排液量2(ml)"));
                String oo2 = "00";
                String _gls = json0(jt.getString("高氯酸用量(ml)"));
                String _pyl3 = json0(jt.getString("排液量3(ml)"));
                String oo3 = "00";
                String _syyl = json0(jt.getString("上样用量(ml)"));
                String oo4 = "0000";
                String _jc2 = json0(jt.getString("甲醇用量2(ml)"));
                String _pyl4 = json0(jt.getString("排液量4(ml)"));
                String oo5 = "00";
                String _jcsry = json0(jt.getString("甲醇水溶液用量(ml)"));
                String _pyl5 = json0(jt.getString("排液量5(ml)"));
                String oo6 = "00";
                String _ahjc = json0(jt.getString("氨化甲醇用量(ml)"));
                String _pyl6 = json0(jt.getString("排液量6(ml)"));
                String oo7 = "00";
                String check_sum = _hcl + _fywd + _jbzs + _jbsj + _jc1 + _pyl1 + oo1 + _syl1 + _pyl2 + oo2 + _gls + _pyl3 + oo3 + _syyl +
                        oo4 + _jc2 + _pyl4 + oo5 + _jcsry + _pyl5 + oo6 + _ahjc + _pyl6 + oo7;
                String content_16 = makeCheck16(check_sum);
                String _check_sum = makeCheckSum(check_sum);
                String total = head + content_16 + _check_sum;
                WriteByte(hexToByteArray(total));
                Log.e(TAG, "获取总数据: " + total);
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
