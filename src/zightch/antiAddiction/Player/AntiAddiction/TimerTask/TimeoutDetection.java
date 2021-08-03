package zightch.antiAddiction.Player.AntiAddiction.TimerTask;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import zightch.antiAddiction.Player.AntiAddiction.AntiAddictionPlayer;
import zightch.antiAddiction.Player.Rest.RestPlayer;
import zightch.antiAddiction.System.SystemData;

public class TimeoutDetection extends BukkitRunnable {
    private final AntiAddictionPlayer aap;
    private final Plugin plugin;
    private final Player player;

    public TimeoutDetection(AntiAddictionPlayer aap, Player player, Plugin plugin) {
        this.aap=aap;
        this.player = player;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        cancel();
        try {
            if (player != null)
                if (!player.isOp()) {
                    SystemData.rpl.add(new RestPlayer(player, plugin));
                    player.kickPlayer("§4你的上线时长已达 §53个小时\n§3请休息 §51小时候 §3后在继续吧");
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(long time) {
        runTaskTimer(plugin, 20L * time, 0);
    }
}
