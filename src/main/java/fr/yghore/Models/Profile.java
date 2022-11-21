package fr.yghore.Models;

import fr.yghore.dyglib.Data.Json;
import fr.yghore.dyglib.Data.Salvageable;
import net.dv8tion.jda.api.EmbedBuilder;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Profile extends User implements Salvageable
{

    private ArrayList<Warn> warns;

    private boolean isBanned;

    public Profile(String memberId)
    {
        super(memberId);
        this.warns = new ArrayList<>();
        this.isBanned = false;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
        this.update();
    }


    public ArrayList<Warn> getWarns() {
        return warns;
    }

    public ArrayList<Warn> getOldestWarns(int i) {
        if(i > this.warns.size()){i = 0;}
        i = this.warns.size() - i;
        ArrayList<Warn> warn = new ArrayList<>();
        for (int j = this.warns.size() - 1; j >= i; j--) {
            warn.add(this.warns.get(j));
        }
        return warn;
    }

    public ArrayList<Warn> getInactifWarns()
    {
        return (ArrayList<Warn>) this.warns.stream().filter(warn -> !warn.isActive()).collect(Collectors.toList());
    }

    public ArrayList<Warn> getActifWarns()
    {
        return (ArrayList<Warn>) this.warns.stream().filter(Warn::isActive).collect(Collectors.toList());
    }



    public void addWarn(Warn warn)
    {
        this.warns.add(warn);
        this.update();
    }

    public int getActiveWarn()
    {
        int active = 0;
        for(Warn w : this.warns)
        {
            if(w.isActive())
            {
                active++;
            }
        }
        return active;
    }


    public EmbedBuilder toEmbed(EmbedBuilder embedBuilder)
    {
        super.toEmbed(embedBuilder);
        embedBuilder.addField("Banni ⚡ ?", (this.isBanned) ? "Oui" : "Non", true);
        embedBuilder.addField("Avertissements 🔒", String.valueOf(this.getAllWarn()), true);
        embedBuilder.addField("Avertissements actif 🔓", String.valueOf(this.getActiveWarn()), true);
        embedBuilder.addBlankField(true);
        return embedBuilder;
    }


    public int getAllWarn()
    {
        return this.warns.size();
    }

    public static Profile load(String id)  {
        Path path = Path.of("users",id + ".json");
        try {
            return (Profile) Json.load(path.toString(), Profile.class);
        }
        catch(FileNotFoundException e)
        {
            Profile user = new Profile(id);
            user.setPath(path.toString());
            return user;
        }

    }








}
