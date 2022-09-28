package fr.yghore.Data;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class User implements Serializable
{

    private String memberId;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;
    private ArrayList<Warn> warns;

    public User(String memberId)
    {
        this.memberId = memberId;
        this.created_date = LocalDateTime.now();
        this.updated_date = LocalDateTime.now();
        this.warns = new ArrayList<>();
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

    public ArrayList<Warn> getWarns() {
        return warns;
    }


    public void addWarn(Warn warn)
    {
        this.warns.add(warn);
    }

    public int getActiveWarn()
    {
        int active = 0;
        for(Warn w : this.warns)
        {
            if(w.getDate().plusMinutes(ConfigData.getConfig().getWarnExpireTime()).isBefore(LocalDateTime.now()))
            {
                active++;
            }
        }
        return active;
    }

    public int getAllWarn()
    {
        return this.warns.size();
    }



}
