package androidx.core.content;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService;

class UnusedAppRestrictionsBackportServiceConnection implements ServiceConnection {
    private final Context mContext;
    private boolean mHasBoundService = false;
    ResolvableFuture<Integer> mResultFuture;
    IUnusedAppRestrictionsBackportService mUnusedAppRestrictionsService = null;

    UnusedAppRestrictionsBackportServiceConnection(Context context) {
        this.mContext = context;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        IUnusedAppRestrictionsBackportService asInterface = IUnusedAppRestrictionsBackportService.Stub.asInterface(iBinder);
        this.mUnusedAppRestrictionsService = asInterface;
        try {
            asInterface.isPermissionRevocationEnabledForApp(getBackportCallback());
        } catch (RemoteException unused) {
            this.mResultFuture.set(0);
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.mUnusedAppRestrictionsService = null;
    }

    public void connectAndFetchResult(ResolvableFuture<Integer> resolvableFuture) {
        if (!this.mHasBoundService) {
            this.mHasBoundService = true;
            this.mResultFuture = resolvableFuture;
            this.mContext.bindService(new Intent(UnusedAppRestrictionsBackportService.ACTION_UNUSED_APP_RESTRICTIONS_BACKPORT_CONNECTION).setPackage(PackageManagerCompat.getPermissionRevocationVerifierApp(this.mContext.getPackageManager())), this, 1);
            return;
        }
        throw new IllegalStateException("Each UnusedAppRestrictionsBackportServiceConnection can only be bound once.");
    }

    public void disconnectFromService() {
        if (this.mHasBoundService) {
            this.mHasBoundService = false;
            this.mContext.unbindService(this);
            return;
        }
        throw new IllegalStateException("bindService must be called before unbind");
    }

    private IUnusedAppRestrictionsBackportCallback getBackportCallback() {
        return new IUnusedAppRestrictionsBackportCallback.Stub() {
            public void onIsPermissionRevocationEnabledForAppResult(boolean z, boolean z2) throws RemoteException {
                if (!z) {
                    UnusedAppRestrictionsBackportServiceConnection.this.mResultFuture.set(0);
                    Log.e(PackageManagerCompat.LOG_TAG, "Unable to retrieve the permission revocation setting from the backport");
                } else if (z2) {
                    UnusedAppRestrictionsBackportServiceConnection.this.mResultFuture.set(3);
                } else {
                    UnusedAppRestrictionsBackportServiceConnection.this.mResultFuture.set(2);
                }
            }
        };
    }
}
