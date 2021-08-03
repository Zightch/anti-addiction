package zightch.antiAddiction.Player.AntiAddiction;

import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import zightch.antiAddiction.Player.AntiAddiction.TimerTask.TimeoutDetection;
import zightch.antiAddiction.System.SystemData;

public class AntiAddictionPlayer {
    private long onlineTime;
    private Date loginTime;
    private Date logoutTime;
    private final String playerName;
    private final TimeoutDetection ts;

    public AntiAddictionPlayer(Player player, Plugin plugin) {
        playerName = player.getName();
        this.loginTime = new Date();
        ts = new TimeoutDetection(player, plugin);
        ts.start(SystemData.maximumOnlineTime);
    }

    public AntiAddictionPlayer(Player player, Plugin plugin, long time) {
        playerName = player.getName();
        this.loginTime = new Date();
        ts = new TimeoutDetection(player, plugin);
        if (time == 0)
            ts.start(SystemData.maximumOnlineTime);
        else ts.start(time);
    }

    public long getOnlineTime() {
        return onlineTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void start(long time) {
        this.loginTime = new Date();
        ts.start(time);
    }

    public void cancel() {
        this.logoutTime = new Date();
        onlineTime = (logoutTime.getTime() - loginTime.getTime()) / 1000;
        ts.cancel();
    }

    public String getPlayerName() {
        return playerName;
    }

    protected void finalize() {
        ts.cancel();
    }
}
