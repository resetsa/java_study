package com.resetsa.homeworks;

public class Shift {
    private final String KEYBOARD = "qwertyuiopasdfghjklzxcvbnm";
    public char shiftChar(char in) {
        var indexIn = KEYBOARD.indexOf(Character.toString(in).toLowerCase());
        if (indexIn == -1) {
            throw new IllegalArgumentException("Character not found in keyboard layout: " + in);
        }
        String result;
        if (indexIn == 0) {
            result = String.valueOf(KEYBOARD.charAt(KEYBOARD.length() - 1));
        } else {
            result = String.valueOf(KEYBOARD.charAt(indexIn - 1));
        }
        return result.charAt(0);
    }
}
