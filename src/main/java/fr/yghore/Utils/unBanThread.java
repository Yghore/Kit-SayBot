package fr.yghore.Utils;

import fr.yghore.Models.Bans;

import java.util.TimerTask;

public class unBanThread extends TimerTask
{


    @Override
    public void run() {
        Bans bans = Bans.load();
        bans.removeExpiredBan();
        bans.save();
    }
}
