package zightch.antiAddiction.System.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Timing extends BukkitRunnable {
    private final Plugin plugin;
    private boolean warning = false;

    public Timing(Plugin plugin) {
        this.plugin = plugin;
        this.start(2);
    }

    public Timing(Plugin plugin, long time) {
        this.plugin = plugin;
        this.start(time * 20L);
    }

    @Override
    public void run() {
        long time = Integer.parseInt(new SimpleDateFormat("HHmm").format(new Date()));
        if (time >= 2250 && !warning) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!player.isOp()) {
                    player.sendMessage("��6����ʱ���� ��522:50\n��6�㻹ʣ�� ��510���� ��6����Ϸʱ��");
                }
            }
            warning = true;
        }
        if (time >= 2300) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!player.isOp()) {
                    player.kickPlayer("��6����ʱ���� ��523:00\n��4�����ټ�����Ϸ��\n��a�����Ϣ��!");
                }
            }
            warning = false;
        }
    }

    public void start(long time) {
        runTaskTimer(plugin, 0, time);
    }
}
