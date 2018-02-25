package com.Prophet.pMap;

import java.util.Set;

public interface pMap<K,V> {

    public static class Entry<K,V> {
        K key;
        V value;

        public Entry(K k, V v){
            this.key = k;
            this.value = v;
        }

        public K getKey(){
            return key;
        }

        public V getValue(){
            return value;
        }

        @Override
        public String toString() {
            return "["+key+", "+value+"]";
        }
    }

    public void clear();

    public boolean containsKey(K k);

    public boolean containsValue(V v);

    public Set<Entry<K,V>> entrySet();

    public V get(K key);

    public boolean isEmpty();

    public Set<K> keySet();

    public V put(K key, V value);

    public void remove(K key);

    public int size();

    public Set<V> values();



}
