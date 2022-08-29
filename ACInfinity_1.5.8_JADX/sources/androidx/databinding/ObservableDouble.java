package androidx.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class ObservableDouble extends BaseObservableField implements Parcelable, Serializable {
    public static final Parcelable.Creator<ObservableDouble> CREATOR = new Parcelable.Creator<ObservableDouble>() {
        public ObservableDouble createFromParcel(Parcel parcel) {
            return new ObservableDouble(parcel.readDouble());
        }

        public ObservableDouble[] newArray(int i) {
            return new ObservableDouble[i];
        }
    };
    static final long serialVersionUID = 1;
    private double mValue;

    public int describeContents() {
        return 0;
    }

    public ObservableDouble(double d) {
        this.mValue = d;
    }

    public ObservableDouble() {
    }

    public ObservableDouble(Observable... observableArr) {
        super(observableArr);
    }

    public double get() {
        return this.mValue;
    }

    public void set(double d) {
        if (d != this.mValue) {
            this.mValue = d;
            notifyChange();
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mValue);
    }
}
