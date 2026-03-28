package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class App extends ListenerAdapter {

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            try {
                int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8000"));
                System.out.println("Server started on port " + port);
HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
                server.createContext("/", new HttpHandler() {
                    @Override
                    public void handle(HttpExchange exchange) throws IOException {
                        String response = "Discord Bot is Alive! 🟢";
                        exchange.sendResponseHeaders(200, response.getBytes().length);
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }
                });
                server.setExecutor(Executors.newSingleThreadExecutor());
                server.start();
                System.out.println("Server started on port " + port);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        String token = System.getenv("BOT_TOKEN");
        System.out.println("TOKEN = " + token);
        if (token == null || token.isBlank()) {
            System.out.println("❌ BOT_TOKEN missing!");
            return;
        }

        JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new App())
                .build()
                .awaitReady();

        System.out.println("✅ BOT ONLINE! Type '!ping' in Discord");
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
