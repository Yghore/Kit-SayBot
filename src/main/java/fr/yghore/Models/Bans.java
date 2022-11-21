package fr.yghore.Models;

import fr.yghore.Main;
import fr.yghore.dyglib.Data.Json;
import fr.yghore.dyglib.Data.Salvageable;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

public class Bans extends Json implements Salvageable
{

    private List<Ban> bans;

    public Bans(Path path) {
        super(path.toString());
        this.bans = new ArrayList<>();
    }


    public List<Ban> getBans() {
        return bans;
    }

    public void addBan(Ban ban)
    {
        this.bans.add(ban);
    }

    public void removeExpiredBan()
    {
        List<Ban> bans = Collections.synchronizedList(this.bans);

        Iterator<Ban> it = bans.iterator();
        while(it.hasNext()) {
            Ban ban = it.next();
            if(ban.getDate_expiration().isBefore(LocalDateTime.now())) {
                it.remove();

                Member member = Main.guild.getMemberById(ban.getUserId());

                if(member != null)
                {
                    Role roleBanned = Main.guild.getRoles().stream()
                            .filter(role -> role.getName().equalsIgnoreCase("Banni")) // filter by role name
                            .findFirst()
                            .orElse(null);
                    if(roleBanned != null)
                    {
                        Main.guild.removeRoleFromMember(member, roleBanned).queue();
                    }
                    TextChannel channel = Main.guild.getTextChannelsByName("b-" + member.getEffectiveName(), true).get(0);
                    channel.getManager().putMemberPermissionOverride(ban.getUserId(), 0, 0).queue();
                    channel.getManager().setName("old-" + channel.getName()).queue();
                }
                Main.LOGGER.sendInfo("Supression d'un ban : " + ban);

            }
        }

        this.bans = bans;

    }

    public static Bans load()  {
        Path path = Path.of("banned", "banned.json");

        try {
            return (Bans) Json.load(path.toString(), Bans.class);
        }
        catch(FileNotFoundException e)
        {
            return new Bans(path);
        }

    }


}
