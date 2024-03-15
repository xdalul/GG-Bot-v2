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
import dev.dalol.commands.modcommands.WhoIsCommand;
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

            JDA jda = JDABuilder.createDefault("MTA3MTU2ODE2MDIyODEzNDkyMg.GJ9rTc.Y6CTTWPLr4SvYdW6DxRm951UmxPyx5sAqdfIX8")
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
                        new OneWordCommand(),
                        new WhoIsCommand()
                    )
                    .build();

            jda.upsertCommand("whois", "Schaue dir einen Nutzer an")
                    .addOption(OptionType.USER, "nutzer", "der nutzer halt ;p")
                    .queue();
        }
}