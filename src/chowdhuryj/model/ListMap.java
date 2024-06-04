/*
 * Course: CSC1120
 * Spring 2024
 * Lab 14 - Even More AutoComplete
 * Name: Jawadul Chowdhury
 * Created: 5/3/24
 */
package chowdhuryj.model;


import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.AbstractMap;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;

/**
 * class for ListMap
 * @param <K> key
 * @param <V> value
 */
public class ListMap<K extends Comparable<K>, V> implements Map<K, V> {

    private final List<Entry<K, V>> entries;

    /**
     * constructor
     */
    public ListMap() {
        entries = new ArrayList<>();
    }


    @Override
    public V put(K key, V value) {
        boolean found = false;
        V ret = null;
        for(int i = 0; i < entries.size() && !found; i++) {
            if(Objects.equals(entries.get(i).getKey(), key)) {
                ret = entries.get(i).getValue();
                entries.get(i).setValue(value);
                found = true;
            }
        }
        if(!found) {
            entries.add(new AbstractMap.SimpleEntry<>(key, value));
        }
        return ret;
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        boolean found = false;
        for(int i = 0; i < entries.size() && !found; i++) {
            if(Objects.equals(entries.get(i).getKey(), key)) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        boolean found = false;
        V ret = null;
        for(int i = 0; i < entries.size() && !found; i++) {
            if(Objects.equals(entries.get(i).getKey(), key)) {
                found = true;
                ret = entries.get(i).getValue();
            }
        }
        return ret;
    }


    @Override
    public V remove(Object key) {
        boolean found = false;
        V ret = null;
        for(int i = 0; i < entries.size() && !found; i++) {
            if(Objects.equals(entries.get(i).getKey(), key)) {
                found = true;
                ret = entries.get(i).getValue();
                entries.remove(i);
            }
        }
        return ret;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        entries.clear();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException();

    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new HashSet<>(entries);
    }
}