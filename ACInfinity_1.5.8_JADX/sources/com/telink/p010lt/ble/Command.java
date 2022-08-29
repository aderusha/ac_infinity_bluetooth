package com.telink.p010lt.ble;

import com.eternal.export.CSVUtil;
import com.telink.p010lt.util.Arrays;
import java.util.UUID;

/* renamed from: com.telink.lt.ble.Command */
public class Command {
    public UUID characteristicUUID;
    public byte[] data;
    public int delay;
    public UUID descriptorUUID;
    public UUID serviceUUID;
    public Object tag;
    public CommandType type;

    /* renamed from: com.telink.lt.ble.Command$Callback */
    public interface Callback {
        void error(Peripheral peripheral, Command command, String str);

        void success(Peripheral peripheral, Command command, Object obj);

        boolean timeout(Peripheral peripheral, Command command);
    }

    /* renamed from: com.telink.lt.ble.Command$CommandType */
    public enum CommandType {
        READ,
        READ_DESCRIPTOR,
        WRITE,
        WRITE_NO_RESPONSE,
        ENABLE_NOTIFY,
        DISABLE_NOTIFY
    }

    public Command() {
        this((UUID) null, (UUID) null, CommandType.WRITE);
    }

    public Command(UUID uuid, UUID uuid2, CommandType commandType) {
        this(uuid, uuid2, commandType, (byte[]) null);
    }

    public Command(UUID uuid, UUID uuid2, CommandType commandType, byte[] bArr) {
        this(uuid, uuid2, commandType, bArr, (Object) null);
    }

    public Command(UUID uuid, UUID uuid2, CommandType commandType, byte[] bArr, Object obj) {
        this.serviceUUID = uuid;
        this.characteristicUUID = uuid2;
        this.type = commandType;
        this.data = bArr;
        this.tag = obj;
    }

    public static Command newInstance() {
        return new Command();
    }

    public void clear() {
        this.serviceUUID = null;
        this.characteristicUUID = null;
        this.descriptorUUID = null;
        this.data = null;
    }

    public String toString() {
        byte[] bArr = this.data;
        String bytesToHexString = bArr != null ? Arrays.bytesToHexString(bArr, CSVUtil.COLUMN_SEPARATOR) : "";
        return "{ tag : " + this.tag + ", type : " + this.type + " CHARACTERISTIC_UUID :" + this.characteristicUUID.toString() + " data: " + bytesToHexString + " delay :" + this.delay + "}";
    }
}
