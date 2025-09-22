package com.example.dungeon.core;

import com.example.dungeon.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Game {
    private final GameState state = new GameState();
    private final Map<String, Command> commands = new LinkedHashMap<>();

    static {
        WorldInfo.touch("Game");
    }

    public Game() {
        registerCommands();
        bootstrapWorld();
    }

    private void registerCommands() {
        commands.put("help", (ctx, a) -> System.out.println("Команды: " + String.join(", ", commands.keySet())));
        commands.put("gc-stats", (ctx, a) -> {
            Runtime rt = Runtime.getRuntime();
            long free = rt.freeMemory(), total = rt.totalMemory(), used = total - free;
            System.out.println("Память: used=" + used + " free=" + free + " total=" + total);
        });
        commands.put("look", (ctx, a) -> System.out.println(ctx.getCurrent().describe()));
        commands.put("move", (ctx, a) -> {
            String direction = a.getFirst();
            Room nextRoom = ctx.getCurrent().getNeighbors().get(direction);
            if (nextRoom == null) {
                throw new InvalidCommandException(String.format("В направление $s выхода нет", direction));
            }
            ctx.setCurrent(nextRoom);
        });
        commands.put("take", (ctx, a) -> {
            var itemName = a.getFirst().strip();
            var items = ctx.getCurrent().getItems().stream()
                .filter(i -> i.getName().equals(itemName))
                .collect(Collectors.toList());
            if (items.isEmpty()) {
                throw new InvalidCommandException(String.format("В комнате нет предмета %s", itemName));
            }
            ctx.getPlayer().addItems(items);
            ctx.getCurrent().removeItems(items);
        });
        commands.put("inventory", (ctx, a) -> {
            StringBuilder sb = new StringBuilder();
            sb.append("Предметы: ");
            if (!ctx.getPlayer().getInventory().isEmpty()) {
                sb.append(String.join(", ", ctx.getPlayer().getInventory().stream().map(Item::getName).toList()));
            }
            System.out.println(sb.toString());
        });
        commands.put("use", (ctx, a) -> {
            var itemName = a.getFirst().strip();
            var optItem = ctx.getPlayer().getInventory().stream()
                .filter(i -> i.getName().equals(itemName))
                .findFirst();
            if (!optItem.isPresent()) {
                throw new InvalidCommandException(String.format("Предмет %s на найден", itemName));
            }
            optItem.get().apply(ctx);
        });
        commands.put("fight", (ctx, a) -> {
            var monster = ctx.getCurrent().getMonster();
            var player = ctx.getPlayer();
            if (monster == null) {
                throw new InvalidCommandException("В комнате нет монстра");
            }
            monster.setHp(monster.getHp()-player.getAttack());
            player.setHp(player.getHp()-monster.getLevel());
            if (monster.getHp() <= 0) {
                System.out.printf("Монстр %s побежден!%n", monster.getName());
                ctx.getCurrent().setMonster(null);
            }
            else {
                System.out.printf("Вы бьёте %s на %d. HP монстра: %d%n", monster.getName(),player.getAttack(), monster.getHp());
            }
            if (player.getHp() <= 0) {
                System.out.println("Игрок побежден!%n");
                System.out.println("Пока!");
                System.exit(0);
            }
            else {
                System.out.printf("%s отвечает на %d. Ваше HP: %d%n",monster.getName(), monster.getLevel(),player.getHp());
            }
        });
        commands.put("save", (ctx, a) -> SaveLoad.save(ctx));
        commands.put("load", (ctx, a) -> SaveLoad.load(ctx));
        commands.put("scores", (ctx, a) -> SaveLoad.printScores());
        commands.put("exit", (ctx, a) -> {
            System.out.println("Пока!");
            System.exit(0);
        });
    }

    private void bootstrapWorld() {
        Player hero = new Player("Герой", 20, 5);
        state.setPlayer(hero);

        Room square = new Room("Площадь", "Каменная площадь с фонтаном.");
        Room forest = new Room("Лес", "Шелест листвы и птичий щебет.");
        Room cave = new Room("Пещера", "Темно и сыро.");
        square.getNeighbors().put("north", forest);
        forest.getNeighbors().put("south", square);
        forest.getNeighbors().put("east", cave);
        cave.getNeighbors().put("west", forest);

        forest.getItems().add(new Potion("Малое зелье", 5));
        forest.setMonster(new Monster("Волк", 1, 8));

        state.setCurrent(square);
    }

    public void run() {
        System.out.println("DungeonMini (TEMPLATE). 'help' — команды.");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("> ");
                String line = in.readLine();
                if (line == null) break;
                line = line.trim();
                if (line.isEmpty()) continue;
                List<String> parts = Arrays.asList(line.split("\s+",2));
                String cmd = parts.getFirst().toLowerCase(Locale.ROOT);
                List<String> args = parts.subList(1, parts.size());
                Command c = commands.get(cmd);
                try {
                    if (c == null) throw new InvalidCommandException("Неизвестная команда: " + cmd);
                    c.execute(state, args);
                    state.addScore(1);
                } catch (InvalidCommandException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Непредвиденная ошибка: " + e.getClass().getSimpleName() + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода: " + e.getMessage());
        }
    }
}
