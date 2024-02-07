package dev.dalol.commands.OneWord;

import dev.dalol.listener.MessageEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Instant;

public class RLMCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("rlm")) {
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                MessageEvent.words.remove(MessageEvent.lastMessage);

                EmbedBuilder builder = new EmbedBuilder();

                builder.setTitle("Folgende Nachricht wurde erfolgreich aus dem System entfernt:");
                builder.setDescription("\"" + MessageEvent.lastMessage + "\"");
                builder.setFooter("GG-Community");
                builder.setColor(0xe35d4b);
                builder.setTimestamp(Instant.now());

                event.replyEmbeds(builder.build()).setEphemeral(true).queue();
                System.out.println("Entfernt: " + MessageEvent.lastMessage);
                MessageEvent.lastMessage = "";
            } else {
                event.reply("Du hast keine Berechtigung dafür!").setEphemeral(true).queue();
            }
        }
    }
}