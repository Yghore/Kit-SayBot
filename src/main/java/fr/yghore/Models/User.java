package fr.yghore.Models;

import fr.yghore.dyglib.Data.Json;
import fr.yghore.dyglib.Data.Salvageable;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class User extends Salvageable
{


    private transient String path;

    private String memberId;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;
    private ArrayList<Warn> warns;

    public User(String memberId)
    {
        this.memberId = memberId;
        this.created_date = LocalDateTime.now();
        this.updated_date = LocalDateTime.now();
        this.warns = new ArrayList<>();
    }



    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public LocalDateTime getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(LocalDateTime updated_date) {
        this.updated_date = updated_date;
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

    public int getAllWarn()
    {
        return this.warns.size();
    }


    private User setPath(String path){this.path = path; return this;}




    public static User load(String path, String memberId)
    {
        try
        {
            return ((User) new Json(User.class, path).load()).setPath(path);

        }
        catch(FileNotFoundException e)
        {
            return new User(memberId).setPath(path);
        }
    }

    public void save()
    {
        new Json(User.class, this.path).save(this);
    }



}
