package com.eternal.base;

import com.eternal.base.concat.DeviceName;
import java.util.List;

public interface IToolBarAction {
    void onNameUpdate(List<DeviceName> list);

    void onSecond();
}
