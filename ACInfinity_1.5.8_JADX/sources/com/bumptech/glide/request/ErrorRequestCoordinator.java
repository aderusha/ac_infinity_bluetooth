package com.bumptech.glide.request;

public final class ErrorRequestCoordinator implements RequestCoordinator, Request {
    private Request error;
    private final RequestCoordinator parent;
    private Request primary;

    public ErrorRequestCoordinator(RequestCoordinator requestCoordinator) {
        this.parent = requestCoordinator;
    }

    public void setRequests(Request request, Request request2) {
        this.primary = request;
        this.error = request2;
    }

    public void begin() {
        if (!this.primary.isRunning()) {
            this.primary.begin();
        }
    }

    public void clear() {
        this.primary.clear();
        if (this.error.isRunning()) {
            this.error.clear();
        }
    }

    public boolean isRunning() {
        return (this.primary.isFailed() ? this.error : this.primary).isRunning();
    }

    public boolean isComplete() {
        return (this.primary.isFailed() ? this.error : this.primary).isComplete();
    }

    public boolean isResourceSet() {
        return (this.primary.isFailed() ? this.error : this.primary).isResourceSet();
    }

    public boolean isCleared() {
        return (this.primary.isFailed() ? this.error : this.primary).isCleared();
    }

    public boolean isFailed() {
        return this.primary.isFailed() && this.error.isFailed();
    }

    public void recycle() {
        this.primary.recycle();
        this.error.recycle();
    }

    public boolean isEquivalentTo(Request request) {
        if (!(request instanceof ErrorRequestCoordinator)) {
            return false;
        }
        ErrorRequestCoordinator errorRequestCoordinator = (ErrorRequestCoordinator) request;
        if (!this.primary.isEquivalentTo(errorRequestCoordinator.primary) || !this.error.isEquivalentTo(errorRequestCoordinator.error)) {
            return false;
        }
        return true;
    }

    public boolean canSetImage(Request request) {
        return parentCanSetImage() && isValidRequest(request);
    }

    private boolean parentCanSetImage() {
        RequestCoordinator requestCoordinator = this.parent;
        return requestCoordinator == null || requestCoordinator.canSetImage(this);
    }

    public boolean canNotifyStatusChanged(Request request) {
        return parentCanNotifyStatusChanged() && isValidRequest(request);
    }

    public boolean canNotifyCleared(Request request) {
        return parentCanNotifyCleared() && isValidRequest(request);
    }

    private boolean parentCanNotifyCleared() {
        RequestCoordinator requestCoordinator = this.parent;
        return requestCoordinator == null || requestCoordinator.canNotifyCleared(this);
    }

    private boolean parentCanNotifyStatusChanged() {
        RequestCoordinator requestCoordinator = this.parent;
        return requestCoordinator == null || requestCoordinator.canNotifyStatusChanged(this);
    }

    private boolean isValidRequest(Request request) {
        return request.equals(this.primary) || (this.primary.isFailed() && request.equals(this.error));
    }

    public boolean isAnyResourceSet() {
        return parentIsAnyResourceSet() || isResourceSet();
    }

    private boolean parentIsAnyResourceSet() {
        RequestCoordinator requestCoordinator = this.parent;
        return requestCoordinator != null && requestCoordinator.isAnyResourceSet();
    }

    public void onRequestSuccess(Request request) {
        RequestCoordinator requestCoordinator = this.parent;
        if (requestCoordinator != null) {
            requestCoordinator.onRequestSuccess(this);
        }
    }

    public void onRequestFailed(Request request) {
        if (request.equals(this.error)) {
            RequestCoordinator requestCoordinator = this.parent;
            if (requestCoordinator != null) {
                requestCoordinator.onRequestFailed(this);
            }
        } else if (!this.error.isRunning()) {
            this.error.begin();
        }
    }
}
