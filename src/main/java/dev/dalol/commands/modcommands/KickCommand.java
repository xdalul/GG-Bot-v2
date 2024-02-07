package dev.dalol.commands.modcommands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class KickCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("kick")) {
            Member member = event.getOption("nutzer").getAsMember();
            String grund = event.getOption("grund").getAsString();
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                try {
                    member.kick().reason(grund).queue();
                    event.reply("Du hast erfolgreich **" + member.getAsMention() + "** wegen **" + grund + "** gekickt.").setEphemeral(true).queue();
                } catch (Exception e) {
                    event.reply("Es ist ein Fehler aufgetreten!").setEphemeral(true).queue();
                    e.printStackTrace();
                }
            }
        }
    }
}
