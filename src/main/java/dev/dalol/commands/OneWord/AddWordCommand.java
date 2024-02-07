package dev.dalol.commands.OneWord;

import dev.dalol.listener.MessageEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class AddWordCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("addword")) {
            String s = event.getOption("wort").getAsString();
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR))  {
                MessageEvent.words.add(s);
                MessageEvent.lastMessage = s;
                event.reply("Das Wort **" + s + "** wurde zum System hinzugefügt.").setEphemeral(true).queue();
            }
        }
    }
}
