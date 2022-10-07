import fr.yghore.Models.Data;
import fr.yghore.Models.Joke;
import fr.yghore.Models.User;

public class testMain {

    public static void main(String[] args) {


        Joke test = (Joke) Data.getData("joke.json", Joke.class);
        test.addJoke("Je suis une blague");
        test.addJoke("Je suis une autre blague");
        test.addJoke("Comment va bien le monde ?");
        Data.saveData("joke.json", test);

        Joke joke = (Joke) Data.getData("joke.json", Joke.class);

        System.out.println(joke.getJoke(2));
        System.out.println(joke.getJoke(3));


    }


}
