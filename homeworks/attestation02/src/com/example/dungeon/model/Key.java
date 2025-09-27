package com.example.dungeon.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Key extends Item {

    @JsonCreator
    public Key(@JsonProperty("name") String name) {
        super(name);
    }

    @Override
    public void apply(GameState ctx) {
        System.out.println("Ключ звенит. Возможно, где-то есть дверь...");
    }
}
