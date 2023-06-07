package me.AhmHkn;

import net.dv8tion.jda.api.*;

import static me.AhmHkn.DEUEsporBot.*;

public class CommandsRegister {

    public static void CommandRegister() {

        bot.upsertCommand("kayıt", "Kullanılan kanalda Rol Kayıt sistemini kurar.").complete();
    }
}
