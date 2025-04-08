package com.khnu.rbecs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.khnu.rbecs.Main.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    @Test
    @DisplayName("for empty array the count should be zero")
    void for_empty_array_the_count_should_be_zero() {
        // AAA
        // Arrange
        int[] arr = {};
        int key = 8;
        int expectedCount = 0;
        // Act
        int actualCount = countOf(arr, key);
        // Assert
        assertEquals(expectedCount, actualCount, "for empty array the count should be zero");
    }

    @Test
    @DisplayName("indexOf for array with multiple entries should be in the correct range")
    void test2() {
        // AAA
        // Arrange
        int[] arr = {1, 2, 2, 2, 2, 2, 8};
        int key = 2;
        // Act
        int ix = indexOf(arr, key);
        // Assert
        boolean withinExpectedRange = 1 <= ix && ix <= 5;
        assertTrue(withinExpectedRange, "the found index should be within 1..5");
    }

    @Test
    @DisplayName("indexOf for array without the key should return -1")
    void test3() {
        // AAA
        // Arrange
        int[] arr = {1, 2, 2, 2, 2, 2, 8};
        int key = 3;
        int expectedIx = -1;
        // Act
        int ix = indexOf(arr, key);
        // Assert
        assertEquals(expectedIx, ix, "key 3 is not present so -1 should have been returned");
    }

    @Test
    @DisplayName("leftIndexOf should return correct left boundary when key is present")
    void leftIndexOfTest1() {
        // Arrange
        int[] arr = {1, 2, 2, 2, 2, 2, 8};
        int key = 2;
        int expectedIx = 1;
        // Act
        int ix = leftIndexOf(arr, key);
        // Assert
        assertEquals(expectedIx, ix, "range for key 2 starts at position 1");
    }

    @Test
    @DisplayName("leftIndexOf should return correct left boundary when key is not present")
    void leftIndexOfTest2() {
        // Arrange
        int[] arr = {1, 2, 2, 2, 2, 2, 8};
        int key = 3;
        int expectedIx = 6;
        // Act
        int ix = leftIndexOf(arr, key);
        // Assert
        assertEquals(expectedIx, ix, "key 3 should be insertable in position 6");
    }

    @Test
    @DisplayName("leftIndexOf for empty array should return 0")
    void leftIndexOfTest3() {
        // Arrange
        int[] arr = {};
        int key = 3;
        int expectedIx = 0;
        // Act
        int ix = leftIndexOf(arr, key);
        // Assert
        assertEquals(expectedIx, ix, "for empty array should return 0");
    }

    @Test
    @DisplayName("leftIndexOf for large value should return n")
    void leftIndexOfTest4() {
        // Arrange
        int[] arr = {1, 2, 3, 4};
        int key = 100;
        int expectedIx = arr.length;
        // Act
        int ix = leftIndexOf(arr, key);
        // Assert
        assertEquals(expectedIx, ix, "for large value should return array.length");
    }
    @Test
    @DisplayName("leftIndexOf for small value should return 0")
    void leftIndexOfTest5() {
        // Arrange
        int[] arr = {1, 2, 3, 4};
        int key = -100;
        int expectedIx = 0;
        // Act
        int ix = leftIndexOf(arr, key);
        // Assert
        assertEquals(expectedIx, ix, "for small value should return 0");
    }

    @Test
    @DisplayName("rightIndexOf should return correct right boundary when key is present")
    void rightIndexOfTest1() {
        // Arrange
        int[] arr = {1, 2, 2, 2, 2, 2, 8};
        int key = 2;
        int expectedIx = 6;
        // Act
        int ix = rightIndexOf(arr, key);
        // Assert
        assertEquals(expectedIx, ix, "range for key 2 starts at position 1");
    }

    @Test
    @DisplayName("rightIndexOf should return correct right boundary when key is not present")
    void rightIndexOfTest2() {
        // Arrange
        int[] arr = {1, 2, 2, 2, 2, 2, 8};
        int key = 3;
        int expectedIx = 6;
        // Act
        int ix = rightIndexOf(arr, key);
        // Assert
        assertEquals(expectedIx, ix, "key 3 should be insertable in position 6");
    }

    @Test
    @DisplayName("rightIndexOf for empty array should return 0")
    void rightIndexOfTest3() {
        // Arrange
        int[] arr = {};
        int key = 3;
        int expectedIx = 0;
        // Act
        int ix = rightIndexOf(arr, key);
        // Assert
        assertEquals(expectedIx, ix, "for empty array should return 0");
    }

    @Test
    @DisplayName("rightIndexOf for large value should return n")
    void rightIndexOfTest4() {
        // Arrange
        int[] arr = {1, 2, 3, 4};
        int key = 100;
        int expectedIx = arr.length;
        // Act
        int ix = rightIndexOf(arr, key);
        // Assert
        assertEquals(expectedIx, ix, "for large value should return array.length");
    }
    @Test
    @DisplayName("rightIndexOf for small value should return 0")
    void rightIndexOfTest5() {
        // Arrange
        int[] arr = {1, 2, 3, 4};
        int key = -100;
        int expectedIx = 0;
        // Act
        int ix = rightIndexOf(arr, key);
        // Assert
        assertEquals(expectedIx, ix, "for small value should return 0");
    }

    @Test
    @DisplayName("countOfLR should return correct value when the values are present")
    void countOfLR_should_return_correct_value() {
        // Arrange
        int[] arr = {1, 2, 2, 2, 3, 4};
        int key = 2;
        int expectedCount = 3;
        // Act
        int count = countOfLR(arr, key);
        // Assert
        assertEquals(expectedCount, count);
    }

    @Test
    @DisplayName("countOfLR should return 0 when the values are not present")
    void countOfLRTest2() {
        // Arrange
        int[] arr = {1, 3, 4, 5};
        int key = 2;
        int expectedCount = 0;
        // Act
        int count = countOfLR(arr, key);
        // Assert
        assertEquals(expectedCount, count);
    }
}