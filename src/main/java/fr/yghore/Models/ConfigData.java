package fr.yghore.Models;

import fr.yghore.dyglib.Configuration.Configurable;

public class ConfigData implements Configurable {

    private static ConfigData config;

    private String token;

    private long welcomeChannel;
    private long guildId;

    private String usersFolder;


    private String dataFolder;

    private String twitchToken;
    private String twitchUsername;

    private long announceTwitchChannel;



    public ConfigData() {
    }

    public String getTwitchToken() {
        return twitchToken;
    }

    public void setTwitchToken(String twitchToken) {
        this.twitchToken = twitchToken;
    }

    public String getTwitchUsername() {
        return twitchUsername;
    }

    public void setTwitchUsername(String twitchUsername) {
        this.twitchUsername = twitchUsername;
    }

    public long getAnnounceTwitchChannel() {
        return announceTwitchChannel;
    }

    public void setAnnounceTwitchChannel(long announceTwitchChannel) {
        announceTwitchChannel = announceTwitchChannel;
    }

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
