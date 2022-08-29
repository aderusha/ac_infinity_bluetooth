package com.google.common.graph;

public interface SuccessorsFunction<N> {
    Iterable<? extends N> successors(N n);
}
