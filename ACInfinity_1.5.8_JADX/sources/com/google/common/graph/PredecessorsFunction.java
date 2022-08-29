package com.google.common.graph;

public interface PredecessorsFunction<N> {
    Iterable<? extends N> predecessors(N n);
}
