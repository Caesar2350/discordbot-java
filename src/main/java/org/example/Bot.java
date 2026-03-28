package org.example;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EnumSet;

public class Bot {

    public void start() {
        try {
            String token = System.getenv("BOT_TOKEN");

            if (token == null || token.isBlank()) {
                System.out.println("❌ BOT_TOKEN missing!");
                return;
            }

            JDABuilder.createDefault(
                            token,
                            EnumSet.of(
                                    GatewayIntent.GUILD_MESSAGES,
                                    GatewayIntent.MESSAGE_CONTENT
                            )
                    )
                    .addEventListeners(new BotListener()) // 👈 listener connected
                    .build();

            System.out.println("✅ Bot is starting...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}