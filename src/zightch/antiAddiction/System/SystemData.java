package zightch.antiAddiction.System;

import zightch.antiAddiction.Player.AntiAddiction.AntiAddictionPlayer;
import zightch.antiAddiction.Player.Rest.RestPlayer;

import java.util.ArrayList;
import java.util.List;

public class SystemData {
    public static long maximumOnlineTime = 60 * 60 * 3;//�����ʱ��(��λ:��)
    public static long half_time = 60 * 60;//��ұ�ǿ�������Ϸ����Ҫ��Ϣ��ʱ��(��λ:��)
    public static long restAllowance = 60 * 30;//����Լ��˳���Ϸ��,���������ʱ������Ҫ��ʱ��(��λ:��)
    public static List<RestPlayer> rpl = new ArrayList<RestPlayer>();
    public static List<AntiAddictionPlayer> apl = new ArrayList<AntiAddictionPlayer>();
}
