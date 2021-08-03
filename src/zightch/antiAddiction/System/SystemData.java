package zightch.antiAddiction.System;

import zightch.antiAddiction.Player.AntiAddiction.AntiAddictionPlayer;
import zightch.antiAddiction.Player.Rest.RestPlayer;

import java.util.ArrayList;
import java.util.List;

public class SystemData {
    public static long maximumOnlineTime = 60 * 60 * 3;//最长在线时间(单位:秒)
    public static long half_time = 60 * 60;//玩家被强制请出游戏后需要休息的时间(单位:秒)
    public static long restAllowance = 60 * 30;//玩家自己退出游戏后,重置最长在线时间所需要的时间(单位:秒)
    public static List<RestPlayer> rpl = new ArrayList<RestPlayer>();
    public static List<AntiAddictionPlayer> apl = new ArrayList<AntiAddictionPlayer>();
}
