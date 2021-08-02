package zightch.antiAddiction.Player.Rest.TimerTask;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import zightch.antiAddiction.Player.Rest.RestPlayer;
import zightch.antiAddiction.SystemData;

public class TimeoutDetection extends BukkitRunnable {
    private final Plugin plugin;
    private final RestPlayer player;

    public TimeoutDetection(RestPlayer player, Plugin plugin) {
        this.player = player;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        cancel();
        for (int i = 0, length = SystemData.apl.size(); i < length; i++) {
            if (player.getPlayerName().equals(SystemData.apl.get(i).getPlayerName())) {
                SystemData.apl.remove(i);
                i--;
                length--;
            }
        }
        for (int i = 0, length = SystemData.rpl.size(); i < length; i++) {
            if (SystemData.rpl.get(i).getPlayerName().equals(player.getPlayerName())) {
                SystemData.rpl.remove(i);
                length--;
                i--;
            }
        }
    }

    public void start(long time) {
        runTaskTimer(plugin, 20L * time, 0);
    }
}
