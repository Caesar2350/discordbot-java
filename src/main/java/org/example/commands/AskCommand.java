package org.example.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import org.example.ai.AIManager;

public class AskCommand implements Command {

    @Override
    public String getName() {
        return "ask";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {

        if (args.length == 0) {
            event.getChannel().sendMessage("⚠️ Ask something after !ask").queue();
            return;
        }

        String prompt = String.join(" ", args);

        try {
            String response = AIManager.generateResponse(prompt);

            int chunkSize = 4000;

            for (int i = 0; i < response.length(); i += chunkSize) {
                String part = response.substring(i, Math.min(response.length(), i + chunkSize));

                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("🤖 AI Assistant (" + AIManager.getModel() + ")");
                embed.setDescription("✨ " + part);
                embed.setColor(java.awt.Color.CYAN);

                event.getChannel().sendMessageEmbeds(embed.build()).queue();
            }

        } catch (Exception e) {
            event.getChannel().sendMessage("❌ Error: " + e.getMessage()).queue();
        }
    }
}