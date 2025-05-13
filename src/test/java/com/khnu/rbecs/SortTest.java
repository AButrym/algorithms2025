package com.khnu.rbecs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static com.khnu.rbecs.Sort.*;
import static org.assertj.core.api.Assertions.assertThat;

class SortTest {

    private int[] createRange(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    @Test
    void minIxTest1() {
        int[] arr = createRange(10);

        int resIx = minIx(arr, 2, arr.length);

        assertThat(resIx).isEqualTo(2);
    }

    @Test
    void minIxTest2() {
        int[] arr = {5, 4, 3, 2, 1};
        int expectedIx = 4;

        int resIx = minIx(arr, 2, arr.length);

        assertThat(resIx).isEqualTo(expectedIx);
    }

    @RepeatedTest(100)
    void selectionSortTest() {
        int[] arr = createRange(10);
        int[] expected = arr.clone();
        int seed = new Random().nextInt();
        Sort.shuffle(arr, new Random(seed));

        selectionSort(arr);

        assertThat(arr)
                .withFailMessage("Test failed with seed = %d", seed)
                .isEqualTo(expected);
    }

    @RepeatedTest(100)
    void bubbleSortTest() {
        int[] arr = createRange(10);
        int[] expected = arr.clone();
        int seed = new Random().nextInt();
        Sort.shuffle(arr, new Random(seed));

        bubbleSort(arr);

        assertThat(arr)
                .withFailMessage("Test failed with seed = %d", seed)
                .isEqualTo(expected);
    }

    @RepeatedTest(100)
    void insertionSortTest() {
        int[] arr = createRange(10);
        int[] expected = arr.clone();
        int seed = new Random().nextInt();
        Sort.shuffle(arr, new Random(seed));

        insertionSort(arr);

        assertThat(arr)
                .withFailMessage("Test failed with seed = %d", seed)
                .isEqualTo(expected);
    }

    @RepeatedTest(100)
    void quickSortTest() {
        int[] arr = createRange(1_000);
        int[] expected = arr.clone();
        int seed = new Random().nextInt();
        Sort.shuffle(arr, new Random(seed));

        quickSort(arr);

        assertThat(arr)
                .withFailMessage("Test failed with seed = %d", seed)
                .isEqualTo(expected);
    }

    @Test
    void reverseTest1() {
        int[] arr = {1, 2, 3};
        int[] expected = {3, 2, 1};

        reverse(arr);

        assertThat(arr)
                .isEqualTo(expected);
    }

    @Test
    void reverseTest2() {
        int[] arr = {1, 2, 3, 4};
        int[] expected = {4, 3, 2, 1};

        reverse(arr);

        assertThat(arr)
                .isEqualTo(expected);
    }

    @DisplayName("bubble sort of a reversed array should return n*(n-1)/2")
    @Test
    void bubbleSortReverseTest() {
        for (int n = 1; n < 10; n++) {
            int[] arr = reverse(createRange(n));
            int[] expected = createRange(n);

            int inversions = bubbleSort(arr);

            assertThat(inversions)
                    .withFailMessage(
                            "Number of inversions wrong for array of size %d", n)
                    .isEqualTo(n * (n - 1) / 2);

            assertThat(arr)
                    .withFailMessage("Failed to sort a reversed array of size = %d", n)
                    .isEqualTo(expected);
        }
    }

    @DisplayName("bubble sort of {2, 4, 3, 1, 5} should return 4")
    @Test
    void bubbleSortReverseTest2() {
        int[] arr = {2, 4, 3, 1, 5};
        int[] expected = {1, 2, 3, 4, 5};
        int expectedInversions = 4;

        int inversions = bubbleSort(arr);

        assertThat(inversions)
                .isEqualTo(expectedInversions);

        assertThat(arr)
                .isEqualTo(expected);
    }

    @DisplayName("bubble sort of a reversed array should return n*(n-1)/2")
    @Test
    void insertionSortReverseTest() {
        for (int n = 1; n < 10; n++) {
            int[] arr = reverse(createRange(n));
            int[] expected = createRange(n);

            int inversions = insertionSort(arr);

            assertThat(inversions)
                    .withFailMessage(
                            "Number of inversions wrong for array of size %d", n)
                    .isEqualTo(n * (n - 1) / 2);

            assertThat(arr)
                    .withFailMessage("Failed to sort a reversed array of size = %d", n)
                    .isEqualTo(expected);
        }
    }

    @DisplayName("bubble sort of {2, 4, 3, 1, 5} should return 4")
    @Test
    void insertionSortReverseTest2() {
        int[] arr = {2, 4, 3, 1, 5};
        int[] expected = {1, 2, 3, 4, 5};
        int expectedInversions = 4;

        int inversions = insertionSort(arr);

        assertThat(inversions)
                .isEqualTo(expectedInversions);

        assertThat(arr)
                .isEqualTo(expected);
    }

    @DisplayName("merge sort of {2, 4, 3, 1, 5} should return 4")
    @Test
    void mergeSortReverseTest2() {
        int[] arr = {2, 4, 3, 1, 5};
        int[] expected = {1, 2, 3, 4, 5};
        int expectedInversions = 4;

        int inversions = mergeSort(arr);

        assertThat(inversions)
                .isEqualTo(expectedInversions);

        assertThat(arr)
                .isEqualTo(expected);
    }

    @DisplayName("quick sort of {2, 4, 3, 1, 5}")
    @Test
    void quickSortReverseTest2() {
        int[] arr = {2, 4, 3, 1, 5};
        int[] expected = {1, 2, 3, 4, 5};

        quickSort(arr);

        assertThat(arr)
                .isEqualTo(expected);
    }

    @Test
    void shuffleTest() {
        // given
        var rnd = new Random(42);
        int[] arr = createRange(5);
        int[] expected = arr.clone();
        // when
        Sort.shuffle(arr, rnd);
        // then
        assertThat(arr)
                .containsExactlyInAnyOrder(expected);
    }

    @Test
    void swapTest() {
        // given
        int[] arr = {1, 2};
        int[] expected = {2, 1};
        // when
        swap(arr, 0, 1);
        // then
        assertThat(arr)
                .isEqualTo(expected);
    }

    @RepeatedTest(100)
    void mergeSortTest() {
        int[] arr = createRange(100);
        int[] expected = arr.clone();
        int seed = new Random().nextInt();
        Sort.shuffle(arr, new Random(seed));
        int[] arr1 = arr.clone();
        int bubbleInversions = bubbleSort(arr1);

        int inversions = mergeSort(arr);

        assertThat(arr)
                .withFailMessage("Test failed with seed = %d", seed)
                .isEqualTo(expected);
        assertThat(inversions)
                .withFailMessage("Test failed with seed = %d", seed)
                .isEqualTo(bubbleInversions);
    }

    @Test
    void timing() {
        int n = 1_000_000;
        int[] arr1 = createRange(n);
        int seed = 42;//new Random().nextInt();
        reverse(arr1);
//        Sort.shuffle(arr1, new Random(seed));
        int[] arr2 = arr1.clone();

        long t0 = System.nanoTime();
        quickSort(arr1);
        long t1 = System.nanoTime();
        mergeSort(arr2);
        long t2 = System.nanoTime();
        System.out.println("quick sort time = " +
                           (t1 - t0) * 1e-9 + " s");
        System.out.println("merge sort time = " +
                           (t2 - t1) * 1e-9 + " s");
    }

    @Test
    @DisplayName("Checking QuickSort on data with duplicates")
    void timing2() {
        int n = 10_000_000;
        int[] arr1 = new int[n];
        int seed = 42;//new Random().nextInt();
        var rnd = new Random(seed);
        for (int i = 0; i < n; i++) {
            arr1[i] = rnd.nextInt(100);
        }
//        Sort.shuffle(arr1, new Random(seed));
        int[] arr2 = arr1.clone();

        long t0 = System.nanoTime();
        quickSort3way(arr1, 0, arr1.length);
        long t1 = System.nanoTime();
        mergeSort(arr2);
        long t2 = System.nanoTime();
        System.out.println("quick sort time = " +
                           (t1 - t0) * 1e-9 + " s");
        System.out.println("merge sort time = " +
                           (t2 - t1) * 1e-9 + " s");
    }

    @Test
    @DisplayName("Checking QuickSort on data with duplicates against count sort")
    void timing3() {
        int n = 10_000_000;
        int[] arr1 = new int[n];
        int seed = 42;//new Random().nextInt();
        var rnd = new Random(seed);
        for (int i = 0; i < n; i++) {
            arr1[i] = rnd.nextInt(100);
        }
//        Sort.shuffle(arr1, new Random(seed));
        int[] arr2 = arr1.clone();

        long t0 = System.nanoTime();
        quickSort3way(arr1, 0, arr1.length);
        long t1 = System.nanoTime();
        countingSort(arr2, 100);
        long t2 = System.nanoTime();
        System.out.println("quick sort time = " +
                           (t1 - t0) * 1e-9 + " s");
        System.out.println("counting sort time = " +
                           (t2 - t1) * 1e-9 + " s");
    }

    @Test
    void testCountingSort() {
        int[] arr = {1, 1, 0, 0, 2, 2, 0, 1, 2};
        int k = 3;
        int[] expected = {0, 0, 0, 1, 1, 1, 2, 2, 2};

        countingSort(arr, k);

        assertThat(arr)
                .isEqualTo(expected);
    }

    @Test
    void quickSelectKTest() {
        int[] arr = {2, 4, 3, 1, 5, 2, 4, 3, 1, 5, 2, 4, 3, 1, 5};
        int medianIx = arr.length / 2;
        int expectedMedian = 3;

        var actual = quickSelectK(arr, medianIx);

        assertThat(actual)
                .isEqualTo(expectedMedian);
    }

    @RepeatedTest(100)
    void quickSelectKNTest() {
        int n = 1_000;
        int[] arr = new int[n];
        int seed = new Random().nextInt();
        var rnd = new Random(seed);
        for (int i = 0; i < n; i++) {
            arr[i] = rnd.nextInt(5_000);
        }
        int k = rnd.nextInt(0, n);
        var arr1 = arr.clone();
        mergeSort(arr1);
        int expected = arr1[k];

        int actual = quickSelectK(arr, k);

        assertThat(actual)
                .withFailMessage("Test failed with seed = %d", seed)
                .isEqualTo(expected);
    }

    @Test
    void quickSelectK2Test() {
        int n = 1_000;
        int[] arr = new int[n];
        int seed = 257152959;
        var rnd = new Random(seed);
        for (int i = 0; i < n; i++) {
            arr[i] = rnd.nextInt(5_000);
        }
        int k = rnd.nextInt(0, n);
        var arr1 = arr.clone();
        mergeSort(arr1);
        int expected = arr1[k];

        int actual = quickSelectK(arr, k);

        assertThat(actual)
                .withFailMessage("Test failed with seed = %d", seed)
                .isEqualTo(expected);
    }

    @Test
    void timing4() {
        int n = 10_000_000;
        int[] arr1 = createRange(n);
        int seed = 42;//new Random().nextInt();
//        reverse(arr1);
        Sort.shuffle(arr1, new Random(seed));
        int[] arr2 = arr1.clone();
        int[] arr3 = arr1.clone();
        int[] arr4 = arr1.clone();

        long t0 = System.nanoTime();
        quickSort(arr1);
        long t1 = System.nanoTime();
        mergeSort(arr2);
        long t2 = System.nanoTime();
        MinBinaryHeap.sort(arr3);
        long t3 = System.nanoTime();
        Arrays.sort(arr4);
        long t4 = System.nanoTime();
        System.out.println("quick sort time = " +
                           (t1 - t0) * 1e-9 + " s");
        System.out.println("merge sort time = " +
                           (t2 - t1) * 1e-9 + " s");
        System.out.println("heap sort time = " +
                           (t3 - t2) * 1e-9 + " s");
        System.out.println("Arrays.sort time = " +
                           (t4 - t3) * 1e-9 + " s");
    }
}