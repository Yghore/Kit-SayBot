package fr.yghore;

import fr.yghore.Commands.Warn;
import fr.yghore.Data.ConfigData;
import fr.yghore.dyglib.Configuration;
import fr.yghore.dyglib.ConfigurationException;
import fr.yghore.dyglib.Logger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {



    public static Logger LOGGER;


    public static void main(String[] args) throws ConfigurationException {
        Configuration config = new Configuration("config.yml", true, "KitSayBot");
        ConfigData.setConfig((ConfigData) config.loadConfig(ConfigData.class));
        if(ConfigData.getConfig() == null){LOGGER.sendError("Une erreur est survenu lors du chargement de la configuration !"); System.exit(1);}

        LOGGER = Logger.getLogger();

        LOGGER.sendPrint("Token  : " + ConfigData.getConfig().getToken());
        LOGGER.sendDebug("WarnExpiration : " + ConfigData.getConfig().getWarnExpireTime());
        LOGGER.sendPrint("Initialisation....");

        try {
            JDA jda = JDABuilder.create(
                            ConfigData.getConfig().getToken().toString(),
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.MESSAGE_CONTENT,
                            GatewayIntent.GUILD_MEMBERS
                    )
                    .addEventListeners(
                            new Warn()
                    )
                    .build();

        }
        catch(LoginException e)
        {
            LOGGER.sendError("Une erreur est survenu lors de la connexion (token invalide)");
            System.exit(1);
        }

    }

}
