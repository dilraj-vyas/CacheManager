package com.cache.manager.linklist;

import com.cache.manager.Cache;

public class LinkListCache<K, V> implements Cache<K, V> {

    private Node head;
    private int size;
    private int capacity;

    public LinkListCache(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void put(K key, V value) {
        Node node = head;

        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.getNext();
        }

        // If the cache is at capacity, remove the least recently used element
        node = new Node();
        node.key = key;
        node.value = value;
        node.next = head;
        head = node;
        size++;

        // If the cache is at capacity, remove the least recently used element
        if (size > capacity) {
            Node prev = null;
            node = head;
            while (node.next != null) {
                prev = node;
                node = node.next;
            }
            prev.next = null;
            size--;

        }


    }

    @Override
    public V get(K key) {
        // Search for the key in the cache
        Node node = head;
        while (node != null) {
            if (node.key.equals(key)) {
                // Key found, move it to the front of the list and return the value
                V value = (V) node.getValue();
                remove(key);
                put(key, value);
                return value;
            }
            node = node.next;
        }
        // Key not found, return null
        return null;
    }

    @Override
    public void remove(K key) {
        Node prev = null;
        Node node = head;
        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    head = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return;
            }
            prev = node;
            node = node.next;
        }
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }
}
