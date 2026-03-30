package org.example.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.example.commands.Command;
import org.example.commands.CommandRegistry;

import java.util.Arrays;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;

        String message = event.getMessage().getContentRaw();

        if (!message.startsWith("!")) return;

        String[] parts = message.substring(1).split(" ");
        String commandName = parts[0].toLowerCase();
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);

        Command command = CommandRegistry.get(commandName);

        if (command != null) {
            command.execute(event, args);
        } else {
            event.getChannel().sendMessage("❌ Unknown command. Use !help").queue();
        }
    }
}