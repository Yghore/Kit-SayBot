package fr.yghore.Models;

import fr.yghore.dyglib.Data.Json;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;

public class Data
{


    public static User getDataUsers(String memberId)
    {
        try{
            return (User) new Json(User.class, Path.of(ConfigData.getConfig().getUsersFolder(), memberId + ".json").toString()).load();

        }
        catch(FileNotFoundException e)
        {
            return new User(memberId);
        }
    }

    public static void saveDataUsers(String memberId, User data)
    {
        new Json(User.class, Path.of(ConfigData.getConfig().getUsersFolder(), memberId + ".json").toString()).save(data);

    }


}
