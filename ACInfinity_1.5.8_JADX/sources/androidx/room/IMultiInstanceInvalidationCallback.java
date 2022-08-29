package androidx.room;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMultiInstanceInvalidationCallback extends IInterface {

    public static class Default implements IMultiInstanceInvalidationCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onInvalidation(String[] strArr) throws RemoteException {
        }
    }

    void onInvalidation(String[] strArr) throws RemoteException;

    public static abstract class Stub extends Binder implements IMultiInstanceInvalidationCallback {
        private static final String DESCRIPTOR = "androidx.room.IMultiInstanceInvalidationCallback";
        static final int TRANSACTION_onInvalidation = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMultiInstanceInvalidationCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMultiInstanceInvalidationCallback)) {
                return new Proxy(iBinder);
            }
            return (IMultiInstanceInvalidationCallback) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onInvalidation(parcel.createStringArray());
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IMultiInstanceInvalidationCallback {
            public static IMultiInstanceInvalidationCallback sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void onInvalidation(String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringArray(strArr);
                    if (this.mRemote.transact(1, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onInvalidation(strArr);
                    }
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iMultiInstanceInvalidationCallback == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iMultiInstanceInvalidationCallback;
                return true;
            }
        }

        public static IMultiInstanceInvalidationCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
