package fr.yghore.Commands.upsertCommand;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class UpsertWarn extends UCommand {




    @Override
    public void load(Guild guild) {
        guild.upsertCommand("warn", "Commande administratif pour la gestion des avertissements")
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                .setGuildOnly(true)
                .addOption(OptionType.STRING, "action", "Actions à effectuer", true)
                .addOption(OptionType.USER, "username", "Membre", true)
                .addOption(OptionType.STRING, "description", "descriptions en cas d'ajout d'un warn", false)
                .queue();
    }
}
