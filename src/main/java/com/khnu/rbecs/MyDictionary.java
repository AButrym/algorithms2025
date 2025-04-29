package com.khnu.rbecs;

public interface MyDictionary
        extends HasSize, Iterable<MyDictionary.Entry>
{
    String put(String key, String value);
    String get(String key);
    String remove(String key);
    boolean containsKey(String key);

    interface Entry {
        String getKey();
        String getValue();
        void setValue(String value);
    }
}
