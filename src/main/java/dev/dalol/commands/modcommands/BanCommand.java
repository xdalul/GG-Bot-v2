package dev.dalol.commands.modcommands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class BanCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("ban")) {
            Member member = event.getOption("nutzer").getAsMember();
            int messagedeletingtimeframe = event.getOption("message-deletion-timeframe").getAsInt();
            String reason = event.getOption("grund").getAsString();
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                try {
                    member.ban(messagedeletingtimeframe, TimeUnit.DAYS).reason(reason).queue();
                    event.reply("Du hast erfolgreich " + member.getAsMention() + " gebannt wegen " + reason + ".").setEphemeral(true).queue();
                } catch (Exception e) {
                    event.reply("Es ist ein Fehler aufgetreten: " + e.getStackTrace()).setEphemeral(true).queue();
                }
            }
        }
    }
}
