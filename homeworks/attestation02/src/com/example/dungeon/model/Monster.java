package com.example.dungeon.model;

import java.util.List;

public class Monster extends Entity {
    private int level;

    public Monster(String name, int level, int hp) {
        super(name, hp);
        this.level = level;
    }

    public Monster(String name, int level, int hp, List<Item> items) {
        super(name, hp, items);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
