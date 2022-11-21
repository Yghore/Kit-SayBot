package fr.yghore;

import fr.yghore.Commands.Ban;
import fr.yghore.Commands.Joke;
import fr.yghore.Commands.ViewProfil;
import fr.yghore.Commands.Warn;
import fr.yghore.Commands.upsertCommand.*;
import fr.yghore.Models.ConfigData;
import fr.yghore.Utils.unBanThread;
import fr.yghore.dyglib.Configuration.Configuration;
import fr.yghore.dyglib.Configuration.ConfigurationException;
import fr.yghore.dyglib.Logger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;


import java.util.Timer;


public class Main {



    public static Logger LOGGER;
    public static Loader loader;
    public static Guild guild;

    public static void main(String[] args) throws ConfigurationException {
        Configuration config = new Configuration("config.yml", true, "KitSayBot");
        ConfigData.setConfig((ConfigData) config.loadConfig(ConfigData.class));
        if(ConfigData.getConfig() == null){LOGGER.sendError("Une erreur est survenu lors du chargement de la configuration !"); System.exit(1);}


        LOGGER = Logger.getLogger();

        LOGGER.sendInfo("Token  : " + ConfigData.getConfig().getToken());
        LOGGER.sendDebug("GuildId : " + ConfigData.getConfig().getGuildId());
        LOGGER.sendInfo("Initialisation....");





        try {
            JDA jda = JDABuilder.create(
                            ConfigData.getConfig().getToken(),
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.MESSAGE_CONTENT,
                            GatewayIntent.GUILD_MEMBERS
                    )
                    .addEventListeners(
                            new Warn(),
                            new Joke(),
                            new Ban(),
                            new ViewProfil()
                    )
                    .build().awaitReady();

            Main.guild = jda.getGuildById(ConfigData.getConfig().getGuildId());
            Loader loader = new Loader(Main.guild).
                    addUCommands(
                            new UpsertWarn(),
                            new UpsertJoke(),
                            new UpsertBan(),
                            new UpsertViewProfil()
                    );

            loader.loader();
            jda.addEventListener(new AutoComplete(loader));

            if(Main.guild.getRoles().stream()
                    .filter(role -> role.getName().equals("Banni")) // filter by role name
                    .findFirst() // take first result
                    .orElse(null) == null)
            {
                Main.LOGGER.sendInfo("Mise en place");

                Main.guild.createRole()
                        .setColor(0x0e0e0e)
                        .setMentionable(false)
                        .setName("Banni")
                        .setPermissions(0L)
                        .queue();
                Thread.sleep(1000);
                Role roleBanned = Main.guild.getRoles().stream()
                        .filter(role -> role.getName().equals("Banni")) // filter by role name
                        .findFirst()
                        .orElse(null);

                System.out.println(roleBanned);


            }
            else
            {
                Main.LOGGER.sendInfo("Role du banni déjà mis en place");
                Main.LOGGER.sendInfo("Mise à jour des salons");
                Role roleBanned = Main.guild.getRoles().stream()
                        .filter(role -> role.getName().equals("Banni")) // filter by role name
                        .findFirst()
                        .orElse(null);
                Main.updateChannelsBanned(roleBanned);
            }

            Main.LOGGER.sendInfo("Mise en place du débanneur automatique");
            new Timer().schedule(new unBanThread(), 10 * 1000, 60 * 1000);



        }
         catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static void updateChannelsBanned(Role roleBanned)
    {
        if(Main.guild.getCategoriesByName("Banni", true).isEmpty())
        {
            Main.guild.createCategory("Banni").addPermissionOverride(guild.getPublicRole(), 0, Permission.ALL_TEXT_PERMISSIONS).queue();
        }
        for (GuildChannel channel : Main.guild.getChannels()) {
            Main.LOGGER.sendInfo("Mise à jour du salon " + channel.getName());
            channel.getPermissionContainer().getManager().putRolePermissionOverride(roleBanned.getIdLong(), 0, Permission.ALL_PERMISSIONS).queue();
        }
    }
}
