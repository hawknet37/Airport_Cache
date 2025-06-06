package dev.hawknet37.airport_cache.entity; // Hoặc package khác tùy bạn

public class CacheEntry<T> {
    private final T value;
    private final long insertionTimeMillis;

    public CacheEntry(T value) {
        this.value = value;
        this.insertionTimeMillis = System.currentTimeMillis();
    }

    public T getValue() {
        return value;
    }

    public long getInsertionTimeMillis() {
        return insertionTimeMillis;
    }
}