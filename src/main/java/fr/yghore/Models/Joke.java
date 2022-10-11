package fr.yghore.Models;

import fr.yghore.dyglib.Data.Json;
import fr.yghore.dyglib.Data.Salvageable;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Joke extends Salvageable
{


    private transient String path;

    public ArrayList<String> jokes;

    public ArrayList<String> getJokes() {
        return jokes;
    }


    public Joke() {
        this.jokes = new ArrayList<>();
    }

    public String getJoke(int i)
    {
        return this.jokes.get(i);
    }

    public String getRandomJoke()
    {
        return this.jokes.get(new Random().nextInt(this.jokes.size()));
    }

    public void addJoke(String desc)
    {
        this.jokes.add(desc);
    }

    public void setJokes(ArrayList<String> jokes) {
        this.jokes = jokes;
    }

    public static Joke load(String path)
    {
        try
        {
            return ((Joke) new Json(Joke.class, path).load()).setPath(path);

        }
        catch(FileNotFoundException e)
        {
            return new Joke().setPath(path);
        }
    }

    private Joke setPath(String path) {
        this.path = path;
        return this;
    }

    public void save()
    {
        new Json(User.class, this.path).save(this);
    }

}
