package org.example;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

class App extends ListenerAdapter {
    public static void main(String[] args) throws Exception {
        String token = System.getenv("BOT_TOKEN");
        if (token == null || token.isBlank()) {
            System.out.println("❌ BOT_TOKEN missing!");
            return;
        }

        JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new App())
                .build()
                .awaitReady();

        System.out.println("✅ BOT ONLINE! Type '!ping'");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        String content = event.getMessage().getContentRaw();

        if (content.equals("!ping")) {
            event.getChannel().sendMessage("**Pong!** Bot works! 🟢").queue();
        }
    }
}
