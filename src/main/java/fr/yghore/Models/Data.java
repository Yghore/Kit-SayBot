package fr.yghore.Models;

import fr.yghore.dyglib.Data.Json;
import fr.yghore.dyglib.Data.Salvageable;
import fr.yghore.dyglib.Logger;

import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;
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

    public static Salvageable getData(String path, Class<? extends Salvageable> cl,Object... paramConst) {
        return getData(path, cl, 0, paramConst);
    }

    public static Salvageable getData(String path, Class<? extends Salvageable> cl, int constructor,Object... paramConst) {
        try{
            return new Json(cl, Path.of(path).toString()).load();

        }
        catch(FileNotFoundException e)
        {
            try
            {
                return (Salvageable) cl.getDeclaredConstructors()[constructor].newInstance(paramConst);

            } catch (InvocationTargetException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger().sendError("Une erreur est survenue lors de la création d'un nouveau fichier de donnée... (mauvais argument passé, etc...)");
                ex.printStackTrace();
            }
        }
        return new Salvageable();
    }

    public static void saveData(String path, Salvageable data)
    {
        new Json(User.class, Path.of(path).toString()).save(data);

    }



}
