package org.example.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.example.commands.Command;
import org.example.commands.PingCommand;
import org.example.commands.HelpCommand;

import java.util.HashMap;
import java.util.Map;

public class MessageListener extends ListenerAdapter {

    // Stores all commands (like ping, help)
    private final Map<String, Command> commands = new HashMap<>();

    // Constructor → runs when bot starts
    public MessageListener() {
        registerCommand(new PingCommand());
        registerCommand(new HelpCommand());
    }

    // Adds command to map
    private void registerCommand(Command command) {
        commands.put(command.getName(), command);
    }

    // This runs EVERY time a message is sent
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        // Ignore bots
        if (event.getAuthor().isBot()) return;

        // Get message text
        String message = event.getMessage().getContentRaw();

        // Only allow commands starting with "!"
        if (!message.startsWith("!")) return;

        // Split message → !ping hello → ["ping", "hello"]
        String[] parts = message.substring(1).split(" ");
        String commandName = parts[0].toLowerCase();

        // Find command
        Command command = commands.get(commandName);

        // Execute if exists
        if (command != null) {
            command.execute(event, parts);
        }
    }
}