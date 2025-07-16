package com.resetsa.homeworks;

public class Main {
    public static void main(String[] args) {
        var game = new GameRSP();
        var player1 = new Person("Игрок 1", game.getValue());
        var player2 = new Person("Игрок 2", game.getValue());
        System.out.printf("%s: %s\n", player1.getName(), player1.getChoice());
        System.out.printf("%s: %s\n", player2.getName(), player2.getChoice());

        if (game.isEqual(player1.getChoice(), player2.getChoice())) {
            System.out.println("Ничья!");
            return;
        }
        
        if (game.isFirstWin(player1.getChoice(), player2.getChoice())) {
            System.out.printf("%s выиграл!\n", player1.getName());
        }
        else {
            System.out.printf("%s выиграл!\n", player2.getName());
        }
    }
}