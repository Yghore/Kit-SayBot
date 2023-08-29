package fr.yghore.Commands;

import fr.yghore.Main;
import fr.yghore.Models.Bans;
import fr.yghore.Models.ConfigData;
import fr.yghore.Models.Moderator;
import fr.yghore.Models.Profile;
import fr.yghore.Utils.Const;
import fr.yghore.Utils.TimeFormat;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Ban extends ListenerAdapter {


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equalsIgnoreCase("ban"))
        {
            Member member = event.getOption("username").getAsMember();
            String desc = event.getOption("desc").getAsString();
            String date = (event.getOption("date") == null) ? "FOREVER" : event.getOption("date").getAsString();
            long modoId = event.getMember().getIdLong();
            Profile profile = Profile.load(member.getId());
            Moderator moderator = Moderator.load(event.getMember().getId());
            if(date == "FOREVER")
            {
                moderator.addBan(new fr.yghore.Models.Ban(modoId, member.getIdLong(), desc));
                member.ban(10, TimeUnit.HOURS).reason(desc).queue();
                profile.setBanned(true);
                event.reply("L'utilisateur <@" + member.getIdLong() + "> à bien été banni à vie!");

            }
            else
            {
                Bans bans = Bans.load();
                Duration duration = TimeFormat.parse(date);
                fr.yghore.Models.Ban ban = new fr.yghore.Models.Ban(member.getIdLong(), modoId, desc, duration);
                moderator.addBan(ban);
                bans.addBan(ban);
                bans.save();
                profile.setBanned(true);
                Role roleBanned = Main.guild.getRoles().stream()
                        .filter(role -> role.getName().equals("Banni")) // filter by role name
                        .findFirst()
                        .orElse(null);
                if(roleBanned != null)
                {
                    Main.guild.addRoleToMember(member, roleBanned).queue();
                    Category cat = Main.guild.getCategoriesByName("banni", true).get(0);
                    Main.guild.createTextChannel("b-" + member.getEffectiveName(), cat)
                            .addMemberPermissionOverride(member.getIdLong(), Permission.MESSAGE_SEND.getRawValue() + Permission.VIEW_CHANNEL.getRawValue() + Permission.MESSAGE_HISTORY.getRawValue(), 0)
                            .addRolePermissionOverride(Main.guild.getPublicRole().getIdLong(), 0, Permission.ALL_PERMISSIONS)
                            .queue();

                    event.reply("L'utilisateur <@" + member.getIdLong() + "> à bien été banni jusqu'a " + TimeFormat.LocalDateTimeToDiscordFormatted(LocalDateTime.now().plus(duration), TimeFormat.DiscordFormat.RELATIVE)).queue();

                }
            }

            moderator.save();

            profile.save();

        }
    }
}
