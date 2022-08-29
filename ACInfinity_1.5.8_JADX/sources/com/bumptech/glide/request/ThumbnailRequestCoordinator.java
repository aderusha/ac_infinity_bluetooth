package com.bumptech.glide.request;

public class ThumbnailRequestCoordinator implements RequestCoordinator, Request {
    private Request full;
    private boolean isRunning;
    private final RequestCoordinator parent;
    private Request thumb;

    ThumbnailRequestCoordinator() {
        this((RequestCoordinator) null);
    }

    public ThumbnailRequestCoordinator(RequestCoordinator requestCoordinator) {
        this.parent = requestCoordinator;
    }

    public void setRequests(Request request, Request request2) {
        this.full = request;
        this.thumb = request2;
    }

    public boolean canSetImage(Request request) {
        return parentCanSetImage() && (request.equals(this.full) || !this.full.isResourceSet());
    }

    private boolean parentCanSetImage() {
        RequestCoordinator requestCoordinator = this.parent;
        return requestCoordinator == null || requestCoordinator.canSetImage(this);
    }

    public boolean canNotifyStatusChanged(Request request) {
        return parentCanNotifyStatusChanged() && request.equals(this.full) && !isAnyResourceSet();
    }

    public boolean canNotifyCleared(Request request) {
        return parentCanNotifyCleared() && request.equals(this.full);
    }

    private boolean parentCanNotifyCleared() {
        RequestCoordinator requestCoordinator = this.parent;
        return requestCoordinator == null || requestCoordinator.canNotifyCleared(this);
    }

    private boolean parentCanNotifyStatusChanged() {
        RequestCoordinator requestCoordinator = this.parent;
        return requestCoordinator == null || requestCoordinator.canNotifyStatusChanged(this);
    }

    public boolean isAnyResourceSet() {
        return parentIsAnyResourceSet() || isResourceSet();
    }

    public void onRequestSuccess(Request request) {
        if (!request.equals(this.thumb)) {
            RequestCoordinator requestCoordinator = this.parent;
            if (requestCoordinator != null) {
                requestCoordinator.onRequestSuccess(this);
            }
            if (!this.thumb.isComplete()) {
                this.thumb.clear();
            }
        }
    }

    public void onRequestFailed(Request request) {
        RequestCoordinator requestCoordinator;
        if (request.equals(this.full) && (requestCoordinator = this.parent) != null) {
            requestCoordinator.onRequestFailed(this);
        }
    }

    private boolean parentIsAnyResourceSet() {
        RequestCoordinator requestCoordinator = this.parent;
        return requestCoordinator != null && requestCoordinator.isAnyResourceSet();
    }

    public void begin() {
        this.isRunning = true;
        if (!this.full.isComplete() && !this.thumb.isRunning()) {
            this.thumb.begin();
        }
        if (this.isRunning && !this.full.isRunning()) {
            this.full.begin();
        }
    }

    public void clear() {
        this.isRunning = false;
        this.thumb.clear();
        this.full.clear();
    }

    public boolean isRunning() {
        return this.full.isRunning();
    }

    public boolean isComplete() {
        return this.full.isComplete() || this.thumb.isComplete();
    }

    public boolean isResourceSet() {
        return this.full.isResourceSet() || this.thumb.isResourceSet();
    }

    public boolean isCleared() {
        return this.full.isCleared();
    }

    public boolean isFailed() {
        return this.full.isFailed();
    }

    public void recycle() {
        this.full.recycle();
        this.thumb.recycle();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0027 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isEquivalentTo(com.bumptech.glide.request.Request r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof com.bumptech.glide.request.ThumbnailRequestCoordinator
            r1 = 0
            if (r0 == 0) goto L_0x0028
            com.bumptech.glide.request.ThumbnailRequestCoordinator r4 = (com.bumptech.glide.request.ThumbnailRequestCoordinator) r4
            com.bumptech.glide.request.Request r0 = r3.full
            if (r0 != 0) goto L_0x0010
            com.bumptech.glide.request.Request r0 = r4.full
            if (r0 != 0) goto L_0x0028
            goto L_0x0018
        L_0x0010:
            com.bumptech.glide.request.Request r2 = r4.full
            boolean r0 = r0.isEquivalentTo(r2)
            if (r0 == 0) goto L_0x0028
        L_0x0018:
            com.bumptech.glide.request.Request r0 = r3.thumb
            com.bumptech.glide.request.Request r4 = r4.thumb
            if (r0 != 0) goto L_0x0021
            if (r4 != 0) goto L_0x0028
            goto L_0x0027
        L_0x0021:
            boolean r4 = r0.isEquivalentTo(r4)
            if (r4 == 0) goto L_0x0028
        L_0x0027:
            r1 = 1
        L_0x0028:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.ThumbnailRequestCoordinator.isEquivalentTo(com.bumptech.glide.request.Request):boolean");
    }
}
