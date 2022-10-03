package fr.yghore.Commands;

import fr.yghore.Data.Data;
import fr.yghore.Data.User;
import fr.yghore.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

import static fr.yghore.Utils.Const.EMBED_NOT_FOUND;


public class Warn extends ListenerAdapter
{



    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event)
    {
        if(event.getName().equals("warn"))
        {
            String action = event.getOption("action").getAsString();
            Member member = event.getOption("username").getAsMember();
            User userData = Data.getDataUsers(member.getId());

            Main.LOGGER.sendDebug(action);
            switch (action)
            {
                case "view":
                    if(userData == null) {event.replyEmbeds(EMBED_NOT_FOUND).queue(); return;};
                    EmbedBuilder eb = new EmbedBuilder()
                            .setAuthor(event.getMember().getEffectiveName() + " - <@" + member.getId() + ">")
                            .setThumbnail(member.getEffectiveAvatarUrl())
                            .setTitle("Historique du membre : " + member.getEffectiveName());
                    if(userData.getWarns().size() == 0){eb.addField(new MessageEmbed.Field("", "Aucun historique", false));}
                    for(fr.yghore.Data.Warn w : userData.getWarns())
                    {
                        eb.addField(w.toField());
                    }
                    event.replyEmbeds(eb.build()).queue();
                    break;
            }

            Data.saveDataUsers(member.getId(), userData);

        }
    }
}
