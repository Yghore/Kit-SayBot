package fr.yghore.Commands;

import fr.yghore.Models.Profile;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ViewProfil extends ListenerAdapter
{

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("viewprofil"))
        {
            Member member = event.getOption("username").getAsMember();
            Profile profile = Profile.load(member.getId());
            EmbedBuilder embedBuilder = new EmbedBuilder();

            profile.toEmbed(embedBuilder);
            event.replyEmbeds(embedBuilder.build()).queue();

        }
    }
}
