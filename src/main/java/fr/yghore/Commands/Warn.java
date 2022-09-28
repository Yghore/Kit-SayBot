package fr.yghore.Commands;

import fr.yghore.Data.User;
import fr.yghore.Data.Users;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import static fr.yghore.Utils.Const.EMBED_NOT_FOUND;


public class Warn extends ListenerAdapter
{

    private Users users;

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event)
    {
        if(event.getName().equals("warn"))
        {
            String action = String.valueOf(event.getOption("action"));
            Member member = (Member) event.getOption("member");
            User userData = this.users.get(member.getId());
            switch (action)
            {
                case "view":
                    if(userData == null) {event.replyEmbeds(EMBED_NOT_FOUND).queue(); return;};
                    EmbedBuilder eb = new EmbedBuilder()
                            .setAuthor(event.getMember() + " - " + member.getEffectiveName())
                            .setThumbnail(member.getEffectiveAvatarUrl())
                            .setTitle("Historique du membre :" + member.getEffectiveName());
                    for(fr.yghore.Data.Warn w : userData.getWarns())
                    {
                        eb.addField(w.toField());
                    }
                    event.replyEmbeds(eb.build()).queue();
                    break;
            }
        }
    }
}
