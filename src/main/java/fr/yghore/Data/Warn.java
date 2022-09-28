package fr.yghore.Data;

import fr.yghore.Utils.Const;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Warn implements Serializable
{

    public enum warnType{Insults, SPAM, MAJ, OTHER};

    private long id;
    private LocalDateTime date;
    private warnType type;

    private String desc;

    public Warn(long id, LocalDateTime date, warnType type, String desc)
    {
        this.id = id;
        this.date = date;
        this.type = type;
        this.desc = desc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public warnType getType() {
        return type;
    }

    public void setType(warnType type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isActive()
    {
        return this.getDate().plusMinutes(ConfigData.getConfig().getWarnExpireTime()).isBefore(LocalDateTime.now());
    }

    public MessageEmbed.Field toField()
    {
        return new MessageEmbed.Field(String.valueOf(this.id), this.desc + "\n__" + this.date.format(Const.DTF) + "__", true);
    }

}
