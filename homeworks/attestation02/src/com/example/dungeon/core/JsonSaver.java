package com.example.dungeon.core;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import com.example.dungeon.model.GameState;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSaver {

    public static void save(GameState ctx, Path save) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (BufferedWriter w = Files.newBufferedWriter(save)) 
        {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(w, ctx);
        }
        catch (Exception e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}
