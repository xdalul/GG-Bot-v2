package dev.dalol.commands.modcommands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class TimeoutCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("timeout")) {
            Member member = event.getOption("nutzer").getAsMember();
            String grund = event.getOption("grund").getAsString();
            User user = event.getOption("nutzer").getAsUser();
            int dauer = event.getOption("dauer").getAsInt();
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                try {
                    member.timeoutFor(dauer, TimeUnit.HOURS).reason(grund).queue();
                    event.reply("Du hast erfolgreich **" + member.getAsMention() + "** für **" + dauer + " Stunden** wegen **" + grund + "** getimed.").setEphemeral(true).queue();
                    user.openPrivateChannel().queue((PrivateChannel channel) -> {

                        // TODO Timeout DMs

                        channel.sendMessage("Test").queue();
                    });
                } catch (Exception e) {
                    event.reply("Es ist ein Fehler aufgetreten: " + e.getStackTrace()).setEphemeral(true).queue();
                }
            }
        }
    }
}
