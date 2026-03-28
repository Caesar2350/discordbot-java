package org.example;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.Color;

import org.example.ai.GeminiService;

public class BotListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;

        String message = event.getMessage().getContentRaw();

        // ✅ Ping test
        if (message.equalsIgnoreCase("ping") || message.equalsIgnoreCase("!ping")) {
            event.getChannel().sendMessage("🏓 Pong! Bot is working.").queue();
            return;
        }

        // ✅ AI command
        if (message.startsWith("!ask")) {

            String prompt = message.replace("!ask", "").trim();

            if (prompt.isEmpty()) {
                event.getChannel().sendMessage("⚠️ Please ask something after `!ask`").queue();
                return;
            }

            String response = GeminiService.getResponse(prompt);

// Split into chunks of 4000
            int chunkSize = 4000;

            for (int i = 0; i < response.length(); i += chunkSize) {
                String part = response.substring(i, Math.min(response.length(), i + chunkSize));

                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("🤖 AI Assistant");
                embed.setDescription("✨ " + part);
                embed.setColor(java.awt.Color.CYAN);

                event.getChannel().sendMessageEmbeds(embed.build()).queue();
            }
        }
    }
}