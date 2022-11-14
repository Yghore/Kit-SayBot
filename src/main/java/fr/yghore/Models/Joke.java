package fr.yghore.Models;

import fr.yghore.dyglib.Data.Json;
import fr.yghore.dyglib.Data.Salvageable;

import java.util.ArrayList;
import java.util.Random;

public class Joke extends Json implements Salvageable
{


    public ArrayList<String> jokes;



    public ArrayList<String> getJokes() {
        return jokes;
    }


    public Joke(String path) {
        super(path);
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


    


}
