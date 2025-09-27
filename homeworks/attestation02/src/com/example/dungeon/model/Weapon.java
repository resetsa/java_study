package com.example.dungeon.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Weapon extends Item {
    private final int bonus;

    @JsonCreator
    public Weapon(@JsonProperty("name") String name, @JsonProperty("bonus") int bonus) {
        super(name);
        this.bonus = bonus;
    }

    @Override
    public void apply(GameState ctx) {
        var p = ctx.getPlayer();
        p.setAttack(p.getAttack() + bonus);
        System.out.println("Оружие экипировано. Атака теперь: " + p.getAttack());
        p.getInventory().remove(this);
    }

    public int getBonus() {
        return bonus;
    }
}
