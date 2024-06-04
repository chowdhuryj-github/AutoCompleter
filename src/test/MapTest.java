/*
 * Course: CSC1120
 * Spring 2024
 * Lab 14 - Even More AutoComplete
 * Name: Jawadul Chowdhury
 * Created: 5/3/24
 */
package test;

import chowdhuryj.model.ListMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.Map;

/**
 * Test for a subset of the Map interface that must be implemented
 * as part of the week 13 lab assignment.
 */
class MapTest {

    private Map<Integer, Integer> map;

    @BeforeEach
    void setup() {
        map = new ListMap<>();
    }

    @org.junit.jupiter.api.Test
    void sizeAndClear() {
        final int badKey = -2;
        Assertions.assertEquals(0, map.size());
        map.clear();
        Assertions.assertEquals(0, map.size());
        map.put(0, 0);
        Assertions.assertEquals(1, map.size());
        map.clear();
        Assertions.assertEquals(0, map.size());
        map.put(0, 0);
        map.put(badKey, 0);
        Assertions.assertEquals(2, map.size());
        map.clear();
        Assertions.assertEquals(0, map.size());
    }

    @org.junit.jupiter.api.Test
    void containsKey() {
        Assertions.assertFalse(map.containsKey(0));
        Assertions.assertFalse(map.containsKey(1));
        map.put(0, 0);
        Assertions.assertTrue(map.containsKey(0));
        Assertions.assertFalse(map.containsKey(1));
        map.put(1, 0);
        Assertions.assertTrue(map.containsKey(0));
        Assertions.assertTrue(map.containsKey(1));
        Assertions.assertFalse(map.containsKey(-1));
    }

    @org.junit.jupiter.api.Test
    void get() {
        final int value = -2;
        Assertions.assertNull(map.get(0));
        map.put(0, 2);
        Assertions.assertEquals(2, map.get(0));
        Assertions.assertNull(map.get(2));
        map.put(-1, value);
        Assertions.assertEquals(value, map.get(-1));
        Assertions.assertNull(map.get(value));
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        Assertions.assertTrue(map.isEmpty());
        map.put(0, 2);
        Assertions.assertFalse(map.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void put() {
        final int value = -2;
        Assertions.assertNull(map.get(0));
        Assertions.assertNull(map.put(0, 2));
        Assertions.assertEquals(2, map.get(0));
        Assertions.assertNull(map.get(2));
        Assertions.assertEquals(2, map.put(0, 1));
        Assertions.assertEquals(1, map.get(0));
        map.put(-1, value);
        Assertions.assertEquals(value, map.get(-1));
        Assertions.assertNull(map.get(value));
    }

    @org.junit.jupiter.api.Test
    void remove() {
        Assertions.assertNull(map.remove(0), "Should return null if item to remove is not present");
        map.put(0, 0);
        map.put(1, 1);
        Assertions.assertNull(map.remove(2), "Should return null if item to remove is not present");
        Assertions.assertEquals(0, map.remove(0));
        Assertions.assertEquals(1, map.size());
        Assertions.assertNull(map.remove(0));
        Assertions.assertEquals(1, map.remove(1));
        Assertions.assertEquals(0, map.size());
    }
}
