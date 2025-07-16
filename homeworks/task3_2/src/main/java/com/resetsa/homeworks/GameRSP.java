package com.resetsa.homeworks;

public class GameRSP {

    public ChoicesRSP getValue() {
        ChoicesRSP[] choices = ChoicesRSP.values();
        int randomIndex = (int) (Math.random() * choices.length);
        return choices[randomIndex];
    }

    public boolean isEqual(ChoicesRSP choice1, ChoicesRSP choice2) {
        return choice1 == choice2;
    }

    public boolean isFirstWin(ChoicesRSP choice1, ChoicesRSP choice2) {
        if (choice1 == ChoicesRSP.ROCK && choice2 == ChoicesRSP.SCISSORS) {
            return true;
        } else if (choice1 == ChoicesRSP.PAPER && choice2 == ChoicesRSP.ROCK) {
            return true;
        } else if (choice1 == ChoicesRSP.SCISSORS && choice2 == ChoicesRSP.PAPER) {
            return true;
        }
        return false;
    }

}
