package fr.yghore.Commands.upsertCommand;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;


public class UpsertRole extends UCommand {

    @Override
    public void load(Guild guild) {

        guild.upsertCommand("role", "Gestion des boutons pour les roles")
                .addOption(OptionType.STRING, "name", "Nom du boutton", true)
                .addOption(OptionType.ROLE, "role", "Role qui va être attribué", true)
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED).queue();
    }

    @Override
    public void autoComplete(CommandAutoCompleteInteractionEvent event) {

    }
}
