import fr.yghore.Models.Data;
import fr.yghore.Models.Joke;
import fr.yghore.Models.User;
import fr.yghore.dyglib.Logger;

public class testMain {

    public static void main(String[] args) {


        User user = User.load("users/" + "test.json", "5565456456");

        Logger.getLogger().sendDebug("TEST : " + user.getMemberId());

        user.save();


    }


}
