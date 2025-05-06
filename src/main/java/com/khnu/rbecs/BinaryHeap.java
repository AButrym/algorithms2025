package com.khnu.rbecs;

import java.util.NoSuchElementException;

public class BinaryHeap implements MyPriorityQueue {
    private static int INITAL_CAPACITY = 8;
    private int size = 0;

    private int[] data = new int[INITAL_CAPACITY];

    @Override
    public void add(int el) {
        if (size + 1 == data.length) {
            resize();
        }
        data[++size] = el;
        int currentPos = size;
        while (currentPos > 1) {
            int parentPos = parent(currentPos);
            if (data[currentPos] < data[parentPos]) {
                swap(currentPos, parentPos);
                currentPos = parentPos;
            } else {
                break;
            }
        }
    }

    private void resize() {
        int[] newData = new int[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    private int parent(int ix) {
        return ix >> 1; // ix / 2
    }

    private int left(int ix) {
        return ix << 1; // 2 * ix
    }

    private int right(int ix) {
        return (ix << 1) + 1; // 2 * ix + 1
    }


    boolean promote(int ix) {
        var parent = parent(ix);
        if (parent == 0) return false;
        if (data[ix] < data[parent]) {
            swap(ix, parent);
            return true;
        }
        return false;
    }

    private void swap(int node1, int node2) {
        var tmp = data[node1];
        data[node1] = data[node2];
        data[node2] = tmp;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public int peekMin() {
        if (isEmpty()) throw new NoSuchElementException();
        return data[1];
    }

    @Override
    public int pollMin() {
        if (size == 1) {
            return data[size--];
        }
        int res = peekMin();
        swap(1, size);
        size--;
        sink(1);
        return res;
    }

    void sink(int ix) {
        int left = left(ix);
        int right = right(ix);

        if (left > size) {
            return;
        }

        if (right > size) {
            if (data[ix] > data[left]) {
                swap(ix, left);
            }
            return;
        }

        int best = data[left] <= data[right] ? left : right;

        if (data[ix] > data[best]) {
            swap(ix, best);
            sink(best);
        }
    }
}
