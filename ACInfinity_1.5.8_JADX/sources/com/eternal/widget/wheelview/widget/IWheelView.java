package com.eternal.widget.wheelview.widget;

import com.eternal.widget.wheelview.adapter.BaseWheelAdapter;
import java.util.HashMap;
import java.util.List;

public interface IWheelView<T> {
    public static final boolean CLICKABLE = false;
    public static final boolean LOOP = false;
    public static final int WHEEL_SIZE = 3;

    void join(WheelView wheelView);

    void joinDatas(HashMap<String, List<T>> hashMap);

    void setLoop(boolean z);

    void setWheelAdapter(BaseWheelAdapter<T> baseWheelAdapter);

    void setWheelClickable(boolean z);

    void setWheelData(List<T> list);

    void setWheelSize(int i);
}
