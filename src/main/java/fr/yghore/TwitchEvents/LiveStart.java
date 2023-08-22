package fr.yghore.TwitchEvents;

import com.github.philippheuer.events4j.core.domain.Event;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import fr.yghore.Main;
import fr.yghore.Models.ConfigData;
import fr.yghore.Utils.Const;

public class LiveStart {


    public LiveStart(ChannelGoLiveEvent event)
    {

        Main.LOGGER.sendInfo("Le streameur " + event.getChannel().getName() + " entre en live !");
        Main.guild.getTextChannelById(ConfigData.getConfig().getAnnounceTwitchChannel()).sendMessageEmbeds(Const.EMBED_TWITCH_ANNOUNCE).queue();

    }



}
