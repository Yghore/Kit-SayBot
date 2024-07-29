package fr.yghore.Commands.upsertCommand;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

public class UpsertWarn extends UCommand {




    @Override
    public void load(Guild guild) {



        SubcommandData add = new SubcommandData("add", "Permet l'ajout d'un warn à un membre")
                .addOption(OptionType.USER, "username", "Utilisateur concerné", true)
                .addOption(OptionType.STRING, "date", "Date d'expiration (FORMAT : 00d00h00m00s | FOREVER)", true)
                .addOption(OptionType.STRING, "description", "descriptions en cas d'ajout d'un warn", true)
                .addOptions(new OptionData(OptionType.STRING, "type", "Type d'avertissement", false)
                        .addChoice("Insulte", "INSULTE")
                        .addChoice("Spam", "SPAM")
                        .addChoice("Majuscules", "MAJ")
                        .addChoice("Autre", "AUTRE")
                );


        SubcommandData view = new SubcommandData("view", "Permet de voir l'historique des avertissements")
                .addOption(OptionType.USER, "username", "Utilisateur concerné", true)
                .addOption(OptionType.INTEGER, "page", "Page des warnings", false)
                .addOptions(new OptionData(OptionType.STRING, "filter", "Filtre de recherche", false)
                        .addChoice("Actif", "actif")
                        .addChoice("Inactif", "inactif")
                        .addChoice("Recent", "recent")
                )
                .addOption(OptionType.INTEGER, "resultperpage", "Le nombre d'avertissement par page", false);


        SubcommandData remove = new SubcommandData("remove", "Permet de supprimé un avertissement")
                .addOption(OptionType.USER, "username", "Utilisateur concerné", true)
                .addOption(OptionType.INTEGER, "id", "Id de l'avertissement", true);

        SubcommandData modify = new SubcommandData("modify", "Modifie l'expiration d'un avertissement")
                .addOption(OptionType.USER, "username", "Utilisateur concerné", true)
                .addOption(OptionType.INTEGER, "id", "Id de l'avertissement", true)
                .addOptions(new OptionData(OptionType.STRING, "type", "Type de changements", true)
                        .addChoice("Ajouter", "add")
                        .addChoice("Enlever", "remove")
                        .addChoice("Redefinir", "set")
                )
                .addOption(OptionType.STRING, "expiration", "Durée à ajouter(FORMAT : 00d00h00m00s d = JOUR, h = HEURE, m = MINUTE, S = SECONDE)", true);


        guild.upsertCommand("warn", "Commande administratif pour la gestion des avertissements")
                .addSubcommands(view, add, remove, modify)
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                .setGuildOnly(true)
                .queue();
    }

    @Override
    public void autoComplete(CommandAutoCompleteInteractionEvent event){}



}
