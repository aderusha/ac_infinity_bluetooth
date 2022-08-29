package com.telink.p010lt.ble;

import android.util.SparseArray;
import java.util.HashMap;

/* renamed from: com.telink.lt.ble.BleNamesResolver */
public class BleNamesResolver {
    public static final String DEFAULT_CHARACTERISTIC_NAME = "Unknown Characteristic";
    private static SparseArray<String> mAppearance = new SparseArray<>();
    private static HashMap<String, String> mCharacteristics = new HashMap<>();
    private static SparseArray<String> mHeartRateSensorLocation = new SparseArray<>();
    private static HashMap<String, String> mServices = new HashMap<>();
    private static SparseArray<String> mValueFormats = new SparseArray<>();

    static {
        mServices.put("00001811-0000-1000-8000-00805f9b34fb", "Alert Notification Service");
        mServices.put("0000180f-0000-1000-8000-00805f9b34fb", "Battery Service");
        mServices.put("00001810-0000-1000-8000-00805f9b34fb", "Blood Pressure");
        mServices.put("00001805-0000-1000-8000-00805f9b34fb", "Current Time Service");
        mServices.put("00001818-0000-1000-8000-00805f9b34fb", "Cycling Power");
        mServices.put("00001816-0000-1000-8000-00805f9b34fb", "Cycling Speed and Cadence");
        mServices.put("0000180a-0000-1000-8000-00805f9b34fb", "Device Information");
        mServices.put("00001800-0000-1000-8000-00805f9b34fb", "Generic Access");
        mServices.put("00001801-0000-1000-8000-00805f9b34fb", "Generic Attribute");
        mServices.put("00001808-0000-1000-8000-00805f9b34fb", "Glucose");
        mServices.put("00001809-0000-1000-8000-00805f9b34fb", "Health Thermometer");
        mServices.put("0000180d-0000-1000-8000-00805f9b34fb", "Heart Rate");
        mServices.put("00001812-0000-1000-8000-00805f9b34fb", "Human Interface Device");
        mServices.put("00001802-0000-1000-8000-00805f9b34fb", "Immediate Alert");
        mServices.put("00001803-0000-1000-8000-00805f9b34fb", "Link Loss");
        mServices.put("00001819-0000-1000-8000-00805f9b34fb", "Location and Navigation");
        mServices.put("00001807-0000-1000-8000-00805f9b34fb", "Next DST Change Service");
        mServices.put("0000180e-0000-1000-8000-00805f9b34fb", "Phone Alert Status Service");
        mServices.put("00001806-0000-1000-8000-00805f9b34fb", "Reference Time Update Service");
        mServices.put("00001814-0000-1000-8000-00805f9b34fb", "Running Speed and Cadence");
        mServices.put("00001813-0000-1000-8000-00805f9b34fb", "Scan Parameters");
        mServices.put("00001804-0000-1000-8000-00805f9b34fb", "Tx Power");
        mCharacteristics.put("00002a43-0000-1000-8000-00805f9b34fb", "Alert Category ID");
        mCharacteristics.put("00002a42-0000-1000-8000-00805f9b34fb", "Alert Category ID Bit Mask");
        mCharacteristics.put("00002a06-0000-1000-8000-00805f9b34fb", "Alert Level");
        mCharacteristics.put("00002a44-0000-1000-8000-00805f9b34fb", "Alert Notification Control Point");
        mCharacteristics.put("00002a3f-0000-1000-8000-00805f9b34fb", "Alert Status");
        mCharacteristics.put("00002a01-0000-1000-8000-00805f9b34fb", "Appearance");
        mCharacteristics.put("00002a19-0000-1000-8000-00805f9b34fb", "Battery Level");
        mCharacteristics.put("00002a49-0000-1000-8000-00805f9b34fb", "Blood Pressure Feature");
        mCharacteristics.put("00002a35-0000-1000-8000-00805f9b34fb", "Blood Pressure Measurement");
        mCharacteristics.put("00002a38-0000-1000-8000-00805f9b34fb", "Body Sensor Location");
        mCharacteristics.put("00002a22-0000-1000-8000-00805f9b34fb", "Boot Keyboard Input Report");
        mCharacteristics.put("00002a32-0000-1000-8000-00805f9b34fb", "Boot Keyboard Output Report");
        mCharacteristics.put("00002a33-0000-1000-8000-00805f9b34fb", "Boot Mouse Input Report");
        mCharacteristics.put("00002a5c-0000-1000-8000-00805f9b34fb", "CSC Feature");
        mCharacteristics.put("00002a5b-0000-1000-8000-00805f9b34fb", "CSC Measurement");
        mCharacteristics.put("00002a2b-0000-1000-8000-00805f9b34fb", "Current Time");
        mCharacteristics.put("00002a66-0000-1000-8000-00805f9b34fb", "Cycling Power Control Point");
        mCharacteristics.put("00002a65-0000-1000-8000-00805f9b34fb", "Cycling Power Feature");
        mCharacteristics.put("00002a63-0000-1000-8000-00805f9b34fb", "Cycling Power Measurement");
        mCharacteristics.put("00002a64-0000-1000-8000-00805f9b34fb", "Cycling Power Vector");
        mCharacteristics.put("00002a08-0000-1000-8000-00805f9b34fb", "Date Time");
        mCharacteristics.put("00002a0a-0000-1000-8000-00805f9b34fb", "Day Date Time");
        mCharacteristics.put("00002a09-0000-1000-8000-00805f9b34fb", "Day of Week");
        mCharacteristics.put("00002a00-0000-1000-8000-00805f9b34fb", "Device Name");
        mCharacteristics.put("00002a0d-0000-1000-8000-00805f9b34fb", "DST Offset");
        mCharacteristics.put("00002a0c-0000-1000-8000-00805f9b34fb", "Exact Time 256");
        mCharacteristics.put("00002a26-0000-1000-8000-00805f9b34fb", "Firmware Revision String");
        mCharacteristics.put("00002a51-0000-1000-8000-00805f9b34fb", "Glucose Feature");
        mCharacteristics.put("00002a18-0000-1000-8000-00805f9b34fb", "Glucose Measurement");
        mCharacteristics.put("00002a34-0000-1000-8000-00805f9b34fb", "Glucose Measurement Context");
        mCharacteristics.put("00002a27-0000-1000-8000-00805f9b34fb", "Hardware Revision String");
        mCharacteristics.put("00002a39-0000-1000-8000-00805f9b34fb", "Heart Rate Control Point");
        mCharacteristics.put("00002a37-0000-1000-8000-00805f9b34fb", "Heart Rate Measurement");
        mCharacteristics.put("00002a4c-0000-1000-8000-00805f9b34fb", "HID Control Point");
        mCharacteristics.put("00002a4a-0000-1000-8000-00805f9b34fb", "HID Information");
        mCharacteristics.put("00002a2a-0000-1000-8000-00805f9b34fb", "IEEE 11073-20601 Regulatory Certification Data List");
        mCharacteristics.put("00002a36-0000-1000-8000-00805f9b34fb", "Intermediate Cuff Pressure");
        mCharacteristics.put("00002a1e-0000-1000-8000-00805f9b34fb", "Intermediate Temperature");
        mCharacteristics.put("00002a6b-0000-1000-8000-00805f9b34fb", "LN Control Point");
        mCharacteristics.put("00002a6a-0000-1000-8000-00805f9b34fb", "LN Feature");
        mCharacteristics.put("00002a0f-0000-1000-8000-00805f9b34fb", "Local Time Information");
        mCharacteristics.put("00002a67-0000-1000-8000-00805f9b34fb", "Location and Speed");
        mCharacteristics.put("00002a29-0000-1000-8000-00805f9b34fb", "Manufacturer Name String");
        mCharacteristics.put("00002a21-0000-1000-8000-00805f9b34fb", "Measurement Interval");
        mCharacteristics.put("00002a24-0000-1000-8000-00805f9b34fb", "Model Number String");
        mCharacteristics.put("00002a68-0000-1000-8000-00805f9b34fb", "Navigation");
        mCharacteristics.put("00002a46-0000-1000-8000-00805f9b34fb", "New Alert");
        mCharacteristics.put("00002a04-0000-1000-8000-00805f9b34fb", "Peripheral Preferred Connection Parameters");
        mCharacteristics.put("00002a02-0000-1000-8000-00805f9b34fb", "Peripheral Privacy Flag");
        mCharacteristics.put("00002a50-0000-1000-8000-00805f9b34fb", "PnP ID");
        mCharacteristics.put("00002a69-0000-1000-8000-00805f9b34fb", "Position Quality");
        mCharacteristics.put("00002a4e-0000-1000-8000-00805f9b34fb", "Protocol Mode");
        mCharacteristics.put("00002a03-0000-1000-8000-00805f9b34fb", "Reconnection Address");
        mCharacteristics.put("00002a52-0000-1000-8000-00805f9b34fb", "Record Access Control Point");
        mCharacteristics.put("00002a14-0000-1000-8000-00805f9b34fb", "Reference Time Information");
        mCharacteristics.put("00002a4d-0000-1000-8000-00805f9b34fb", "Report");
        mCharacteristics.put("00002a4b-0000-1000-8000-00805f9b34fb", "Report Map");
        mCharacteristics.put("00002a40-0000-1000-8000-00805f9b34fb", "Ringer Control Point");
        mCharacteristics.put("00002a41-0000-1000-8000-00805f9b34fb", "Ringer Setting");
        mCharacteristics.put("00002a54-0000-1000-8000-00805f9b34fb", "RSC Feature");
        mCharacteristics.put("00002a53-0000-1000-8000-00805f9b34fb", "RSC Measurement");
        mCharacteristics.put("00002a55-0000-1000-8000-00805f9b34fb", "SC Control Point");
        mCharacteristics.put("00002a4f-0000-1000-8000-00805f9b34fb", "Scan Interval Window");
        mCharacteristics.put("00002a31-0000-1000-8000-00805f9b34fb", "Scan Refresh");
        mCharacteristics.put("00002a5d-0000-1000-8000-00805f9b34fb", "Sensor Location");
        mCharacteristics.put("00002a25-0000-1000-8000-00805f9b34fb", "Serial Number String");
        mCharacteristics.put("00002a05-0000-1000-8000-00805f9b34fb", "Service Changed");
        mCharacteristics.put("00002a28-0000-1000-8000-00805f9b34fb", "Software Revision String");
        mCharacteristics.put("00002a47-0000-1000-8000-00805f9b34fb", "Supported New Alert Category");
        mCharacteristics.put("00002a48-0000-1000-8000-00805f9b34fb", "Supported Unread Alert Category");
        mCharacteristics.put("00002a23-0000-1000-8000-00805f9b34fb", "System ID");
        mCharacteristics.put("00002a1c-0000-1000-8000-00805f9b34fb", "Temperature Measurement");
        mCharacteristics.put("00002a1d-0000-1000-8000-00805f9b34fb", "Temperature Type");
        mCharacteristics.put("00002a12-0000-1000-8000-00805f9b34fb", "Time Accuracy");
        mCharacteristics.put("00002a13-0000-1000-8000-00805f9b34fb", "Time Source");
        mCharacteristics.put("00002a16-0000-1000-8000-00805f9b34fb", "Time Update Control Point");
        mCharacteristics.put("00002a17-0000-1000-8000-00805f9b34fb", "Time Update State");
        mCharacteristics.put("00002a11-0000-1000-8000-00805f9b34fb", "Time with DST");
        mCharacteristics.put("00002a0e-0000-1000-8000-00805f9b34fb", "Time Zone");
        mCharacteristics.put("00002a07-0000-1000-8000-00805f9b34fb", "Tx Power Level");
        mCharacteristics.put("00002a45-0000-1000-8000-00805f9b34fb", "Unread Alert Status");
        Integer num = 52;
        mValueFormats.put(num.intValue(), "32bit float");
        Integer num2 = 50;
        mValueFormats.put(num2.intValue(), "16bit float");
        Integer num3 = 34;
        mValueFormats.put(num3.intValue(), "16bit signed int");
        Integer num4 = 36;
        mValueFormats.put(num4.intValue(), "32bit signed int");
        Integer num5 = 33;
        mValueFormats.put(num5.intValue(), "8bit signed int");
        Integer num6 = 18;
        mValueFormats.put(num6.intValue(), "16bit unsigned int");
        Integer num7 = 20;
        mValueFormats.put(num7.intValue(), "32bit unsigned int");
        Integer num8 = 17;
        mValueFormats.put(num8.intValue(), "8bit unsigned int");
        Integer num9 = 833;
        mAppearance.put(num9.intValue(), "Heart Rate Sensor: Belt");
        Integer num10 = 832;
        mAppearance.put(num10.intValue(), "Generic Heart Rate Sensor");
        Integer num11 = 0;
        mAppearance.put(num11.intValue(), "Unknown");
        Integer num12 = 64;
        mAppearance.put(num12.intValue(), "Generic Phone");
        Integer num13 = 1157;
        mAppearance.put(num13.intValue(), "Cycling: Speed and Cadence Sensor");
        Integer num14 = 1152;
        mAppearance.put(num14.intValue(), "General Cycling");
        Integer num15 = 1153;
        mAppearance.put(num15.intValue(), "Cycling Computer");
        Integer num16 = 1154;
        mAppearance.put(num16.intValue(), "Cycling: Speed Sensor");
        Integer num17 = 1155;
        mAppearance.put(num17.intValue(), "Cycling: Cadence Sensor");
        Integer num18 = 1156;
        mAppearance.put(num18.intValue(), "Cycling: Speed and Cadence Sensor");
        mAppearance.put(num13.intValue(), "Cycling: Power Sensor");
        mHeartRateSensorLocation.put(num11.intValue(), "Other");
        Integer num19 = 1;
        mHeartRateSensorLocation.put(num19.intValue(), "Chest");
        Integer num20 = 2;
        mHeartRateSensorLocation.put(num20.intValue(), "Wrist");
        Integer num21 = 3;
        mHeartRateSensorLocation.put(num21.intValue(), "Finger");
        Integer num22 = 4;
        mHeartRateSensorLocation.put(num22.intValue(), "Hand");
        Integer num23 = 5;
        mHeartRateSensorLocation.put(num23.intValue(), "Ear Lobe");
        Integer num24 = 6;
        mHeartRateSensorLocation.put(num24.intValue(), "Foot");
    }

    public static boolean isCharacteristic(String str) {
        return mCharacteristics.containsKey(str);
    }

    public static boolean isService(String str) {
        return mServices.containsKey(str);
    }

    public static String resolveAppearance(int i) {
        return mAppearance.get(Integer.valueOf(i).intValue(), "Unknown Appearance");
    }

    public static String resolveCharacteristicName(String str) {
        String str2 = mCharacteristics.get(str);
        return str2 == null ? DEFAULT_CHARACTERISTIC_NAME : str2;
    }

    public static String resolveHeartRateSensorLocation(int i) {
        return mHeartRateSensorLocation.get(Integer.valueOf(i).intValue(), "Other");
    }

    public static String resolveServiceName(String str) {
        String str2 = mServices.get(str);
        return str2 == null ? "Unknown Service" : str2;
    }

    public static String resolveUuid(String str) {
        String str2 = mServices.get(str);
        if (str2 != null) {
            return "Service: " + str2;
        }
        String str3 = mCharacteristics.get(str);
        if (str3 == null) {
            return "Unknown UUID";
        }
        return "Characteristic: " + str3;
    }

    public static String resolveValueTypeDescription(int i) {
        return mValueFormats.get(Integer.valueOf(i).intValue(), "Unknown Format");
    }
}
