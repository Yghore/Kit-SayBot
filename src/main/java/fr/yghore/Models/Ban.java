package fr.yghore.Models;

import fr.yghore.dyglib.Data.Salvageable;

import java.time.Duration;
import java.time.LocalDateTime;

public class Ban implements Salvageable
{


    private LocalDateTime date_banned;
    private LocalDateTime date_expiration;
    private long userId;
    private long authorId;
    private String desc;


    public Ban(long userId, long authorId, String desc)
    {
        this.date_banned = LocalDateTime.now();
        this.date_expiration = LocalDateTime.MAX;
        this.userId = userId;
        this.authorId = authorId;
        this.desc = desc;
    }

    public Ban(long userId, long authorId, String desc, Duration duration)
    {
        this.date_banned = LocalDateTime.now();
        this.date_expiration = LocalDateTime.now().plus(duration);
        this.userId = userId;
        this.authorId = authorId;
        this.desc = desc;
    }


    @Override
    public String toString() {
        return this.userId + " banned to " + this.authorId + " from " + this.date_expiration;
    }

    public LocalDateTime getDate_banned() {
        return date_banned;
    }

    public void setDate_banned(LocalDateTime date_banned) {
        this.date_banned = date_banned;
    }

    public LocalDateTime getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(LocalDateTime date_expiration) {
        this.date_expiration = date_expiration;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }




}
