package fr.yghore.Data;

import fr.yghore.Utils.Const;
import fr.yghore.dyglib.Data.Salvageable;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.emoji.Emoji;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Warn extends Salvageable
{

    public enum warnType{INSULTS("Insulte"), SPAM("Spam"), MAJ("Majuscules"), OTHER("Autre");

        public final String label;

        private warnType(String label) {
            this.label = label;
        }
    };

    private long id;

    private LocalDateTime dateExpiration;
    private LocalDateTime dateCreated;

    private warnType type;

    private String desc;

    public Warn(long id, LocalDateTime dateExpire, warnType type, String desc)
    {
        this.id = id;

        this.dateExpiration = dateExpire;
        this.dateCreated = LocalDateTime.now();

        this.type = type;
        this.desc = desc;
    }

    public LocalDateTime getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDateTime dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return this.dateExpiration.isBefore(LocalDateTime.now());
    }

    public MessageEmbed.Field[] toFields()
    {
        return new MessageEmbed.Field[]{
                new MessageEmbed.Field("ID : ", String.valueOf(this.id), true),
                new MessageEmbed.Field("Type :", this.type.label, true),
                new MessageEmbed.Field("Description : ", this.desc, false),
                new MessageEmbed.Field("Expiration :", this.dateExpiration.format(Const.DTF) + " " + ((this.isActive()) ? Emoji.fromFormatted(":close").asUnicode() :  Emoji.fromFormatted(":open:").asUnicode()), true),
                new MessageEmbed.Field("Création", this.dateCreated.format(Const.DTF), true)
        };
    }

}
