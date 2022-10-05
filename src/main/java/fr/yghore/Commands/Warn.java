package fr.yghore.Commands;

import fr.yghore.Data.Data;
import fr.yghore.Data.User;
import fr.yghore.Main;
import fr.yghore.Utils.Const;
import fr.yghore.Utils.PaginationEmbed;
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
import java.util.ArrayList;
import java.util.Objects;
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
                    int page = (event.getOption("page") == null) ? 1 : event.getOption("page").getAsInt();
                    int resultPerPage = (event.getOption("resultperpage") == null) ? 4 : event.getOption("resultperpage").getAsInt() * 4;
                    fr.yghore.Data.Warn.warnFilter filter = (event.getOption("filter") == null) ? null : fr.yghore.Data.Warn.warnFilter.type(event.getOption("filter").getAsString());
                    EmbedBuilder eb = new EmbedBuilder()
                            .setAuthor(event.getMember().getEffectiveName())
                            .setThumbnail(member.getEffectiveAvatarUrl())
                            .setTitle("Historique du membre (" + ((filter == null) ? "Aucun Filtre" : filter.label) + ")")
                            .setDescription("⚠️ Nombre d'avertissement : " + userData.getAllWarn()
                            + "\n" + "🛑 Nombre d'avertissement en cours : " + userData.getActiveWarn());
                            eb.addBlankField(false);

                    if(userData.getWarns().size() == 0){
                        eb.addField(new MessageEmbed.Field("", "Aucun historique", false));
                        event.replyEmbeds(eb.build()).queue();
                        return;
                    }


                    ArrayList<fr.yghore.Data.Warn> warns = userData.getWarns();
                    if(filter == fr.yghore.Data.Warn.warnFilter.NEWEST){ warns = userData.getOldestWarns(5);}
                    if(filter == fr.yghore.Data.Warn.warnFilter.ACTIF){ warns = userData.getActifWarns();}
                    if(filter == fr.yghore.Data.Warn.warnFilter.INACTIF){ warns = userData.getInactifWarns();}
                    for(fr.yghore.Data.Warn w : warns)
                    {
                        for (MessageEmbed.Field field : w.toFields()) {
                            eb.addField(field);
                            if(field == null){
                                eb.addBlankField(false);

                            }
                        }
                    }
                    PaginationEmbed pe = new PaginationEmbed(eb, resultPerPage);

                    event.replyEmbeds(pe.getPaginateEmbed(page)).queue();
                    break;

                case "add":
                    Duration durationWarn = TimeFormat.parse(event.getOption("date").getAsString());
                    String desc = event.getOption("description").getAsString();
                    String type = Objects.requireNonNull(event.getOption("type")).getAsString();


                    fr.yghore.Data.Warn.warnType typeWarn = typeWarn = fr.yghore.Data.Warn.warnType.type(type);

                    if(durationWarn == null || desc.equals("")){event.replyEmbeds(EMBED_MISSING_OPTION).setEphemeral(true).queue();return;}

                    LocalDateTime now = LocalDateTime.now();
                    long id =  + Long.parseLong("" + (member.getIdLong() % 10000) + now.getMonthValue() + now.getDayOfMonth() + now.getYear() + userData.getAllWarn());
                    LocalDateTime date = now.plus(durationWarn);
                    fr.yghore.Data.Warn warn = new fr.yghore.Data.Warn(id, date, typeWarn, desc);
                    userData.addWarn(warn);
                    EmbedBuilder embedBuilder = new EmbedBuilder().setAuthor("Ajout de l'avertissement")
                            .setColor(Color.GREEN)
                            .setDescription("Vous avez ajouté l'avertissement suivant : ");

                    for (MessageEmbed.Field field : warn.toFields()) {
                        embedBuilder.addField(field);
                    }

                    event.replyEmbeds(
                        embedBuilder.build()
                    ).queue();
                    break;
                case "remove":
                    long idRemove = event.getOption("id").getAsLong();
                    int index = userData.getWarns().indexOf(new fr.yghore.Data.Warn(idRemove));
                    if(index == -1){ event.replyEmbeds(EMBED_NOT_FOUND).queue();return;}
                    warn = userData.getWarns().get(index);
                    userData.getWarns().remove(new fr.yghore.Data.Warn(idRemove));
                    embedBuilder = new EmbedBuilder()
                            .setColor(Color.GREEN)
                            .setTitle("Vous venez de supprimé l'avertissement :");

                    for (MessageEmbed.Field field : warn.toFields()) {
                        embedBuilder.addField(field);
                    }

                    event.replyEmbeds(
                            embedBuilder.build()
                    ).queue();
                    break;

                case "modify":
                    long idModify = event.getOption("id").getAsLong();
                    String expiration = event.getOption("expiration").getAsString();
                    index = userData.getWarns().indexOf(new fr.yghore.Data.Warn(idModify));
                    if(index == -1){ event.replyEmbeds(EMBED_NOT_FOUND).queue();return;}
                    warn = userData.getWarns().get(index);
                    warn.setDateExpiration(warn.getDateExpiration().plus(TimeFormat.parse(expiration)));
                    break;

            }

            Data.saveDataUsers(member.getId(), userData);

        }
    }
}
