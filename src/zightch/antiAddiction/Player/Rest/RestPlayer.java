package zightch.antiAddiction.Player.Rest;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import zightch.antiAddiction.Player.Rest.TimerTask.TimeoutDetection;
import zightch.antiAddiction.SystemData;

public class RestPlayer {
    private String playerName;
    private final TimeoutDetection ts;

    public RestPlayer(Player player, Plugin plugin) {
        playerName = player.getName();
        ts = new TimeoutDetection(this, plugin);
        ts.start(SystemData.half_time);
    }

    public String getPlayerName() {
        return playerName;
    }

    protected void finalize() {
        ts.cancel();
    }
}
