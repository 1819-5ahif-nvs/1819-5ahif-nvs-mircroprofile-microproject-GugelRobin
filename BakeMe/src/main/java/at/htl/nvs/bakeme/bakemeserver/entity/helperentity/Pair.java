package at.htl.nvs.bakeme.bakemeserver.entity.helperentity;

import java.io.Serializable;

public class Pair<K, V> implements Serializable{

    private K key;

    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Pair() {
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
