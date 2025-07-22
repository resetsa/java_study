package com.resetsa.homeworks;

import org.junit.Test;

public class SortTest {
    @Test
    public void testSort() {
        String input = "cdbea kjhdn";
        String expected = "abcde dhjkn";
        String result = Sort.sort(input);
        assert result.equals(expected) : "Expected: " + expected + ", but got: " + result;

        input = "hello";
        expected = "ehllo";
        result = Sort.sort(input);
        assert result.equals(expected) : "Expected: " + expected + ", but got: " + result;

        input = "54321";
        expected = "12345";
        result = Sort.sort(input);
        assert result.equals(expected) : "Expected: " + expected + ", but got: " + result;
    }
}
