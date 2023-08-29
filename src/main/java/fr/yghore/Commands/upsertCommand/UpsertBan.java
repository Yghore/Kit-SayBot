package fr.yghore.Commands.upsertCommand;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;


public class UpsertBan extends UCommand {

    @Override
    public void load(Guild guild) {

        guild.upsertCommand("ban", "Ban une personne (permet d'avoir un historique)")
                .addOption(OptionType.USER, "username", "Utilisateur à bannir", true)
                .addOption(OptionType.STRING, "desc", "Description", true)
                .addOption(OptionType.STRING, "date", "Date d'expiration (FORMAT : 00d00h00m00s | FOREVER)", false)
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED).queue();

    }

    @Override
    public void autoComplete(CommandAutoCompleteInteractionEvent event) {

    }
}
