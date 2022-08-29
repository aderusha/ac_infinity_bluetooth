package p019no.nordicsemi.android.dfu;

import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/* renamed from: no.nordicsemi.android.dfu.DfuServiceController */
public class DfuServiceController implements DfuController {
    private boolean mAborted;
    private final LocalBroadcastManager mBroadcastManager;
    private boolean mPaused;

    DfuServiceController(Context context) {
        this.mBroadcastManager = LocalBroadcastManager.getInstance(context);
    }

    public void pause() {
        if (!this.mAborted && !this.mPaused) {
            this.mPaused = true;
            Intent intent = new Intent(DfuBaseService.BROADCAST_ACTION);
            intent.putExtra(DfuBaseService.EXTRA_ACTION, 0);
            this.mBroadcastManager.sendBroadcast(intent);
        }
    }

    public void resume() {
        if (!this.mAborted && this.mPaused) {
            this.mPaused = false;
            Intent intent = new Intent(DfuBaseService.BROADCAST_ACTION);
            intent.putExtra(DfuBaseService.EXTRA_ACTION, 1);
            this.mBroadcastManager.sendBroadcast(intent);
        }
    }

    public void abort() {
        if (!this.mAborted) {
            this.mAborted = true;
            this.mPaused = false;
            Intent intent = new Intent(DfuBaseService.BROADCAST_ACTION);
            intent.putExtra(DfuBaseService.EXTRA_ACTION, 2);
            this.mBroadcastManager.sendBroadcast(intent);
        }
    }

    public boolean isPaused() {
        return this.mPaused;
    }

    public boolean isAborted() {
        return this.mAborted;
    }
}
