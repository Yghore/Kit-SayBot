package fr.yghore.Commands;

import fr.yghore.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.FileNotFoundException;

public class Joke extends ListenerAdapter {


    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("blague")) {
            String joke = null;
            try {
                joke = ((fr.yghore.Models.Joke) fr.yghore.Models.Joke.load("joke.json", fr.yghore.Models.Joke.class)).getRandomJoke();
            } catch (FileNotFoundException e) {
                Main.LOGGER.sendInfo("Le fichier blague n'existe pas !");
            }

            event.replyEmbeds(new EmbedBuilder().setAuthor("Blague :").setDescription(joke).setColor(Color.RED).build()).queue();
        }

    }

}
