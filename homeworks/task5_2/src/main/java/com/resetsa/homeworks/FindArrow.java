package com.resetsa.homeworks;

import org.apache.commons.lang3.StringUtils;

public class FindArrow {
    private final String arrowRigth = ">>-->";
    private final String arrowLeft = "<<--<";

    public int findCountArrow(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }
       int countRigth=StringUtils.countMatches(input, arrowRigth);
       int countLeft=StringUtils.countMatches(input, arrowLeft);

       return countLeft+countRigth;
    }
}
