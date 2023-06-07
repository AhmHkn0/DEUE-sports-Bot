package me.AhmHkn;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import net.dv8tion.jda.api.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class RolKayit extends ListenerAdapter {


    @Override
    public void onSlashCommand(SlashCommandEvent e) {
        if (e.getName().equals("kayıt")) {
            if (!e.getChannelType().isGuild()) return;
            if (e.getMember().hasPermission(Permission.ADMINISTRATOR) || e.getMember().getIdLong() == 319535775022841867L) {
                KayitMesaji(e.getGuild(), e.getTextChannel());
                e.replyEmbeds(Embed("Rol alma sistemi başarıyla kuruldu.", Color.green).build()).setEphemeral(true).queue();
            } else {
                e.replyEmbeds(Embed("Bu komutu yalnızca yetki sahibi kullanıcılar kullanabilir.", Color.red).build()).setEphemeral(true).queue();
            }
        }
    }



    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().equals("!kayıt")) {
            if (e.getMember().hasPermission(Permission.ADMINISTRATOR) || e.getMember().getIdLong() == 319535775022841867L) {
                KayitMesaji(e.getGuild(), e.getTextChannel());
            }
        }
    }

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent e) {
        if (!e.getChannelType().isGuild()) return;
        if (Objects.requireNonNull(e.getUser()).isBot()) return;
        Role rol = RolCek(e.getGuild(), e.getReaction().getReactionEmote().getName());
        Role valorantoyun = null;
        Role loloyun = null;
        if (rol != null) {
            if (e.getReactionEmote().getName().contains("lig_valorant") || e.getReactionEmote().getName().contains("lig_lol")) {
                for (Role oyunrol0 : e.getGuild().getRoles()) {
                    if (oyunrol0.getName().equalsIgnoreCase("Valorant")) {
                        valorantoyun = oyunrol0;
                    }
                    if (oyunrol0.getName().equalsIgnoreCase("League of Legends")) {
                        loloyun = oyunrol0;
                    }
                }
            }
            if (e.getReactionEmote().getName().contains("lig_valorant")) {
                for (Role role : Objects.requireNonNull(e.getMember()).getRoles()) {
                    if (role.getName().contains("Valorant")) {
                        e.getGuild().removeRoleFromMember(e.getMember(), role).queue();
                    }
                }
            }
            if (e.getReactionEmote().getName().contains("lig_lol")) {
                for (Role role : Objects.requireNonNull(e.getMember()).getRoles()) {
                    if (role.getName().contains("LoL")) {
                        e.getGuild().removeRoleFromMember(e.getMember(), role).queue();
                    }
                }
            }
            if (!Objects.requireNonNull(e.getMember()).getRoles().contains(rol)) {
                e.getGuild().addRoleToMember(e.getMember().getIdLong(), rol).queue();
                if (valorantoyun != null && e.getReactionEmote().getName().contains("valorant"))
                    e.getGuild().addRoleToMember(e.getMember().getIdLong(), valorantoyun).queue();
                if (loloyun != null && e.getReactionEmote().getName().contains("lol"))
                    e.getGuild().addRoleToMember(e.getMember().getIdLong(), loloyun).queue();
            }
        }
    }

    @Override
    public void onMessageReactionRemove(@NotNull MessageReactionRemoveEvent e) {
        if (!e.getChannelType().isGuild()) return;
        Role rol = RolCek(e.getGuild(), e.getReaction().getReactionEmote().getName());
        Role valorantoyun = null;
        Role loloyun = null;
        if (rol != null) {
            if (e.getReactionEmote().getName().contains("lig_valorant") || e.getReactionEmote().getName().contains("lig_lol")) {
                for (Role oyunrol0 : e.getGuild().getRoles()) {
                    if (oyunrol0.getName().equalsIgnoreCase("Valorant")) {
                        valorantoyun = oyunrol0;
                    }
                    if (oyunrol0.getName().equalsIgnoreCase("League of Legends")) {
                        loloyun = oyunrol0;
                    }
                }
            }
            if (Objects.requireNonNull(e.getMember()).getRoles().contains(rol)) {
                e.getGuild().removeRoleFromMember(e.getMember().getIdLong(), rol).queue();
                if (valorantoyun != null && e.getReactionEmote().getName().contains("valorant"))
                    e.getGuild().removeRoleFromMember(e.getMember(), valorantoyun).queue();
                if (loloyun != null && e.getReactionEmote().getName().contains("lol"))
                    e.getGuild().removeRoleFromMember(e.getMember(), loloyun).queue();
            }
        }
    }

    public EmbedBuilder Embed(String mesaj, Color color) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(color);
        eb.setDescription(mesaj);
        return eb;
    }

    public Role RolCek(Guild guild, String rol) {
        if (rol.contains("_")) {
            if (!rol.contains("lig_")) {
                String[] RolAdi = rol.split("_");
                for (Role role : guild.getRoles()) {
                    if (RolAdi[1].equalsIgnoreCase(role.getName().replace(" ", ""))) {
                        try {
                            return role;
                        } catch (Exception ex) {
                            return null;
                        }
                    }
                }
            }
            if (rol.contains("lig_")) {
                String[] RolAdi0 = rol.split("_");
                String RolAdi = RolAdi0[1];
                for (Role role : guild.getRoles()) {
                    String[] rolfor0 = role.getName().split(" ");
                    String rolfor = rolfor0[0];
                    if (RolAdi.equalsIgnoreCase(rolfor)) {
                        try {
                            String Lig = RolAdi0[2]
                                    .replace("demir", "Demir")
                                    .replace("gumus", "Gümüş")
                                    .replace("Altin", "Altın")
                                    .replace("plat", "Platin")
                                    .replace("immo", "Ölümsüzlük");
                            String ayrac = rolfor0[1];
                            String finalRol = rolfor+" "+ayrac+" "+Lig;
                            if (role.getName().equalsIgnoreCase(finalRol)) {
                                return role;
                            }
                        } catch (Exception ex) {
                            return null;
                        }
                    }
                }
            }
        }
        return null;
    }

    public void KayitMesaji(Guild guild, TextChannel textChannel) {
        MessageEmbed oyunmesaj = Embed("Aşağıdaki oyun ifadelerine tıklayarak istediğiniz oyun rolüne sahip olabilirsiniz.", Color.cyan).build();
        MessageEmbed ligmesaj = Embed("Aşağıdaki lig ifadelerine tıklayarak Valorant ve League of Legends oyunları için liginizle birlikte seçim yapabilirsiniz.", Color.cyan).build();
        ArrayList<Emote> emoteList = new ArrayList<>();

        for (Emote emote : Objects.requireNonNull(guild).getEmotes()) {
            if (emote.getName().contains("lig_")) {
                emoteList.add(emote);
            }
        }


        for (Emote emote : Objects.requireNonNull(guild).getEmotes()) {
            if (emote.getName().equalsIgnoreCase("lig_valorant_demir"))
                emoteList.set(0, emote);
            if (emote.getName().equalsIgnoreCase("lig_valorant_bronz"))
                emoteList.set(1, emote);
            if (emote.getName().equalsIgnoreCase("lig_valorant_gumus"))
                emoteList.set(2, emote);
            if (emote.getName().equalsIgnoreCase("lig_valorant_altin"))
                emoteList.set(3, emote);
            if (emote.getName().equalsIgnoreCase("lig_valorant_plat"))
                emoteList.set(4, emote);
            if (emote.getName().equalsIgnoreCase("lig_valorant_elmas"))
                emoteList.set(5, emote);
            if (emote.getName().equalsIgnoreCase("lig_valorant_immo"))
                emoteList.set(6, emote);
            if (emote.getName().equalsIgnoreCase("lig_valorant_radyant"))
                emoteList.set(7, emote);
            if (emote.getName().equalsIgnoreCase("lig_lol_demir"))
                emoteList.set(8, emote);
            if (emote.getName().equalsIgnoreCase("lig_lol_bronz"))
                emoteList.set(9, emote);
            if (emote.getName().equalsIgnoreCase("lig_lol_gumus"))
                emoteList.set(10, emote);
            if (emote.getName().equalsIgnoreCase("lig_lol_altin"))
                emoteList.set(11, emote);
            if (emote.getName().equalsIgnoreCase("lig_lol_plat"))
                emoteList.set(12, emote);
            if (emote.getName().equalsIgnoreCase("lig_lol_elmas"))
                emoteList.set(13, emote);
            if (emote.getName().equalsIgnoreCase("lig_lol_master"))
                emoteList.set(14, emote);
            if (emote.getName().equalsIgnoreCase("lig_lol_grandmaster"))
                emoteList.set(15, emote);
            if (emote.getName().equalsIgnoreCase("lig_lol_challanger"))
                emoteList.set(16, emote);
        }
        textChannel.sendMessageEmbeds(ligmesaj).queue(message -> {
            for (Emote emote : emoteList) {
                message.addReaction(emote).queue();
            }
        });
        textChannel.sendMessageEmbeds(oyunmesaj).queue(message -> {
            for (Emote emote : Objects.requireNonNull(guild).getEmotes()) {
                if (emote.getName().contains("oyun_")) {
                    message.addReaction(emote).queueAfter(4, TimeUnit.SECONDS);
                }
            }
        });
    }
}
