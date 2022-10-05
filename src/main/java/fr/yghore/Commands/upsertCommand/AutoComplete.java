package fr.yghore.Commands.upsertCommand;

import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class AutoComplete extends ListenerAdapter
{

    private Loader loader;

    public AutoComplete(Loader loader)
    {
        this.loader = loader;
    }


    @Override
    public void onCommandAutoCompleteInteraction(@NotNull CommandAutoCompleteInteractionEvent event) {
        this.loader.loadAutoComplete(event);
    }
}
