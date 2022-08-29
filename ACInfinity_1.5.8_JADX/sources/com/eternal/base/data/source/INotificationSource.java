package com.eternal.base.data.source;

import com.eternal.base.concat.DeviceModelInfo;
import com.eternal.base.concat.NotificationName;
import com.eternal.base.database.entity.Notification;
import java.util.List;

public interface INotificationSource {
    void addNotification(Notification notification);

    void create(Notification notification, boolean z, byte b);

    void deleteNotification(String str, byte b, int i, byte b2);

    void deleteNotification(String str, byte b, int i, byte b2, int i2);

    DeviceModelInfo getModelInfo(String str);

    Notification getNotification(String str, byte b, int i, byte b2);

    List<NotificationName> getNotificationNames(String str, byte b);

    List<Notification> getNotifications(String str);

    List<Notification> getNotifications(String str, byte b);

    Boolean nameExists(String str, byte b, String str2, byte b2);

    void setNotification(Notification notification);

    void setNotificationOpen(String str, byte b, int i, byte b2, boolean z);
}
