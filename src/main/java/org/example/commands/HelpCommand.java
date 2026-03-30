package org.example.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelpCommand implements Command {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {

        String helpMessage = """
        🤖 Nexus AI Commands:

        !ping → Check bot
        !help → Show commands
        !ask  → Ask AI
        !ai   → Switch AI (gemini/grok)
        """;

        event.getChannel().sendMessage(helpMessage).queue();
    }
}