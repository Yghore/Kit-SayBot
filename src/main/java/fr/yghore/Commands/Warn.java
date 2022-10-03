package fr.yghore.Commands;

import fr.yghore.Data.Data;
import fr.yghore.Data.User;
import fr.yghore.Main;
import fr.yghore.Utils.TimeFormat;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static fr.yghore.Utils.Const.EMBED_MISSING_OPTION;
import static fr.yghore.Utils.Const.EMBED_NOT_FOUND;


public class Warn extends ListenerAdapter
{



    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event)
    {
        if(event.getName().equals("warn"))
        {

            Member member = event.getOption("username").getAsMember();
            User userData = Data.getDataUsers(member.getId());

            if(userData == null) {event.replyEmbeds(EMBED_NOT_FOUND).queue(); return;}
            switch (event.getSubcommandName())
            {
                case "view":
                    EmbedBuilder eb = new EmbedBuilder()
                            .setAuthor(event.getMember().getEffectiveName() + " - <@" + member.getId() + ">")
                            .setThumbnail(member.getEffectiveAvatarUrl())
                            .setTitle("Historique du membre : " + member.getEffectiveName());
                    if(userData.getWarns().size() == 0){eb.addField(new MessageEmbed.Field("", "Aucun historique", false));}
                    for(fr.yghore.Data.Warn w : userData.getWarns())
                    {

                        for (MessageEmbed.Field field : w.toFields()) {
                            eb.addField(field);
                        }
                        eb.addBlankField(false);
                    }
                    event.replyEmbeds(eb.build()).queue();
                    break;

                case "add":
                    Duration durationWarn = TimeFormat.parse(event.getOption("date").getAsString());
                    String desc = event.getOption("description").getAsString();
                    if(durationWarn == null || desc.equals("")){event.replyEmbeds(EMBED_MISSING_OPTION).setEphemeral(true).queue();return;}

                    LocalDateTime now = LocalDateTime.now();
                    long id =  + Long.parseLong("" + (member.getIdLong() % 10000) + now.getMonthValue() + now.getDayOfMonth() + now.getYear() + userData.getAllWarn());
                    LocalDateTime date = now.plus(durationWarn);
                    userData.addWarn(new fr.yghore.Data.Warn(id, date, fr.yghore.Data.Warn.warnType.INSULTS, desc));
                    event.replyEmbeds(
                            new EmbedBuilder().setAuthor("Ajout de l'avertissement")
                                    .setColor(Color.GREEN)
                                    .setDescription("Vous avez bien ajouté l'avertissement !")
                                    .build()
                    ).queue();
                    break;

            }

            Data.saveDataUsers(member.getId(), userData);

        }
    }
}
