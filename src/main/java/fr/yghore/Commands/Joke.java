package fr.yghore.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Joke extends ListenerAdapter {


    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("blague")) {
            String joke = fr.yghore.Models.Joke.load("joke.json").getRandomJoke();

            event.replyEmbeds(new EmbedBuilder().setAuthor("Blague :").setDescription(joke).setColor(Color.RED).build()).queue();
        }

    }

}
