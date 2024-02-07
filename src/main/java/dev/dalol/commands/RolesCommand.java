package dev.dalol.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RolesCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("addrole")) {
            Member member = event.getOption("nutzer").getAsMember();
            Role role = event.getOption("rolle").getAsRole();
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                event.getGuild().addRoleToMember(member, role).queue();
                event.reply("Du hast erfolgreich " + member.getAsMention() + " die Rolle " + role.getAsMention() + " hinzugefügt.").setEphemeral(true).queue();
            }
        }
        if (event.getName().equals("removerole")) {
            Member member = event.getOption("nutzer").getAsMember();
            Role role = event.getOption("rolle").getAsRole();
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                event.getGuild().removeRoleFromMember(member, role).queue();
                event.reply("Du hast erfolgreich " + member.getAsMention() + " die Rolle " + role.getAsMention() + " entfernt.").setEphemeral(true).queue();
            }
        }
    }
}