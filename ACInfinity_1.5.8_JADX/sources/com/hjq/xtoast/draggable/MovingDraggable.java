package com.hjq.xtoast.draggable;

import android.view.MotionEvent;
import android.view.View;

public class MovingDraggable extends BaseDraggable {
    private float mViewDownX;
    private float mViewDownY;

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mViewDownX = motionEvent.getX();
            this.mViewDownY = motionEvent.getY();
        } else if (action != 1) {
            if (action == 2) {
                updateLocation(motionEvent.getRawX() - this.mViewDownX, (motionEvent.getRawY() - ((float) getStatusBarHeight())) - this.mViewDownY);
            }
        } else if (this.mViewDownX == motionEvent.getX() && this.mViewDownY == motionEvent.getY()) {
            return false;
        } else {
            return true;
        }
        return false;
    }
}
