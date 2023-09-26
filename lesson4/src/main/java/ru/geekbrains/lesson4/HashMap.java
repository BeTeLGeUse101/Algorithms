package ru.geekbrains.lesson4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashMap<K, V> implements Iterable<V> {

    private static final int INIT_BUCKET_COUNT = 16;
    private static final double LOAD_FACTOR = 0.75;

    private int size;
    private LinkedList<Entry<K, V>>[] buckets;

    public HashMap() {
        this(INIT_BUCKET_COUNT);
    }

    public HashMap(int initCount) {
        buckets = new LinkedList[initCount];
        for (int i = 0; i < initCount; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<V> iterator() {
        return new HashMapIterator();
    }

    class HashMapIterator implements Iterator<V> {
        private int bucketIndex = 0;
        private int listIndex = 0;

        @Override
        public boolean hasNext() {
            while (bucketIndex < buckets.length) {
                if (listIndex < buckets[bucketIndex].size()) {
                    return true;
                } else {
                    bucketIndex++;
                    listIndex = 0;
                }
            }
            return false;
        }

        @Override
        public V next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Entry<K, V> entry = buckets[bucketIndex].get(listIndex);
            listIndex++;
            return entry.getValue();
        }
    }

    class Entry<K, V> {
        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }
    }

    private int calculateBucketIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    private void resize() {
        int newCapacity = buckets.length * 2;
        LinkedList<Entry<K, V>>[] newBuckets = new LinkedList[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newBuckets[i] = new LinkedList<>();
        }
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                int index = Math.abs(entry.getKey().hashCode()) % newCapacity;
                newBuckets[index].add(entry);
            }
        }
        buckets = newBuckets;
    }

    public V put(K key, V value) {
        if (buckets.length * LOAD_FACTOR <= size) {
            resize();
        }

        int index = calculateBucketIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.value = value;
                return oldValue;
            }
        }

        bucket.add(new Entry<>(key, value));
        size++;
        return null;
    }

    public V get(K key) {
        int index = calculateBucketIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }

        return null;
    }

    public V remove(K key) {
        int index = calculateBucketIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                bucket.remove(entry);
                size--;
                return entry.getValue();
            }
        }

        return null;
    }

    public boolean containsKey(K key) {
        int index = calculateBucketIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }

        return false;
    }
}
