package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.example.events.MessageListener;

public class Bot {

    public void start() {
        try {
            String token = System.getenv("BOT_TOKEN");

            if (token == null || token.isBlank()) {
                System.out.println("❌ BOT_TOKEN is missing!");
                return;
            }

            JDA jda = JDABuilder.createDefault(token)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                    .addEventListeners(new MessageListener())
                    .build();

            jda.awaitReady();

            System.out.println("✅ Bot is ONLINE!");
            System.out.println("🤖 Logged in as: " + jda.getSelfUser().getAsTag());

        } catch (Exception e) {
            System.out.println("❌ Error starting bot:");
            e.printStackTrace();
        }
    }
}