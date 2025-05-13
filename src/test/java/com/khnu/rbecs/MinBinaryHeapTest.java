package com.khnu.rbecs;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MinBinaryHeapTest {

    @Test
    void testEmptyHeap() {
        MyPriorityQueue heap = new MinBinaryHeap();

        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());
        assertThrows(NoSuchElementException.class, heap::peekMin);
        assertThrows(NoSuchElementException.class, heap::pollMin);
    }

    @Test
    void testAddAndPeekMin() {
        MyPriorityQueue heap = new MinBinaryHeap();

        heap.add(5);
        assertEquals(1, heap.size());
        assertEquals(5, heap.peekMin());

        heap.add(3);
        assertEquals(2, heap.size());
        assertEquals(3, heap.peekMin());

        heap.add(7);
        assertEquals(3, heap.size());
        assertEquals(3, heap.peekMin());

        heap.add(1);
        assertEquals(4, heap.size());
        assertEquals(1, heap.peekMin());
    }

    @Test
    void testPollMin() {
        MyPriorityQueue heap = new MinBinaryHeap();

        heap.add(5);
        heap.add(3);
        heap.add(7);
        heap.add(1);

        assertEquals(1, heap.pollMin());
        assertEquals(3, heap.size());
        assertEquals(3, heap.peekMin());

        assertEquals(3, heap.pollMin());
        assertEquals(2, heap.size());
        assertEquals(5, heap.peekMin());

        assertEquals(5, heap.pollMin());
        assertEquals(1, heap.size());
        assertEquals(7, heap.peekMin());

        assertEquals(7, heap.pollMin());
        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testSingleElementHeap() {
        MyPriorityQueue heap = new MinBinaryHeap();

        heap.add(42);
        assertEquals(1, heap.size());
        assertEquals(42, heap.peekMin());
        assertEquals(42, heap.pollMin());
        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testDuplicateElements() {
        MyPriorityQueue heap = new MinBinaryHeap();

        heap.add(5);
        heap.add(5);
        heap.add(5);

        assertEquals(3, heap.size());
        assertEquals(5, heap.pollMin());
        assertEquals(5, heap.pollMin());
        assertEquals(5, heap.pollMin());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testResizing() {
        MyPriorityQueue heap = new MinBinaryHeap();

        // Add more elements than the initial capacity (8)
        for (int i = 0; i < 20; i++) {
            heap.add(20 - i);
        }

        assertEquals(20, heap.size());
        assertEquals(1, heap.peekMin());

        // Verify elements come out in sorted order
        for (int i = 1; i <= 20; i++) {
            assertEquals(i, heap.pollMin());
        }

        assertTrue(heap.isEmpty());
    }

    @Test
    void testAddAfterRemoval() {
        MyPriorityQueue heap = new MinBinaryHeap();

        heap.add(5);
        heap.add(3);
        heap.add(7);

        assertEquals(3, heap.pollMin());

        heap.add(1);
        heap.add(4);

        assertEquals(1, heap.pollMin());
        assertEquals(4, heap.pollMin());
        assertEquals(5, heap.pollMin());
        assertEquals(7, heap.pollMin());

        assertTrue(heap.isEmpty());
    }

    @Test
    void testHeapify() {
        int[] arr = { 5, 4, 3, 2, 1, 2, 3, 4, 5 };
        String expected = "1 2 2 4 4 3 3 5 5 ";
        MinBinaryHeap heap = new MinBinaryHeap(arr);
        assertEquals(9, heap.size());
        assertEquals(expected, heap.dataAsString());
    }

    @Test
    void sort() {
        int[] arr = { 5, 4, 3, 2, 1, 2, 3, 4, 5 };
        int[] expected = { 1, 2, 2, 3, 3, 4, 4, 5, 5 };

        MinBinaryHeap.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @RepeatedTest(10)
    void sort2() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        int[] expected = arr.clone();
        Sort.shuffle(arr);

        MinBinaryHeap.sort(arr);

        assertArrayEquals(expected, arr);
    }
}
