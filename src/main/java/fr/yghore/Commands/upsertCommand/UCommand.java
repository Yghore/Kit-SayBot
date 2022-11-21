package fr.yghore.Commands.upsertCommand;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;

public abstract class UCommand
{

    public void load(Guild guild) {}

    public abstract void autoComplete(CommandAutoCompleteInteractionEvent event);

}
