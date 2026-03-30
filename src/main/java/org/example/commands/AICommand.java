package org.example.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.example.ai.AIManager;

public class AICommand implements Command {

    @Override
    public String getName() {
        return "ai";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {

        if (args.length == 0) {
            event.getChannel().sendMessage(
                    "Current AI: " + AIManager.getModel()
            ).queue();
            return;
        }

        String model = args[0].toLowerCase();

        if (!model.equals("gemini") && !model.equals("grok")) {
            event.getChannel().sendMessage(
                    "Invalid AI. Use: !ai gemini OR !ai grok"
            ).queue();
            return;
        }

        AIManager.setModel(model);

        event.getChannel().sendMessage(
                "Switched AI to: " + model
        ).queue();
    }
}