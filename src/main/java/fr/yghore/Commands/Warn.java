package fr.yghore.Commands;

import fr.yghore.Utils.Const;
import fr.yghore.Utils.PaginationEmbed;
import fr.yghore.Models.Profile;
import fr.yghore.Utils.TimeFormat;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class Warn extends ListenerAdapter
{



    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event)
    {
        if(event.getName().equals("warn"))
        {

            Member member = event.getOption("username").getAsMember();
            Profile userData = Profile.load(member.getId());



//            if(member.hasPermission(Permission.ADMINISTRATOR) || member.isOwner()){
//                event.replyEmbeds(Const.EMBED_FORBIDDEN_PERMISSION).queue();
//                            return;
//            }

            if(userData == null) {event.replyEmbeds(Const.EMBED_NOT_FOUND).queue(); return;}
            switch (event.getSubcommandName())
            {
                case "view":
                    int page = (event.getOption("page") == null) ? 1 : event.getOption("page").getAsInt();
                    int resultPerPage = (event.getOption("resultperpage") == null) ? 5 : event.getOption("resultperpage").getAsInt() * 5;
                    fr.yghore.Models.Warn.warnFilter filter = (event.getOption("filter") == null) ? null : fr.yghore.Models.Warn.warnFilter.type(event.getOption("filter").getAsString());
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


                    ArrayList<fr.yghore.Models.Warn> warns = userData.getWarns();
                    if(filter == fr.yghore.Models.Warn.warnFilter.NEWEST){ warns = userData.getOldestWarns(5);}
                    if(filter == fr.yghore.Models.Warn.warnFilter.ACTIF){ warns = userData.getActifWarns();}
                    if(filter == fr.yghore.Models.Warn.warnFilter.INACTIF){ warns = userData.getInactifWarns();}
                    for(fr.yghore.Models.Warn w : warns)
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
                    String type = (event.getOption("type") == null) ? "OTHER" : event.getOption("type").getAsString();


                    fr.yghore.Models.Warn.warnType typeWarn = fr.yghore.Models.Warn.warnType.type(type);

                    if(durationWarn == null || desc.equals("")){event.replyEmbeds(Const.EMBED_MISSING_OPTION).setEphemeral(true).queue();return;}

                    LocalDateTime now = LocalDateTime.now();
                    long id = Long.parseLong("" + (member.getIdLong() % 10000) + now.getMonthValue() + now.getDayOfMonth() + now.getYear() + userData.getAllWarn());
                    LocalDateTime date = now.plus(durationWarn);
                    fr.yghore.Models.Warn warn = new fr.yghore.Models.Warn(id, date, typeWarn, desc, event.getMember().getIdLong());
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

                    if(userData.getActiveWarn() == 2){ member.timeoutFor(1, TimeUnit.HOURS).queue();}
                    if(userData.getActiveWarn() == 4){ member.timeoutFor(5, TimeUnit.HOURS).queue();}

                    break;
                case "remove":
                    long idRemove = event.getOption("id").getAsLong();
                    int index = userData.getWarns().indexOf(new fr.yghore.Models.Warn(idRemove));
                    if(index == -1){ event.replyEmbeds(Const.EMBED_NOT_FOUND).queue();return;}
                    warn = userData.getWarns().get(index);
                    userData.getWarns().remove(new fr.yghore.Models.Warn(idRemove));
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
                    type = event.getOption("type").getAsString();
                    index = userData.getWarns().indexOf(new fr.yghore.Models.Warn(idModify));
                    if(index == -1){ event.replyEmbeds(Const.EMBED_NOT_FOUND).queue();return;}

                    warn = userData.getWarns().get(index);
                    if(type.equalsIgnoreCase("add")) {warn.setDateExpiration(warn.getDateExpiration().plus(TimeFormat.parse(expiration)));}
                    else if(type.equalsIgnoreCase("remove")) {warn.setDateExpiration(warn.getDateExpiration().minus(TimeFormat.parse(expiration)));}
                    else if(type.equalsIgnoreCase("set")) {warn.setDateExpiration(LocalDateTime.now().plus(TimeFormat.parse(expiration)));}

                    embedBuilder = new EmbedBuilder().setAuthor("Modification de l'avertissement")
                            .setColor(Color.GREEN)
                            .setDescription("Vous avez modifié l'avertissement suivant : ");

                    for (MessageEmbed.Field field : warn.toFields()) {
                        embedBuilder.addField(field);
                    }

                    event.replyEmbeds(
                            embedBuilder.build()
                    ).queue();
                    break;

            }

            userData.save();

        }
    }
}
