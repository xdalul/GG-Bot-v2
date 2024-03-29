package dev.dalol;

import dev.dalol.commands.NickCommand;
import dev.dalol.commands.OneWord.AddWordCommand;
import dev.dalol.commands.OneWord.RLMCommand;
import dev.dalol.commands.OneWord.UnterbrechenCommand;
import dev.dalol.commands.OneWordCommand;
import dev.dalol.commands.ReportCommand;
import dev.dalol.commands.RolesCommand;
import dev.dalol.commands.modcommands.BanCommand;
import dev.dalol.commands.modcommands.KickCommand;
import dev.dalol.commands.modcommands.TimeoutCommand;
import dev.dalol.listener.MessageEvent;
import dev.dalol.listener.ReportModal;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure()
                .directory("src/main/resources")
                .filename("env")
                .load();

        JDA jda = JDABuilder.createDefault(dotenv.get("token"))
                .setActivity(Activity.playing("GG-Games"))
                .setStatus(OnlineStatus.ONLINE)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(
                    new BanCommand(),
                    new KickCommand(),
                    new TimeoutCommand(),
                    new RolesCommand(),
                    new NickCommand(),
                    new MessageEvent(),
                    new RLMCommand(),
                    new UnterbrechenCommand(),
                    new AddWordCommand(),
                    new ReportCommand(),
                    new ReportModal(),
                    new OneWordCommand()
                )
                .build();

        jda.upsertCommand("ban", "Banne einen Nutzer")
                .addOption(OptionType.USER, "nutzer", "Den Nutzer, den du bannen willst", true)
                .addOption(OptionType.INTEGER, "message-deletion-timeframe", "Von wievielen Tagen sollen die Nachrichten von einem Nutzer entfernt werden.", true)
                .addOption(OptionType.STRING, "grund", "Grund des Banns", true)
                .queue();

        jda.upsertCommand("kick", "Kicke einen Nutzer")
                .addOption(OptionType.USER, "nutzer", "Der, der gekickt werden soll", true)
                .addOption(OptionType.STRING, "grund", "Der Grund des kicks", true)
                .queue();

        jda.upsertCommand("timeout", "Timeoute einen Nutzer")
                .addOption(OptionType.USER, "nutzer", "Der, der getimed werden soll", true)
                .addOption(OptionType.STRING, "grund", "Der Grund des Timeouts", true)
                .addOption(OptionType.INTEGER, "dauer", "Die Dauer des Timeouts in Stunden", true)
                .queue();

        jda.upsertCommand("addrole", "Rolle zu einem Nutzer hinzufügen")
                .addOption(OptionType.USER, "nutzer", "Der Nutzer, der die Rolle kriegen soll.", true)
                .addOption(OptionType.ROLE, "rolle", "Die Rolle, die der Nutzer bekommen soll", true)
                .queue();

        jda.upsertCommand("removerole", "Rolle von einem Nutzer entfernetn")
                .addOption(OptionType.USER, "nutzer", "Der Nutzer, der die Rolle entwented werden soll.", true)
                .addOption(OptionType.ROLE, "rolle", "Die Rolle, die der Nutzer bekommen soll", true)
                .queue();

        jda.upsertCommand("nick", "Bennene einen Nutzer um")
                .addOption(OptionType.USER, "nutzer", "Der Nutzer, der umbenannt werden soll", true)
                .addOption(OptionType.STRING, "nickname", "Der Nickname", true)
                .queue();

        jda.upsertCommand("unterbrechen", "Unterbreche die aktuell laufende Satzreihe")
                .addOption(OptionType.STRING, "grund", "warum machst du diese ;)", true)
                .queue();

        jda.upsertCommand("rlm", "lösche die zuletzt gesendete Nachricht in Oneword (Aus dem System)")
                .queue();

        jda.upsertCommand("addword", "Added ein Wort zu dem aktuellen Satz")
                .addOption(OptionType.STRING, "wort", "das Wort das du adden willst", true)
                .queue();

        jda.upsertCommand("report", "Melde einen Fehler").queue();
        jda.upsertCommand("oneword", "Info zu Oneword").queue();
    }
}