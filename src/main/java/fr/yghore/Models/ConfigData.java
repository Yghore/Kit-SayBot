package fr.yghore.Models;

import fr.yghore.dyglib.Configuration.Configurable;

public class ConfigData extends Configurable
{

    private static ConfigData config;

    private String token;


    private long welcomeChannel;
    private long guildId;

    private String usersFolder;


    private String dataFolder;

    // dataFolder

    public String getDataFolder() {
        return dataFolder;
    }

    public void setDataFolder(String dataFolder) {
        this.dataFolder = dataFolder;
    }


    // welcomeChannel

    public long getWelcomeChannel() {
        return welcomeChannel;
    }

    public void setWelcomeChannel(long welcomeChannel) {
        this.welcomeChannel = welcomeChannel;
    }


    // guildId

    public long getGuildId() {
        return guildId;
    }

    public void setGuildId(long guildId) {
        this.guildId = guildId;
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
