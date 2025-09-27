package com.example.dungeon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Player extends Entity {
    private int attack;

    @JsonCreator
    public Player(@JsonProperty("name") String name,
        @JsonProperty("hp") int hp,
        @JsonProperty("inventory") List<Item> items,
        @JsonProperty("attack") int attack) {
        super(name, hp, items);
        this.attack = attack;
    }

    public Player(String name, int hp, int attack) {
        super(name, hp);
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

}
