package cn.lemonnetwork.lemonbedwars.Lobby;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Quest implements Listener {


    @EventHandler
    public void openinv(NPCRightClickEvent e) {
        if (e.getNPC().getName().equalsIgnoreCase("§b")) {

        }
    }
    public static String getYesterDayStartTimeStamp() {
        SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND,0); //这是将【秒】设置为0
        calendar.set(Calendar.MINUTE,0); //这是将【分】设置为0
        calendar.set(Calendar.HOUR_OF_DAY,0); //这是将【时】设置为0
        calendar.add(Calendar.DATE,-1); //当前日期加一
        String yesterday  = sdfYMD.format(calendar.getTime()); //获取昨天的时间 如2021-02-25 00:00:00
        return yesterday;
    }
    public static Date getDateTime()
    {
        java.util.Date dateTime = null;
        String yesterDayStartTimeStamp = getYesterDayStartTimeStamp();
        SimpleDateFormat formatter = new SimpleDateFormat("d天HH时mm分ss秒");
        try
        {
            dateTime = formatter.parse(yesterDayStartTimeStamp);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        //Sun May 09 00:00:00 CST 2021
        return dateTime;
    }
}
