package fr.yghore.Commands.upsertCommand;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandGroupData;

public class UpsertWarn extends UCommand {




    @Override
    public void load(Guild guild) {
        SubcommandData add = new SubcommandData("add", "Permet l'ajout d'un warn à un membre")
                .addOption(OptionType.USER, "username", "Utilisateur concerné", true)
                .addOption(OptionType.STRING, "date", "Date d'expiration (FORMAT : 00d00h00m00s d = JOUR, h = HEURE, m = MINUTE, S = SECONDE", true)
                .addOption(OptionType.STRING, "description", "descriptions en cas d'ajout d'un warn", true);
        SubcommandData view = new SubcommandData("view", "Permet de voir l'historique des avertissements")
                .addOption(OptionType.USER, "username", "Utilisateur concerné", true);
        guild.upsertCommand("warn", "Commande administratif pour la gestion des avertissements")
                .addSubcommands(add, view)
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                .setGuildOnly(true)
                .queue();
    }
}
