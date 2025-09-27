package com.example.dungeon.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Potion extends Item {
    private final int heal;

    @JsonCreator
    public Potion(@JsonProperty("name") String name, @JsonProperty("heal") int heal) {
        super(name);
        this.heal = heal;
    }

    public int getHeal() {
        return heal;
    }

    @Override
    public void apply(GameState ctx) {
        Player p = ctx.getPlayer();
        p.setHp(p.getHp() + heal);
        System.out.println("Выпито зелье: +" + heal + " HP. Текущее HP: " + p.getHp());
        p.getInventory().remove(this);
    }
}
