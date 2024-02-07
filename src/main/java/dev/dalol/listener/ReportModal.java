package dev.dalol.listener;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReportModal extends ListenerAdapter {
    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if (event.getModalId().equals("report")) {
            String fehler = event.getValue("subject").getAsString();
            String recreate = event.getValue("recreate").getAsString();

            event.reply("Danke für deine Fehlermeldung, wir werden den Bug so früh wie möglich beheben.").setEphemeral(true).queue();
            event.getGuild().getTextChannelById("1153680683630338151").sendMessage("# Bug Report von " + event.getMember().getAsMention() + "\n\nFehler:\n* **" + fehler + "** \nWo und Wie der Fehler kommt:\n* **" + recreate + "** \n\n<@&989895363123114025>").queue();
        }
    }
}
