package org.example;

import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.JDABuilder;

import org.example.commands.*;
import org.example.events.MessageListener;

public class Bot {

    public void start() throws Exception {

        String token = System.getenv("BOT_TOKEN");

        JDABuilder.createDefault(token,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT
                )
                .addEventListeners(new MessageListener())
                .build();

        // ✅ Register commands HERE
        CommandRegistry.register(new PingCommand());
        CommandRegistry.register(new HelpCommand());
        CommandRegistry.register(new AskCommand());
        CommandRegistry.register(new AICommand());
    }
}