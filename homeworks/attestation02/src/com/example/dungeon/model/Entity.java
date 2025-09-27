package com.example.dungeon.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")

public abstract class Entity {
    private String name;
    private int hp;
    private List<Item> inventory = new ArrayList<>();

    public Entity(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public Entity(String name, int hp, List<Item> items) {
        this.name = name;
        this.hp = hp;
        this.inventory = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        return "Entity [name=" + name + ", hp=" + hp + ", inventory=" + inventory + "]";
    }

}
