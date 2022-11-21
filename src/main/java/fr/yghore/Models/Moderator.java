package fr.yghore.Models;

import fr.yghore.dyglib.Data.Json;
import fr.yghore.dyglib.Data.Salvageable;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Moderator extends User implements Salvageable {


    private ArrayList<Ban> banneds;

    public Moderator(String memberId) {
        super(memberId);
        this.banneds = new ArrayList<>();
    }

    public void addBan(Ban b)
    {
        this.banneds.add(b);
    }


    public static Moderator load(String id)  {
        Path path = Path.of("users", "moderator", id + ".json");

        try {
            return (Moderator) Json.load(Path.of("users_", id + "_moderator.json").toString(), Moderator.class);
        }
        catch(FileNotFoundException e)
        {
            return (Moderator) new Moderator(id).setPath(path.toString());
        }

    }


}
