package com.khnu.rbecs;

import java.util.Random;

public class Sort {
    // merge sort
    // T(n) = 2 * T(n/2) + O(n)
    // T(n) = a * T(n/b) + O(n^k)   Master Method
    // QuickSort with two pivots (3-way partitioning)
    // counting sort
    // QuickSelect

    private static Random RND = new Random();

    // T(n) = T(n/2) + O(n) => T(n) ! O(n)
    // returns arr[k] as in a sorted arr
    public static int quickSelectK(int[] arr, int k) {
        return quickSelectK(arr, k, 0, arr.length - 1);
    }

    static int quickSelectK(int[] arr, int k, int left, int right) {
        assert k > left && k < right;
        var twoPos = threeWayPartitioning(arr, left, right);
        if (k < twoPos.left()) {
            return quickSelectK(arr, k, left, twoPos.left());
        }
        if (k > twoPos.right()) {
            return quickSelectK(arr, k, twoPos.right(), right);
        }
        return arr[k];
    }

    public static void countingSort(int[] arr, int k) {
        int[] count = new int[k];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        int[] ix = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            ix[i] = ix[i - 1] + count[i - 1];
        }
        for (int el = 0; el < k; el++) {
            for (int i = ix[el]; i < ix[el+1]; i++) {
                arr[i] = el;
            }
        }
    }

    public static void quickSort(int[] arr) {
        // in place
        // non stable
        // T(n) = 2 * T(n/2) + O(n)  => T(n) ~ n * lg(n)
        // T(n) = T(n-1) + O(n) => T(n) ~ n^2
        quickSort3way(arr, 0, arr.length);
    }

    static void quickSort2Way(int[] arr,
                              int leftInclusive, int rightExclusive
    ) {
        if (rightExclusive - leftInclusive <= 1) return;

        int ixPivot = twoWayPartitioning(arr, leftInclusive, rightExclusive);

        quickSort2Way(arr, leftInclusive, ixPivot);
        quickSort2Way(arr, ixPivot + 1, rightExclusive);
    }

    static void quickSort3way(int[] arr,
                              int leftInclusive, int rightExclusive
    ) {
        if (rightExclusive - leftInclusive <= 1) return;

        var twoPositions = threeWayPartitioning(arr, leftInclusive, rightExclusive);

        quickSort3way(arr, leftInclusive, twoPositions.left());
        quickSort3way(arr, twoPositions.right(), rightExclusive);
    }

    static TwoPositions threeWayPartitioning(int[] arr, int leftInclusive, int rightExclusive) {
        // return left such that i < left => arr[i] < pivot
        // return right such that i >= right => arr[i] > pivot
        // left <= i < right => arr[i] == pivot
        int pivotIx = choosePivot(arr, leftInclusive, rightExclusive);
        swap(arr, leftInclusive, pivotIx);
        final int pivot = arr[leftInclusive];

        int l = leftInclusive;
        int m = leftInclusive + 1;
        int r = rightExclusive;
        while (m < r) {
            if (arr[m] < pivot) {
                swap(arr, l++, m++);
            } else if (arr[m] > pivot) {
                swap(arr, m, --r);
            } else {
                m++;
            }
        }
        return new TwoPositions(l, r);
    }

    static int twoWayPartitioning(int[] arr, int leftInclusive, int rightExclusive) {
        int pivotIx = choosePivot(arr, leftInclusive, rightExclusive);
        swap(arr, leftInclusive, pivotIx);
        final int pivot = arr[leftInclusive];

        int l = leftInclusive + 1;
        int r = rightExclusive;
        while (l < r) {
            if (arr[l] < pivot) {
                l++;
            } else {
                swap(arr, l, --r);
            }
        }
        swap(arr, leftInclusive, r - 1);
        return r - 1;
    }

    private static int choosePivot(int[] arr, int leftInclusive, int rightExclusive) {
        return RND.nextInt(leftInclusive, rightExclusive);
    }

    public static int mergeSort(int[] arr) {
        final int minSizeForInsertionSort = 20;
        if (arr.length <= minSizeForInsertionSort)
            return insertionSort(arr);
        int leftSize = arr.length / 2;
        int rightSize = arr.length - leftSize;
        int[] left = new int[leftSize];
        int[] right = new int[rightSize];
        System.arraycopy(arr, 0, left, 0, leftSize);
        System.arraycopy(arr, leftSize, right, 0, rightSize);
        int totalInversions = mergeSort(left);
        totalInversions += mergeSort(right);
        totalInversions += merge(left, right, arr);
        return totalInversions;
    }

    static int merge(int[] left, int[] right, int[] arr) {
        int iLeft = 0, iRight = 0, iRes = 0, inversions = 0;
        while (iLeft < left.length && iRight < right.length) {
            if (left[iLeft] <= right[iRight]) {
                arr[iRes++] = left[iLeft++];
            } else {
                arr[iRes++] = right[iRight++];
                inversions += left.length - iLeft;
            }
        }
        while (iLeft < left.length) {
            arr[iRes++] = left[iLeft++];
        }
        while (iRight < right.length) {
            arr[iRes++] = right[iRight++];
        }
        return inversions;
    }

    // insertion sort
    public static int insertionSort(int[] arr) {
        int n = arr.length;
        int inversions = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                    inversions++;
                } else {
                    break;
                }
            }
        }
        return inversions;
    }

    // bubble sort
    public static int bubbleSort(int[] arr) {
        int n = arr.length;
        int inversions = 0;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    inversions++;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return inversions;
    }

    // selection sort
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int ix = minIx(arr, i, arr.length);
            swap(arr, i, ix);
        }
    }

    static int minIx(
            int[] arr,
            int lowInclusive,
            int highExclusive
    ) {
        int res = lowInclusive;
        for (int i = lowInclusive + 1; i < highExclusive; i++) {
            if (arr[i] < arr[res]) {
                res = i;
            }
        }
        return res;
    }

    public static void shuffle(int[] arr) {
        var rnd = new Random();
        shuffle(arr, rnd);
    }

    public static int[] reverse(int[] arr) {
        for (int left = 0, right = arr.length - 1;
             left < right;
             left++, right--
        ) {
            swap(arr, left, right);
        }
        return arr;
    }

    static void shuffle(int[] arr, Random rnd) {
        for (int i = 0; i < arr.length - 1; i++) {
            int ix = rnd.nextInt(i, arr.length);
            swap(arr, i, ix);
        }
    }

    static void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    record TwoPositions(int left, int right) {
    }
}
