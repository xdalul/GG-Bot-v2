package dev.dalol.commands.modcommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class WhoIsCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("whois")) {
            Member member = event.getOption("nutzer").getAsMember();
            if (member != null) {
                EmbedBuilder builder = new EmbedBuilder();

                builder.setTitle(member.getEffectiveName() + "'s WhoIs");
                if (member.getNickname() != null) {
                    builder.addField("Nickname", member.getNickname(), true);
                }
                builder.addField("Server betreten", member.getTimeJoined().toString(), true);
                builder.addField("Discord betreten", member.getTimeCreated().toString(), true);
                builder.addField("Rollen", member.getRoles().stream().findAny().get().getAsMention(), true);
                // TODO timeout, banned

                builder.addField("Timeouta", String.valueOf(member.isTimedOut()), true);

                event.replyEmbeds(builder.build()).queue();
            }
        }
    }
}
