package fr.yghore.Commands.upsertCommand;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;

public class UpsertJoke extends UCommand {
    @Override
    public void load(Guild guild) {
        guild.upsertCommand("blague", "Affiche une blague").queue();
    }

    @Override
    public void autoComplete(CommandAutoCompleteInteractionEvent event) {

    }
}
