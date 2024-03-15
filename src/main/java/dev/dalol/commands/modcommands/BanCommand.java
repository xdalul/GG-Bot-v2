package dev.dalol.commands.modcommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class BanCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("ban")) {
            Member member = event.getOption("nutzer").getAsMember();
            User user = event.getOption("nutzer").getAsUser();
            int messagedeletingtimeframe = event.getOption("message-deletion-timeframe").getAsInt();
            String reason = event.getOption("grund").getAsString();
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                try {
                    member.ban(messagedeletingtimeframe, TimeUnit.DAYS).reason(reason).queue();
                    event.reply("Du hast erfolgreich " + member.getAsMention() + " gebannt wegen " + reason + ".").setEphemeral(true).queue();
                    user.openPrivateChannel().queue((PrivateChannel channel) -> {

                        // TODO Ban DMs

                        channel.sendMessage("Test").queue();

                        // TODO Embed zu verwarnungen

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setTitle("Ban getätigt!");
                        builder.setDescription("Es wurde ein permanter Ban getätigt. Unten gibt es paar Optionen");
                        builder.addField("Nutzer", member.getAsMention(), false);
                        builder.addField("Grund", reason, false);
                        builder.addField("Message Deletiona Timeframe", messagedeletingtimeframe + " Tage", false);
                        builder.setFooter("GG-Moderation");
                        builder.setTimestamp(Instant.now());
                        builder.setColor(0xff2b2b);

                        Button button = Button.danger("whoisbutt", "Nutzer anschauen");


                        event.getGuild().getTextChannelById("1059162001097637979").sendMessageEmbeds(builder.build()).queue();
                    });
                } catch (Exception e) {
                    event.reply("Es ist ein Fehler aufgetreten: " + e.getStackTrace()).setEphemeral(true).queue();
                }
            }
        }
    }
}
