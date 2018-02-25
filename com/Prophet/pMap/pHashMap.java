package com.Prophet.pMap;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


@SuppressWarnings("unchecked")
public class pHashMap<K,V> implements pMap<K,V> {

    private static int DEFAULT_INITIAL_CAPACITY = 16;

    private static int MAX_CAPACITY = 1<<30;

    private int capacity;

    private static float DEFAULT_MAX_LOAD_FACTOR = 0.75F;

    private float loadFactorThreshold;

    private int size = 0;

    LinkedList<pMap.Entry<K,V>>[] data;

    public pHashMap(int initialCapacity, float loadFactorThreshold){
        if(initialCapacity > MAX_CAPACITY) this.capacity = MAX_CAPACITY;
        else this.capacity = trimCapacity(initialCapacity);
        this.loadFactorThreshold = loadFactorThreshold;
        data = new LinkedList[capacity];
    }

    public pHashMap(int initialCapacity){
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    public pHashMap(){
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }


    private static int supplementalHash(int h){
        h ^= (h>>>20)^(h>>>12);
        return h^(h>>>7)^(h>>>4);
    }

    private int hash(K key){
        return supplementalHash(key.hashCode()) & (capacity -1);
    }




    private int trimCapacity(int initialCapacity){
        int capacity = 1;
        while(capacity < initialCapacity) capacity<<=1;
        return capacity;
    }

    private void rehash(){
        Set<Entry<K,V>> entries = entrySet();
        capacity <<= 1;
        data = new LinkedList[capacity];
        size = 0;
        for(Entry<K,V> entry: entries){
            put(entry.getKey(), entry.getValue());
        }
    }

    private void removeEntries(){
        for (int i = 0; i < capacity; i++) {
            if(data[i]!=null) data[i].clear();
        }
    }

    @Override
    public void clear() {
        removeEntries();
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < capacity; i++) {
            if(data[i] != null){
                LinkedList<Entry<K,V>> bucket = data[i];
                for(Entry<K,V> entry: bucket){
                    if(entry.getValue().equals(value)) return true;
                }
            }
        }
        return false;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K,V>> set = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            if(data[i] != null){
                LinkedList<Entry<K,V>> bucket = data[i];
                for(Entry<K,V> entry: bucket){
                    set.add(entry);
                }
            }
        }
        return set;
    }

    @Override
    public V get(K key) {
        int bucketIndex = hash(key);
        if(data[bucketIndex]!=null){
            LinkedList<Entry<K,V>> bucket = data[bucketIndex];
            for(Entry<K,V> entry: bucket){
                if(entry.getKey().equals(key)) return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            if(data[i] != null){
                LinkedList<Entry<K,V>> bucket = data[i];
                for(Entry<K,V> entry: bucket){
                    set.add(entry.getKey());
                }
            }
        }
        return set;
    }

    @Override
    public V put(K key, V value) {
        int bucketIndex = hash(key);

        //Search the LinkedList in the bucket index, if the same key exists, replace the old value with new value.
        if(get(key) != null){
            LinkedList<Entry<K,V>> bucket = data[bucketIndex];
            for(Entry<K,V> entry: bucket){
                if(entry.getKey().equals(key)){
                    V oldVal = entry.getValue();
                    entry.value = value;
                    return oldVal;
                }
            }
        }

        if(size >= capacity*loadFactorThreshold){
            if(capacity == MAX_CAPACITY) throw new RuntimeException("Exceeding maximum capacity");
            rehash();
            bucketIndex = hash(key); //After rehash the index of bucket has changed.
        }

        if(data[bucketIndex] == null){
            data[bucketIndex] = new LinkedList<Entry<K,V>>();
        }

        data[bucketIndex].add(new Entry<K,V>(key, value));

        size++;
        return value;
    }

    @Override
    public void remove(K key) {
        int bucketIndex = hash(key);
        if(data[bucketIndex]!=null){
            LinkedList<Entry<K,V>> bucket = data[bucketIndex];
            for(Entry<K,V> entry: bucket){
                if(entry.getKey().equals(key)) {
                    bucket.remove(entry);
                    size--;
                    break;
                }
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<V> values() {
        Set<V> set = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            if(data[i] != null){
                LinkedList<Entry<K,V>> bucket = data[i];
                for(Entry<K,V> entry: bucket){
                    set.add(entry.getValue());
                }
            }
        }
        return set;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < capacity; i++) {
            if(data[i] != null && data[i].size() > 0){
                for(Entry<K,V> entry: data[i]) sb.append(entry);
            }
        }

        sb.append("]");
        return sb.toString();
    }
}
