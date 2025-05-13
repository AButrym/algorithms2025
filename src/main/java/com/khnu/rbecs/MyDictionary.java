package com.khnu.rbecs;

public interface MyDictionary<K, V>
        extends HasSize, Iterable<MyDictionary.Entry<K, V>>
{
    V put(K key, V value);
    V get(K key);
    V remove(K key);
    boolean containsKey(K key);

    interface Entry<K1, V1> {
        K1 getKey();
        V1 getValue();
        void setValue(V1 value);
    }
}
