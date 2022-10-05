package fr.yghore.Utils;

import java.time.Duration;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

public class TimeFormat
{

   // 6d3h4m10s -> P6DT3H4M
    // FOREVER

    public static Duration parse(String time)
    {
        if(time.equalsIgnoreCase("FOREVER")){return ChronoUnit.FOREVER.getDuration();}
        int days = 0;
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        for (String s : time.split("(?<=[A-z])")) {
            int parsedS = Integer.parseInt(s.replaceAll("[A-z]", ""));
            if(s.endsWith("mo")){ days += parsedS;}
            if(s.endsWith("d")){ days += parsedS;}
            if(s.endsWith("h")){ hours = parsedS;}
            if(s.endsWith("m")){ minutes = parsedS;}
            if(s.endsWith("s")){ seconds = parsedS;}
        }

        String parsed = "P" + days + "DT" + hours + "H" + minutes + "M" + seconds + "S";
        return Duration.parse(parsed);
    }

}
