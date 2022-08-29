package androidx.core.app.unusedapprestrictions;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IUnusedAppRestrictionsBackportCallback extends IInterface {

    public static class Default implements IUnusedAppRestrictionsBackportCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onIsPermissionRevocationEnabledForAppResult(boolean z, boolean z2) throws RemoteException {
        }
    }

    void onIsPermissionRevocationEnabledForAppResult(boolean z, boolean z2) throws RemoteException;

    public static abstract class Stub extends Binder implements IUnusedAppRestrictionsBackportCallback {
        private static final String DESCRIPTOR = "androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback";
        static final int TRANSACTION_onIsPermissionRevocationEnabledForAppResult = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUnusedAppRestrictionsBackportCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IUnusedAppRestrictionsBackportCallback)) {
                return new Proxy(iBinder);
            }
            return (IUnusedAppRestrictionsBackportCallback) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean z = false;
                boolean z2 = parcel.readInt() != 0;
                if (parcel.readInt() != 0) {
                    z = true;
                }
                onIsPermissionRevocationEnabledForAppResult(z2, z);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IUnusedAppRestrictionsBackportCallback {
            public static IUnusedAppRestrictionsBackportCallback sDefaultImpl;
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

            public void onIsPermissionRevocationEnabledForAppResult(boolean z, boolean z2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 0;
                    obtain.writeInt(z ? 1 : 0);
                    if (z2) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(1, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onIsPermissionRevocationEnabledForAppResult(z, z2);
                    }
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUnusedAppRestrictionsBackportCallback iUnusedAppRestrictionsBackportCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iUnusedAppRestrictionsBackportCallback == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iUnusedAppRestrictionsBackportCallback;
                return true;
            }
        }

        public static IUnusedAppRestrictionsBackportCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
