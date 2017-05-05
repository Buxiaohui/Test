package com.example.bxh.sayhello.cache;

/**
 * Created by buxiaohui on 5/5/17.
 */

public class Pair<K, V> {
    public K first;

    public V second;

    public Pair() {}

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }
}