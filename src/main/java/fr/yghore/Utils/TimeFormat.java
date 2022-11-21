package fr.yghore.Utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class TimeFormat
{


    public enum DiscordFormat {
        SHORT_TIME("t"),
        LONG_TIME("T"),
        SHORT_DATE("d"),
        LONG_DATE("D"),
        SHORT_DATE_TIME("f"),
        LONG_DATE_TIME("F"),
        RELATIVE("R");

        private String format;

        public String getFormat(){return this.format;}
        private DiscordFormat(String format)
        {
            this.format = format;
        }
    }

   // 6d3h4m10s -> P6DT3H4M
    // FOREVER

    public static Duration parse(String time)
    {
        if(time.equalsIgnoreCase("FOREVER")){return ChronoUnit.CENTURIES.getDuration();}
        int days = 0;
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        for (String s : time.split("(?<=[A-z])")) {
            int parsedS = Integer.parseInt(s.replaceAll("[A-z]", ""));
            if(s.endsWith("mo")){ days += (parsedS * 30);}
            if(s.endsWith("d")){ days += parsedS;}
            if(s.endsWith("h")){ hours = parsedS;}
            if(s.endsWith("m")){ minutes = parsedS;}
            if(s.endsWith("s")){ seconds = parsedS;}
        }

        String parsed = "P" + days + "DT" + hours + "H" + minutes + "M" + seconds + "S";
        return Duration.parse(parsed);
    }

    public static long LocalDateTimeToEpochSecond(LocalDateTime localDateTime)
    {
        return localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    public static String LocalDateTimeToDiscordFormatted(LocalDateTime localDateTime, DiscordFormat discordFormat)
    {
        return "<t:" + LocalDateTimeToEpochSecond(localDateTime) + ":" + discordFormat.getFormat() + ">";
    }

    public static String LocalDateTimeToDiscordFormatted(long epoch, DiscordFormat discordFormat)
    {
        return "<t:" + epoch + ":" + discordFormat.getFormat() + ">";
    }

}
