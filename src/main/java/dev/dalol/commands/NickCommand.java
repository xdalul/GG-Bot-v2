package dev.dalol.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class NickCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("nick")) {
            Member member = event.getOption("nutzer").getAsMember();
            String s = event.getOption("nickname").getAsString();
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                try {
                    member.modifyNickname(s).queue();
                    event.reply("Du hast erfolgreich " + member.getAsMention() + " zu **" + s + "** umbenannt").setEphemeral(true).queue();
                } catch (Exception e) {
                    e.printStackTrace();
                    event.reply("Es ist ein Fehler aufgetreten!").setEphemeral(true).queue();
                }
            }
        }
    }
}
