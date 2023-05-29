package cn.lemoncraft.bedwars.Game.Arena.Menu;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Game.Arena.GameStart;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Arrays;

public class QuickMessage implements Listener {
    public static void openmain(Player player) {
        Inventory inv = Bukkit.createInventory(null, 36, "快速交流");
        Team team = GameStart.getcoreboard().getEntryTeam(player.getName());
        ItemStack hello = new ItemStack(Material.BOOK);
        ItemMeta hellometa = hello.getItemMeta();
        hellometa.setDisplayName("&a你好 ( ﾟ◡ﾟ)/!");
        hellometa.setLore(new ArrayList<>(Arrays.asList("", "&e点击发送!")));
        hello.setItemMeta(hellometa);
        inv.setItem(10, hello);
        ItemStack gotohome = new ItemStack(Material.BOOK);
        ItemMeta gotohomemeta = hello.getItemMeta();
        gotohomemeta.setDisplayName("&a我要回到基地了");
        gotohomemeta.setLore(new ArrayList<>(Arrays.asList("", "&e点击发送!")));
        gotohome.setItemMeta(gotohomemeta);
        inv.setItem(11, gotohome);
        ItemStack inc = new ItemStack(Material.IRON_FENCE);
        ItemMeta incmeta = hello.getItemMeta();
        incmeta.setDisplayName("&a&a我在防守!");
        incmeta.setLore(new ArrayList<>(Arrays.asList("", "&e点击发送!")));
        inc.setItemMeta(incmeta);
        inv.setItem(12, inc);
        player.openInventory(inv);
    }
    @EventHandler
    public void click(InventoryClickEvent event) {
        try {
            Player player = (Player) event.getWhoClicked();
            Team team = GameStart.getcoreboard().getEntryTeam(player.getName());
            if (event.getInventory().getTitle().equalsIgnoreCase("快速交流")) {
                event.setCancelled(true);
                if (!event.getCurrentItem().getItemMeta().getDisplayName().contains("一起进攻") || !event.getCurrentItem().getItemMeta().getDisplayName().contains("我正在进攻") || !event.getCurrentItem().getItemMeta().getDisplayName().contains("我正在收集") || !event.getCurrentItem().getItemMeta().getDisplayName().contains("我需要")) {
                    for (String forplayer : team.getEntries()) {
                        Player forplayerplayer = Bukkit.getPlayer(forplayer);
                        if (forplayerplayer != null) {
                            forplayerplayer.sendMessage(BedWars.api.getUserManager().getUser(forplayer).getCachedData().getMetaData().getPrefix()+forplayer+"&a: "+event.getCurrentItem().getItemMeta().getDisplayName());
                        }
                    }

                }
            }
        } catch (NullPointerException error) {}
    }

}
