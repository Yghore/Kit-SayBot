package fr.yghore.Commands.upsertCommand;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class Loader extends ArrayList<UCommand>
{

    private Guild guild;

    public Loader(Guild g)
    {
        this.guild = g;
    }

    public Loader addUCommand(UCommand cmd)
    {
        super.add(cmd);
        return this;
    }

    public Loader addUCommands(UCommand ...cmds)
    {
        super.addAll(Arrays.stream(cmds).toList());
        return this;
    }

    public void loader()
    {
        this.forEach(v ->
        {
            v.load(guild);
        });
    }

    public void loadAutoComplete(CommandAutoCompleteInteractionEvent e)
    {
        this.forEach(v -> {
            v.autoComplete(e);
        });
    }


}
