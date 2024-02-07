package dev.dalol.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Instant;

public class OneWordCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("oneword")) {
            EmbedBuilder builder = new EmbedBuilder();

            builder.setTitle("`♻️` Hilfe zu OneWord");
            builder.setDescription("**Spielerklärung**: In OneWord geht es darum das jeder ein Wort schreibt und eine andere Person dann diesen Satz weiter führt. So entstehen lustige Sätze die von mehreren Person gemacht wurden.\n**Regeln**: Als Regeln für OneWord gibt es:\n1. Jeder darf nur 1 Wort in einer Nachricht schreiben.\n2. Man darf keine 2 Nachrichten hintereinander schreiben.\n3. Das Wort muss grammatikalisch stimmen.\n\n**Commands**: An Commands gibt es aktuell die folgenden:\n/report - Reporte einen Fehler.\n/oneword - Dieser Command zeigt dir aktuell dieses Hilfe Menu an.\n\n**Datenschutz-Information**: Ab jetzt, werden eure Nachrichten in der Konsole (LOG) gespeichert, damit wir Fehler oder was auch immer schnell beseitigen können.\n\n*Mehr Features sind in Planung ;3*");
            builder.setFooter("GG-Community");
            builder.setColor(0x00b9e3);
            builder.setTimestamp(Instant.now());

            event.replyEmbeds(builder.build()).setEphemeral(true).queue();

        }
    }
}
