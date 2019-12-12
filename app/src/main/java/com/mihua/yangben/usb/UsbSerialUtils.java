package com.mihua.yangben.usb;

import android.content.Context;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;

import com.mihua.yangben.usb.driver.UsbSerialDriver;
import com.mihua.yangben.usb.driver.UsbSerialPort;
import com.mihua.yangben.usb.driver.UsbSerialProber;
import com.mihua.yangben.usb.util.SerialInputOutputManager;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2018/02/07
 *     desc   : usb 用于控制usb端口，测试端口
 * </pre>
 */
public class UsbSerialUtils {


    private volatile static UsbSerialUtils sUsbSerialUtils;
    private SerialInputOutputManager mSerialIoManager;
    private SerialInputOutputManager.Listener mListener;
    private UsbManager mUsbManager;

    private final ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    private UsbSerialPort sPort;
    private final Context mApplicationContext;


    public UsbSerialUtils(Context context) {
        mApplicationContext = context.getApplicationContext();
        mUsbManager = (UsbManager) mApplicationContext.getSystemService(Context.USB_SERVICE);
    }

    /**
     * application中初始化
     *
     * @param context
     */
    public static UsbSerialUtils init(Context context) {
        if (sUsbSerialUtils == null) {
            synchronized (UsbSerialUtils.class) {
                if (sUsbSerialUtils == null) {
                    sUsbSerialUtils = new UsbSerialUtils(context);
                    return sUsbSerialUtils;
                }
            }
        }
        return sUsbSerialUtils;
    }

    /**
     * 获得实例
     *
     * @return
     */
    public static UsbSerialUtils getInstance() {
        if (sUsbSerialUtils != null) {
            return sUsbSerialUtils;
        }
        return null;
    }


    public void setListener(SerialInputOutputManager.Listener listener) {
        if (listener == null) {
            return;
        }
        mListener = listener;
    }

    // 得到 Usb Port
    public UsbSerialPort getUsbSerialPort() {

        try {
            List<UsbSerialDriver> drivers = UsbSerialProber.getDefaultProber().findAllDrivers(mUsbManager);
            if (drivers != null) {
                if (!drivers.isEmpty()) {
                    UsbSerialDriver usbSerialDriver = drivers.get(0);

                    if (usbSerialDriver != null) {
                        List<UsbSerialPort> list = usbSerialDriver.getPorts();
                        if (list != null) {
                            if (!list.isEmpty()) {
                                sPort = list.get(0);
                                return sPort;
                            }
                        }
                    }
                } else {
                    Logger.d("drivers is empty");
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ----------------------------------------------------------------
    // usb 串口相关

    /**
     * @param baudRate
     * @param usbSerialPort
     * @return 读写线程提交成功
     */
    public boolean setUsbPortParamsAndStart(int baudRate, UsbSerialPort usbSerialPort) {

        UsbDeviceConnection connection = null;
        try {
            connection = mUsbManager.openDevice(usbSerialPort.getDriver().getDevice());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (connection == null) {
            Logger.d("Opening device failed");
            return false;
        }

        try {
            usbSerialPort.open(connection);
            usbSerialPort.setParameters(baudRate, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);

            Logger.d("CD  - Carrier Detect", usbSerialPort.getCD()
                    + "\r\n CTS - Clear To Send", usbSerialPort.getCTS()
                    + "\r\nDSR - Data Set Ready", usbSerialPort.getDSR()
                    + "\nDTR - Data Terminal Ready", usbSerialPort.getDTR()
                    + "\nDSR - Data Set Ready", usbSerialPort.getDSR()
                    + "\nRI  - Ring Indicator", usbSerialPort.getRI()
                    + "\nRTS - Request To Send", usbSerialPort.getRTS());

        } catch (IOException e) {
            Logger.d("Error setting up device: " + e.getMessage(), e);
            Logger.d("Error opening device: " + e.getMessage());
            try {
                usbSerialPort.close();
            } catch (IOException e2) {
                // Ignore.
            }
            usbSerialPort = null;
            return false;
        }
        Logger.d("Serial device: " + usbSerialPort.getClass().getSimpleName());
        onDeviceStateChange();
        return true;
    }

    private void stopIoManager() {
        if (mSerialIoManager != null) {
            Logger.d("Stopping io manager ..");
            mSerialIoManager.stop();
            mSerialIoManager = null;
        }
    }

    private void startIoManager() {
        if (sPort != null && mListener != null) {
            Logger.d("Starting io manager ..");
            mSerialIoManager = new SerialInputOutputManager(sPort, mListener);
            mExecutor.submit(mSerialIoManager);
        }
    }

    public void onDeviceStateChange() {
        stopIoManager();
        startIoManager();
    }

    public void onPause() {
        stopIoManager();
        if (sPort != null) {
            try {
                sPort.close();
            } catch (IOException e) {
                // Ignore.
            }
            sPort = null;
        }
    }

    public void sendMessage(byte[] data) {
        if (mSerialIoManager != null) {
            mSerialIoManager.writeAsync(data);
        }
    }

}
