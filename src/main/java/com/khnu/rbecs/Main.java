package com.khnu.rbecs;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public static int countOfBF(int[] arr, int key) {
        int count = 0;
        for (var el : arr) {
            if (key == el) count++;
        }
        return count;
    }

    public static int countOf(int[] arr, int key) {
        int ix = indexOf(arr, key);
        if (ix == -1) return 0;
        int count = 1;
        int cur = ix - 1;
        while (cur >= 0 && arr[cur] == key) {
            count++;
            cur--;
        }
        cur = ix + 1;
        while (cur < arr.length && arr[cur] == key) {
            count++;
            cur++;
        }
        return count;
    }

    public static int countOfLR(int[] arr, int key) {
        int left = leftIndexOf(arr, key);
        int right = rightIndexOf(arr, key);
        return right - left;
    }

    public static int indexOf(int[] arr, int key) {
        if (arr == null || arr.length == 0 ||
            key < arr[0] || key > arr[arr.length - 1]) return -1;
        return indexOf(key, arr, 0, arr.length - 1);
    }

    static int indexOf(int key, int[] arr, int left, int right) {
        if (left > right) return -1;
        int mid = left + (right - left) / 2;
        if (key == arr[mid]) return mid;
        if (key < arr[mid]) return indexOf(key, arr, left, mid - 1);
        return indexOf(key, arr, mid + 1, right);
    }

    public static int leftIndexOf(int[] arr, int key) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    public static int rightIndexOf(int[] arr, int key) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (key < arr[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}