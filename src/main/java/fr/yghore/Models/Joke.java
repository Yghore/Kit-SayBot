package fr.yghore.Models;

import fr.yghore.dyglib.Data.Salvageable;

import java.util.ArrayList;
import java.util.HashMap;

public class Joke extends Salvageable
{

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

    public void addJoke(String desc)
    {
        this.jokes.add(desc);
    }

    public void setJokes(ArrayList<String> jokes) {
        this.jokes = jokes;
    }
}
