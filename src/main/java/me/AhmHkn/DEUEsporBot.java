package me.AhmHkn;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.*;

import javax.security.auth.login.LoginException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static me.AhmHkn.CommandsRegister.CommandRegister;


public class DEUEsporBot {

    public static JDA bot;
    public static String token = null;



    public static void main(String[] args) throws LoginException {
        token = args[0];

        ScheduledExecutorService sync = Executors.newScheduledThreadPool(1);

        bot = JDABuilder.createDefault(token)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .setChunkingFilter(ChunkingFilter.ALL)
                .addEventListeners(new RolKayit())
                .build();

        bot.getPresence().setStatus(OnlineStatus.ONLINE);
    //    bot.getPresence().setActivity(Activity.playing(""));


        Runnable commands = new Runnable() {
            public void run() {
                CommandRegister();
            }
        };

        sync.schedule(commands, 3, TimeUnit.SECONDS);




    }

}
