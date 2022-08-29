package androidx.room;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.room.IMultiInstanceInvalidationCallback;

public interface IMultiInstanceInvalidationService extends IInterface {

    public static class Default implements IMultiInstanceInvalidationService {
        public IBinder asBinder() {
            return null;
        }

        public void broadcastInvalidation(int i, String[] strArr) throws RemoteException {
        }

        public int registerCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, String str) throws RemoteException {
            return 0;
        }

        public void unregisterCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, int i) throws RemoteException {
        }
    }

    void broadcastInvalidation(int i, String[] strArr) throws RemoteException;

    int registerCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, String str) throws RemoteException;

    void unregisterCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IMultiInstanceInvalidationService {
        private static final String DESCRIPTOR = "androidx.room.IMultiInstanceInvalidationService";
        static final int TRANSACTION_broadcastInvalidation = 3;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_unregisterCallback = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMultiInstanceInvalidationService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMultiInstanceInvalidationService)) {
                return new Proxy(iBinder);
            }
            return (IMultiInstanceInvalidationService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int registerCallback = registerCallback(IMultiInstanceInvalidationCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(registerCallback);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                unregisterCallback(IMultiInstanceInvalidationCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                broadcastInvalidation(parcel.readInt(), parcel.createStringArray());
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IMultiInstanceInvalidationService {
            public static IMultiInstanceInvalidationService sDefaultImpl;
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

            public int registerCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMultiInstanceInvalidationCallback != null ? iMultiInstanceInvalidationCallback.asBinder() : null);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCallback(iMultiInstanceInvalidationCallback, str);
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unregisterCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMultiInstanceInvalidationCallback != null ? iMultiInstanceInvalidationCallback.asBinder() : null);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unregisterCallback(iMultiInstanceInvalidationCallback, i);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void broadcastInvalidation(int i, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStringArray(strArr);
                    if (this.mRemote.transact(3, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().broadcastInvalidation(i, strArr);
                    }
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMultiInstanceInvalidationService iMultiInstanceInvalidationService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iMultiInstanceInvalidationService == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iMultiInstanceInvalidationService;
                return true;
            }
        }

        public static IMultiInstanceInvalidationService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
