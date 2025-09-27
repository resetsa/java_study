package com.example.dungeon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Monster extends Entity {
    private int level;

    public Monster(String name, int level, int hp) {
        super(name, hp);
        this.level = level;
    }

    @JsonCreator
    public Monster(@JsonProperty("name") String name,
        @JsonProperty("level") int level,
        @JsonProperty("hp") int hp,
        @JsonProperty("items") List<Item> items) {
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
