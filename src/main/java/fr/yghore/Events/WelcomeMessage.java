package fr.yghore.Events;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class WelcomeMessage extends ListenerAdapter {


    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent event) {

    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        
    }
}
