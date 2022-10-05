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

    public enum warnType{

        INSULTS("Insulte"), SPAM("Spam"), MAJ("Maj"), OTHER("Autre");

        public final String label;

        private warnType(String label) {
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

    };

    public enum warnFilter{

        ACTIF("Actif"), INACTIF("Inactif"), NEWEST("Recent");

        public final String label;

        private warnFilter(String label) {
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
        return this.dateExpiration.isAfter(LocalDateTime.now());
    }

    public MessageEmbed.Field[] toFields()
    {
        return new MessageEmbed.Field[]{
                new MessageEmbed.Field("**⚠️ Avertissement** (``" + this.id + "``) __" + this.type.label + "__", "", false),
                new MessageEmbed.Field("Description", this.desc, false),
                new MessageEmbed.Field("Création", this.dateCreated.format(Const.DTF), true),
                new MessageEmbed.Field("Expiration", this.dateExpiration.format(Const.DTF) + " " + ((!this.isActive()) ? "\uD83D\uDD13" : "\uD83D\uDD12"), true),
                null,
        };
    }

}
