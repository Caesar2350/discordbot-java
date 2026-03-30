package org.example.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {

    private static final Map<String, Command> commands = new HashMap<>();

    public static void register(Command command) {
        commands.put(command.getName(), command);
    }

    public static Command get(String name) {
        return commands.get(name);
    }

    public static Map<String, Command> getAll() {
        return commands;
    }
}