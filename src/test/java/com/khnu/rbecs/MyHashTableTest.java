package com.khnu.rbecs;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MyHashTableTest {

    @Test
    void test1() {
        var map = new MyHashTable();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");

        assertEquals("1", map.get("a"));
        assertEquals("2", map.get("b"));
        assertEquals("3", map.get("c"));
        assertEquals(3, map.size());
        assertTrue(map.containsKey("a"));
        assertTrue(map.containsKey("b"));
        assertTrue(map.containsKey("c"));
        assertFalse(map.containsKey("d"));
    }

    @Test
    void iteratorTest() {
        var map = new MyHashTable<String, String>();
        int expectedSum = 0;
        for (int i = 0; i < 30; i++) {
            map.put(String.valueOf(i), String.valueOf(i * i));
            expectedSum += i * i;
        }
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        for (var entry : map) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(" ");
            sum += Integer.parseInt(entry.getValue());
        }
        System.out.println(sb);
        assertEquals(expectedSum, sum);
        assertEquals(30, map.size());
    }

    @Test
    void name1() {
        int n = 10_000_000;
        long t1 = System.nanoTime();
        var map = new MyHashTable<String, String>();
        for (int i = 0; i < n; i++) {
            map.put(String.valueOf(i), String.valueOf(i * i));
        }
        long t2 = System.nanoTime();
        System.out.println("Put Time: " + (t2 - t1) * 1e-9);
        assertEquals(n, map.size());
        for (int i = 0; i < n; i++) {
            assertEquals(String.valueOf(i * i), map.get(String.valueOf(i)));
        }
        long t3 = System.nanoTime();
        System.out.println("Get Time: " + (t3 - t2) * 1e-9);
        for (int i = 0; i < n; i++) {
            assertEquals(String.valueOf(i * i), map.remove(String.valueOf(i)));
        }
        long t4 = System.nanoTime();
        System.out.println("Remove Time: " + (t4 - t3) * 1e-9);
        assertEquals(0, map.size());

        System.out.println("==========");

        long t5 = System.nanoTime();
        var map2 = new HashMap<String, String>();
        for (int i = 0; i < n; i++) {
            map2.put(String.valueOf(i), String.valueOf(i * i));
        }
        long t6 = System.nanoTime();
        System.out.println("Put Time: " + (t6 - t5) * 1e-9);
        assertEquals(n, map2.size());
        for (int i = 0; i < n; i++) {
            assertEquals(String.valueOf(i * i), map2.get(String.valueOf(i)));
        }
        long t7 = System.nanoTime();
        System.out.println("Get Time: " + (t7 - t6) * 1e-9);
        for (int i = 0; i < n; i++) {
            assertEquals(String.valueOf(i * i), map2.remove(String.valueOf(i)));
        }
        long t8 = System.nanoTime();
        System.out.println("Remove Time: " + (t8 - t7) * 1e-9);
        assertEquals(0, map2.size());
    }
}