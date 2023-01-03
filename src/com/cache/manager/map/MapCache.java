package com.cache.manager.map;

import java.util.HashMap;
import java.util.Map;

import com.cache.manager.Cache;

public class MapCache<K, V> implements Cache<K, V> {


    private Map<K, V> cache = new HashMap<>();

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }
}
