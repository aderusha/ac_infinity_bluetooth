package com.eternal.data;

import androidx.lifecycle.MutableLiveData;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.utils.Utils;
import java.util.Locale;

public class TargetModel {
    public MutableLiveData<String> descTxt = new MutableLiveData<>();
    private int distance;
    public MutableLiveData<Integer> expand = new MutableLiveData<>();
    private int min;
    public BindingCommand<Void> onClose = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            TargetModel.this.expand.setValue(0);
            TargetModel targetModel = TargetModel.this;
            targetModel.setTarget(targetModel.target);
        }
    });
    public BindingCommand<Void> onExpand = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (TargetModel.this.expand.getValue() == null || TargetModel.this.expand.getValue().intValue() == 1) {
                TargetModel.this.expand.setValue(2);
                return;
            }
            TargetModel.this.expand.setValue(1);
            TargetModel targetModel = TargetModel.this;
            targetModel.setTarget(targetModel.target);
        }
    });
    public MutableLiveData<String> scoreTxt = new MutableLiveData<>();
    private int step;
    /* access modifiers changed from: private */
    public int target;
    public MutableLiveData<String> targetTxt = new MutableLiveData<>();
    public MutableLiveData<String> titleTxt = new MutableLiveData<>();
    public MutableLiveData<String> titleTxt2 = new MutableLiveData<>();
    private final int type;
    private String unit;
    public MutableLiveData<String> unitTxt = new MutableLiveData<>();

    public void setTarget(int i) {
        this.target = i;
        if (i == -32768) {
            this.targetTxt.setValue("");
            return;
        }
        int i2 = this.type;
        if (i2 == 0 || i2 == 1) {
            MutableLiveData<String> mutableLiveData = this.targetTxt;
            mutableLiveData.setValue(i + "");
            return;
        }
        this.targetTxt.setValue(String.format(Locale.ENGLISH, "%.1f", new Object[]{Float.valueOf(((float) i) / 10.0f)}));
    }

    public int getTarget() {
        return this.target;
    }

    public int getMin() {
        return this.min;
    }

    public int getMax() {
        return this.min + this.distance;
    }

    public void setMin(int i) {
        this.min = i;
    }

    public String getUnit() {
        return this.unit;
    }

    public int getStep() {
        return this.step;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public void setData(int i, int i2, int i3, String str) {
        this.min = i;
        this.distance = i2;
        this.step = i3;
        this.unit = str;
        this.unitTxt.setValue(str);
    }

    TargetModel(int i) {
        this.type = i;
        if (i == 0) {
            this.titleTxt.setValue("Target Temperature");
            this.titleTxt2.setValue("TARGET TEMPERATURE ANALYSIS");
        } else if (i == 1) {
            this.titleTxt.setValue("Target Humidity");
            this.titleTxt2.setValue("TARGET HUMIDITY ANALYSIS");
        } else {
            this.titleTxt.setValue("Target VPD");
            this.titleTxt2.setValue("TARGET VPD ANALYSIS");
        }
    }

    public void setExpand(int i) {
        this.expand.setValue(Integer.valueOf(i));
    }

    public String setDesc(String str, String str2, int i, int i2, int i3) {
        String str3;
        String str4;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        int i7 = (100 - i5) - i6;
        if (i5 == -32768 || i6 == -32768 || i7 < 0 || i7 > 100) {
            this.scoreTxt.setValue("SCORE ");
            this.descTxt.setValue("");
            return "SCORE ";
        }
        String str5 = "SCORE " + i7;
        this.scoreTxt.setValue(str5);
        int i8 = this.type;
        String str6 = i8 == 0 ? "temp" : i8 == 1 ? "humidity" : "vpd";
        if (i4 != 0) {
            str3 = i4 != 1 ? i4 != 2 ? i4 != 3 ? i4 != 4 ? "" : "year" : "month" : "week" : "day";
        } else {
            str3 = "hour";
        }
        StringBuilder sb = new StringBuilder();
        if (this.type == 2) {
            str4 = String.format(Locale.ENGLISH, "%.1f", new Object[]{Float.valueOf(((float) this.step) / 10.0f)});
        } else {
            str4 = "" + this.step;
        }
        sb.append(Utils.getString(C1835R.string.target_desc_first, str6, str4, this.unit));
        if (i4 < 5) {
            sb.append(Utils.getString(C1835R.string.target_desc_second_1, str3, str));
        } else {
            sb.append(Utils.getString(C1835R.string.target_desc_second_2, str, str2));
        }
        if (i7 == 100) {
            this.descTxt.setValue(sb.toString());
            return str5;
        }
        if (i5 == i6) {
            sb.append(Utils.getString(C1835R.string.target_desc_third_2, str6, Integer.valueOf(i2), Integer.valueOf(i3)));
        } else {
            int i9 = C1835R.string.target_desc_third_1;
            Object[] objArr = new Object[3];
            objArr[0] = str6;
            objArr[1] = i5 > i6 ? "above" : "below";
            objArr[2] = Integer.valueOf(Math.max(i2, i3));
            sb.append(Utils.getString(i9, objArr));
        }
        int i10 = this.type;
        sb.append(Utils.getString(C1835R.string.target_desc_four, i10 == 0 ? i5 > i6 ? "AC unit" : i5 < i6 ? "heater" : "AC unit/heater" : i10 == 1 ? i5 > i6 ? "dehumidifier" : i5 < i6 ? "humidifier" : "humidifier/dehumidifier" : i5 > i6 ? "AC unit/humidifier" : i5 < i6 ? "heater/dehumidifier" : "humidifier/dehumidifier/AC unit/heater"));
        this.descTxt.setValue(sb.toString());
        return str5;
    }
}
