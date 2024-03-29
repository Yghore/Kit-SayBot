package fr.yghore.Models;

import fr.yghore.Utils.TimeFormat;
import fr.yghore.dyglib.Data.Salvageable;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.time.LocalDateTime;

public class Warn implements Salvageable
{

    public enum warnType{

        INSULTS("Insulte"), SPAM("Spam"), MAJ("Maj"), OTHER("Autre");

        public final String label;

        warnType(String label) {
            this.label = label;
        }

        public static warnType type(String n) {
            for (warnType c : values()) {
                if (c.label.equalsIgnoreCase(n)) {
                    return c;
                }
            }
            return OTHER;
        }

    }

    public enum warnFilter{

        ACTIF("Actif"), INACTIF("Inactif"), NEWEST("Recent");

        public final String label;

        warnFilter(String label) {
            this.label = label;
        }

        public static warnFilter type(String n) {
            for (warnFilter c : values()) {
                if (c.label.equalsIgnoreCase(n)) {
                    return c;
                }
            }
            return null;
        }

    }

    private long id;


    private long moderator;

    private LocalDateTime dateExpiration;
    private LocalDateTime dateCreated;

    private warnType type;

    private String desc;

    public Warn(long id, LocalDateTime dateExpire, warnType type, String desc, long moderator)
    {
        this.id = id;

        this.dateExpiration = dateExpire;
        this.dateCreated = LocalDateTime.now();

        this.moderator = moderator;

        this.type = type;
        this.desc = desc;
    }

    public Warn(long id)
    {
        this.id = id;
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


    public long getModerator() {
        return moderator;
    }

    public void setModerator(long moderator) {
        this.moderator = moderator;
    }

    public boolean isActive()
    {
        return this.dateExpiration.isAfter(LocalDateTime.now());
    }



    public MessageEmbed.Field[] toFields()
    {
        return new MessageEmbed.Field[]{
                new MessageEmbed.Field("**⚠️ Avertissement** (``" + this.id + "``) __" + this.type.label + "__", "", false),
                new MessageEmbed.Field("Modérateur : ", "<@" + this.moderator + ">", true),
                new MessageEmbed.Field("Description", this.desc, false),
                new MessageEmbed.Field("Création", TimeFormat.LocalDateTimeToDiscordFormatted(this.dateCreated, TimeFormat.DiscordFormat.SHORT_DATE_TIME), true),
                new MessageEmbed.Field("Expiration",  TimeFormat.LocalDateTimeToDiscordFormatted( this.dateExpiration, TimeFormat.DiscordFormat.RELATIVE) + ((!this.isActive()) ? "\uD83D\uDD13" : "\uD83D\uDD12"), true),
                null,
        };
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == this){return true;}
        if(!(o instanceof Warn w)){return false;}
        return w.id == this.id;

    }

}
