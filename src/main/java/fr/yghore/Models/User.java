package fr.yghore.Models;

import fr.yghore.Main;
import fr.yghore.Utils.TimeFormat;
import fr.yghore.dyglib.Data.Json;
import fr.yghore.dyglib.Data.Salvageable;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;


import java.time.LocalDateTime;


public class User extends Json implements Salvageable
{

    private String memberId;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;


    public User(String memberId)
    {
        this.memberId = memberId;
        this.created_date = LocalDateTime.now();
        this.updated_date = LocalDateTime.now();
    }


    public Member getMember()
    {
        return Main.guild.getMemberById(this.memberId);
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public LocalDateTime getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(LocalDateTime updated_date) {
        this.updated_date = updated_date;
    }


    protected void update()
    {
        this.updated_date = LocalDateTime.now();
    }



    public EmbedBuilder toEmbed(EmbedBuilder embedBuilder)
    {
        embedBuilder.setAuthor(this.getMember().getNickname());
        embedBuilder.setThumbnail(this.getMember().getEffectiveAvatarUrl());
        embedBuilder.addField("Crée le ", TimeFormat.LocalDateTimeToDiscordFormatted(this.created_date, TimeFormat.DiscordFormat.SHORT_DATE_TIME), true);
        embedBuilder.addField("Dernière mise à jour le", TimeFormat.LocalDateTimeToDiscordFormatted(this.updated_date, TimeFormat.DiscordFormat.SHORT_DATE_TIME), true);
        embedBuilder.addField("A rejoint le 🚪", TimeFormat.LocalDateTimeToDiscordFormatted(this.getMember().getTimeJoined().toLocalDateTime(), TimeFormat.DiscordFormat.SHORT_DATE_TIME), false);
        embedBuilder.addBlankField(false);
        return embedBuilder;
    }






}
