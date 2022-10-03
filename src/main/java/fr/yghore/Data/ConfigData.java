package fr.yghore.Data;

import fr.yghore.dyglib.Configuration.Configurable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConfigData extends Configurable
{

    private static ConfigData config;

    private String token;

    private long warnExpireTime;

    private long guildId;

    private String usersFolder;

    // guildId

    public long getGuildId() {
        return guildId;
    }

    public void setGuildId(long guildId) {
        this.guildId = guildId;
    }


    // warnExpireTime

    public long getWarnExpireTime()
    {
        return warnExpireTime;
    }

    public void setWarnExpireTime(long warnExpireTime) {
        this.warnExpireTime = warnExpireTime;
    }


    // Config

    public static ConfigData getConfig() {
        return config;
    }

    public static void setConfig(ConfigData config) {
        ConfigData.config = config;
    }


    // TOKEN

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsersFolder(String usersFolder) {
        this.usersFolder = usersFolder;
    }

    public String getUsersFolder() {
        return this.usersFolder;
    }
}
