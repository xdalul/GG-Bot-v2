package dev.dalol.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

public class ReportCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("report")) {
            TextInput subject = TextInput.create("subject", "der Fehler", TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Was ist der Fehler?")
                    .setMinLength(10)
                    .setMaxLength(100)
                    .build();

            TextInput body = TextInput.create("recreate", "Wie kommt der Fehler vor?", TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Hier erklären, wie der Fehler vorkommt")
                    .setMinLength(30)
                    .setMaxLength(1000)
                    .build();

            Modal modal = Modal.create("report", "Report")
                    .addComponents(ActionRow.of(subject), ActionRow.of(body))
                    .build();

            event.replyModal(modal).queue();
        }
    }
}
