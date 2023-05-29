package cn.lemoncraft.bedwars.Lobby;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Quest implements Listener {

    Plugin plugin = BedWars.getPlugin(BedWars.class);

    String url = "jdbc:mysql://" + plugin.getConfig().getString("MySQL.url") + ":" + plugin.getConfig().getString("MySQL.port") + "/" + plugin.getConfig().getString("MySQL.db");
    String user = plugin.getConfig().getString("MySQL.username");
    String password = plugin.getConfig().getString("MySQL.password");

    @EventHandler
    public void clickinv(InventoryClickEvent e) {
        try {

            if (e.getInventory().getTitle().equalsIgnoreCase("起床战争任务")) {
                e.setCancelled(true);
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains("每日首胜")) {
                    ResultSet dayrs;
                    Player player = (Player) e.getWhoClicked();
                    Statement statement = PlayerDataManage.BedWarsdataSource.getConnection().createStatement();
                    String sql = "SELECT * FROM player_day_task WHERE uuid = '" + player.getUniqueId().toString() + "'";
                    dayrs = statement.executeQuery(sql);
                    if (dayrs.next()) {
                        if (dayrs.getInt("DayWin") == 2) {
                            player.sendMessage("§c你现在还不能开启这个任务!");
                            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                        } else {
                            if (dayrs.getInt("DayWin") == 1) {
                                player.sendMessage("§c你已经开启了§6每日任务:每日首胜§c任务!");
                                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                            } else {
                                player.sendMessage("§a你接受了 §6每日任务:每日首胜§a 任务!");
                                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 24);
                                sql = "UPDATE player_day_task" + " SET DayWin = 1 WHERE uuid = '" + player.getUniqueId().toString() + "'";
                                statement.executeUpdate(sql);
                                ItemStack daywin = getdaywin(dayrs);
                                player.getOpenInventory().setItem(11, daywin);
                            }

                        }
                    } else {
                        player.sendMessage("§a你接受了 §6每日任务:每日首胜§a 任务!");
                        player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 24);
                        sql = "UPDATE player_day_task" + " SET DayWin = 1 WHERE uuid = '" + player.getUniqueId().toString() + "'";
                        statement.executeUpdate(sql);
                        ItemStack daywin = getdaywin(dayrs);
                        player.getInventory().setItem(11, daywin);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
        }
    }

    @EventHandler
    public void openinv(NPCRightClickEvent e) {
        if (e.getNPC().getName().equalsIgnoreCase("§b")) {

            Player player = e.getClicker();
            ResultSet dayrs;

            try {
                Statement statement = PlayerDataManage.BedWarsdataSource.getConnection().createStatement();
                String sql = "SELECT * FROM player_day_task WHERE uuid = '" + player.getUniqueId().toString() + "'";
                dayrs = statement.executeQuery(sql);
                if (!dayrs.next()) {
                    if (player.hasPermission("" + BedWars.servername + ".MVP+") || player.hasPermission("" + BedWars.servername + ".MVP++") || player.hasPermission("" + BedWars.servername + ".admin") || player.hasPermission("" + BedWars.servername + ".owner") || player.hasPermission("" + BedWars.servername + ".bilibili")) {
                        sql = "INSERT INTO player_day_task (uuid, DayWin, DoublePlay) VALUES ('" + player.getUniqueId().toString() + "', 1, 1)";
                    } else {
                        sql = "INSERT INTO player_day_task (uuid, DayWin, DoublePlay) VALUES ('" + player.getUniqueId().toString() + "', 0, 0)";
                    }
                    statement.executeUpdate(sql);
                }
                Inventory inv = Bukkit.createInventory(null, 45, "起床战争任务");
                ItemStack bed = new ItemStack(Material.BED);
                ItemMeta bedmeta = bed.getItemMeta();
                bedmeta.setDisplayName("§a起床战争任务");
                ArrayList<String> lore = new ArrayList<>();
                lore.add("§7查看所有在起床战争中可用的任务和挑战 §c(制作中)");
                bedmeta.setLore(lore);
                bed.setItemMeta(bedmeta);
                inv.setItem(4, bed);
                ItemStack daywin = getdaywin(dayrs);
                inv.setItem(11, daywin);
                e.getClicker().openInventory(inv);
            } catch (SQLException error) {
                error.printStackTrace();
            }

        }
    }

    public ItemStack getdaywin(ResultSet dayrs) {
        ItemStack daywin = new ItemStack(Material.PAPER);
        ItemMeta daywinmeta = daywin.getItemMeta();
        daywinmeta.setDisplayName("§a每日任务:每日首胜");
        ArrayList<String> daywinlore = new ArrayList<>();
        daywinlore.add("§7赢得一局起床战争");
        daywinlore.add("");
        daywinlore.add("§7奖励:");
        daywinlore.add("§8+§33850 §7" + BedWars.servername + "经验");
        daywinlore.add("§8+§b250 §7起床战争经验");
        daywinlore.add("");
        daywinlore.add("§8§o每日任务每天限领一次");
        daywinlore.add("");
        try {
            if (!dayrs.next()) {
                if (dayrs.getInt("DayWin") == 2) {
                    daywin.setType(Material.MAP);
                    daywinlore.add("§c你需要等待 " + getDateTime().toString().replace("0天", "").replace("0小时", "").replace("0分", ""));
                } else {
                    if (dayrs.getInt("DayWin") == 1) {
                        //daywin.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
                        daywinmeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                        daywinlore.add("§a你已经接受了这个任务!");
                    } else {
                        daywinlore.add("§e点击接受");
                    }

                }
            } else {
                daywinlore.add("§e点击接受");
            }

        } catch (SQLException e) {
        }

        daywinmeta.setLore(daywinlore);
        daywin.setItemMeta(daywinmeta);
        return daywin;
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
