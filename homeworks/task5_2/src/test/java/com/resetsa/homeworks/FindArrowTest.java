package com.resetsa.homeworks;

import org.junit.Test;

public class FindArrowTest {
    @Test
    public void testFindCountArrow() {
        FindArrow findArrow = new FindArrow();
        
        String input = ">>-->>---<<--<<";
        int count = findArrow.findCountArrow(input);
        assert count == 2 : "Expected 2 arrows, but got " + count;

        input = "000000";
        count = findArrow.findCountArrow(input);
        assert count == 0 : "Expected 0 arrows, but got " + count;

        input = ">>-->>>>-->>....>>-->>";
        count = findArrow.findCountArrow(input);
        assert count == 3 : "Expected 3 arrows, but got " + count;

        input = ">>-->>>>-->>....>>-->>00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        try {
            findArrow.findCountArrow(input);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Input exceeds maximum length of 106");
        }

        // Test with an empty string
        try {
            findArrow.findCountArrow("");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Input cannot be null or empty");
        }

        // Test with a null input
        try {
            findArrow.findCountArrow(null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Input cannot be null or empty");
        }
    }
}
