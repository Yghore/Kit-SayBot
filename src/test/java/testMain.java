import fr.yghore.Utils.TimeFormat;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class testMain {

    public static void main(String[] args) {
        Duration duration = Duration.parse("P160DT3H4M");
        System.out.println(duration.toString());

        Duration dur = TimeFormat.parse("60d30h20m10s");
        System.out.println(dur);


    }


}
