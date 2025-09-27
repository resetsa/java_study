package com.example.dungeon.core;

import com.example.dungeon.model.GameState;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonLoader {
    public static void load(GameState ctx, Path load) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (BufferedReader r = Files.newBufferedReader(load)) 
        {
            objectMapper.readerForUpdating(ctx).readValue(r, ctx.getClass());
        }
        catch (Exception e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}
