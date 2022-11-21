package fr.yghore.Commands.upsertCommand;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class UpsertViewProfil extends UCommand {

    @Override
    public void load(Guild guild)
    {
        guild.upsertCommand("viewprofil", "Voir le profil d'un utilisateur")
                .addOption(OptionType.USER, "username", "Utilisateur", true)
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                .queue();
    }

    @Override
    public void autoComplete(CommandAutoCompleteInteractionEvent event) {

    }
}
