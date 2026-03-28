package org.example.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class HelpCommand implements Command {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {

        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("🤖 Bot Commands");
        embed.setColor(Color.CYAN);

        embed.addField("!ping", "Check if bot is alive", false);
        embed.addField("!help", "Show this help menu", false);

        embed.setFooter("Made by you 🚀");

        event.getChannel().sendMessageEmbeds(embed.build()).queue();
    }
}