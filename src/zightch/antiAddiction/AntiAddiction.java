package zightch.antiAddiction;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import zightch.antiAddiction.Player.AntiAddiction.AntiAddictionPlayer;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.bukkit.event.player.PlayerLoginEvent.Result.KICK_OTHER;

public class AntiAddiction
        extends JavaPlugin
        implements Listener {
    @Override
    public void onLoad() {
        getLogger().info("防沉迷插件已加载");
    }


    @Override
    public void onEnable() {
        getLogger().info("防沉迷插件已启动");
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        SystemData.apl.clear();
        SystemData.rpl.clear();
        getLogger().info("防沉迷插件已卸载");
    }

    @EventHandler
    public void Join(PlayerJoinEvent event) {
        for (int i = 0, length = SystemData.apl.size(); i < length; i++) {
            if (event.getPlayer().getName().equals(SystemData.apl.get(i).getPlayerName())) {
                long timeDifference = ((new Date()).getTime() - SystemData.apl.get(i).getLogoutTime().getTime()) / 1000;
                if (timeDifference > SystemData.restAllowance) {
                    SystemData.apl.remove(i);
                    i--;
                    length--;
                } else {
                    SystemData.apl.add(new AntiAddictionPlayer(event.getPlayer(), this,SystemData.maximumOnlineTime - SystemData.apl.get(i).getOnlineTime() + timeDifference));
                    SystemData.apl.remove(i);
                    return;
                }
            }
        }
        SystemData.apl.add(new AntiAddictionPlayer(event.getPlayer(), this));
    }

    @EventHandler
    public void Login(PlayerLoginEvent event) {
        SimpleDateFormat df = new SimpleDateFormat("HH");
        int hour = 0;
        try {
            hour = Integer.parseInt(df.format(new Date()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if ((hour < 7 | hour > 22) & !event.getPlayer().isOp()) {
            event.disallow(KICK_OTHER, "§4现在的时间段不允许你登录游戏\n§5你可以在 §27:00 - 22:00 §5时间段正常登陆游戏");
        }
        for (int i = 0, length = SystemData.rpl.size(); i < length; i++) {
            if (SystemData.rpl.get(i).getPlayerName().equals(event.getPlayer().getName())) {
                event.disallow(KICK_OTHER, "§4你还没有休息够 §51个小时 §4哦");
            }
        }
    }

    @EventHandler
    public void quit(PlayerQuitEvent event) {
        for (int i = 0, length = SystemData.apl.size(); i < length; i++) {
            if (event.getPlayer().getName().equals(SystemData.apl.get(i).getPlayerName())) {
                SystemData.apl.get(i).cancel();
            }
        }
    }
}
