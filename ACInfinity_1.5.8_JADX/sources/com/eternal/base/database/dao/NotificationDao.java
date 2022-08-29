package com.eternal.base.database.dao;

import com.eternal.base.concat.NotificationName;
import com.eternal.base.database.entity.Notification;
import java.util.List;

public interface NotificationDao {
    void deleteNotification(String str, byte b, int i, byte b2);

    void deleteNotification(String str, int i, byte b);

    void deleteNotification(String str, int i, byte b, byte b2);

    void insertNotification(Notification notification);

    Notification loadByMacAndId(String str, byte b, int i, byte b2);

    List<Notification> loadMacAll(String str);

    List<Notification> loadMacAll(String str, byte b);

    List<NotificationName> loadMacAllName(String str);

    List<NotificationName> loadMacAllName(String str, byte b);

    String loadNameByMac(String str, byte b, String str2, List<Byte> list);

    void update(Notification notification);

    void updateOpen(String str, byte b, int i, byte b2, boolean z);
}
