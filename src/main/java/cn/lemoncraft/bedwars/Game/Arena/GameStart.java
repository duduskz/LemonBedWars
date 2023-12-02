package cn.lemoncraft.bedwars.Game.Arena;

import cn.hpnetwork.lemonnick.API.LemonNickAPI;
import cn.lemoncraft.bedwars.API.Event.GameStartEvent;
import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Game.Arena.Menu.Selector;
import cn.lemoncraft.bedwars.Game.Arena.SpecialMode.RushMode;
import cn.lemoncraft.bedwars.Item.Game;
import cn.lemoncraft.bedwars.Utils.*;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.text.SimpleDateFormat;
import java.util.*;

public class GameStart {
    private static final Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

    public static Scoreboard getScoreboard() {
        return scoreboard;
    }

    public static String isyou(Team team, String playername) {
        for (String name : team.getEntries()) {
            if (Objects.equals(playername, name)) {
                return "§7你";
            }
        }


        return "";
    }



    public static void start() {


        for (NPCRegistry r : CitizensAPI.getNPCRegistries()) {
            r.deregisterAll();
        }
        Plugin plugin = JavaPlugin.getPlugin(BedWars.class);
        try {
            Objective objective = Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("showplayerhealth", "health");

            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
            objective.setDisplayName("§c❤");
        } catch (IllegalArgumentException e) {
            Objective objective = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("showplayerhealth");

            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
            objective.setDisplayName("§c❤");
        }

        FileConfiguration config = plugin.getConfig();
        BedWars.state = "Play"; // 设置当前游戏状态
        MinecraftServer.getServer().setMotd("play");


        List<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers()); //获取所有在线玩家
        Collections.shuffle(playerList);


        Team spectator = scoreboard.registerNewTeam("旁观者");
        spectator.setAllowFriendlyFire(false);
        spectator.setCanSeeFriendlyInvisibles(true);
        spectator.setPrefix("§7旁观者 ");
        spectator.setSuffix("§7");
        spectator.setDisplayName("0");
        PlayerDataManage.AddQuickShopItem();
        for (Player forplayer : playerList) {

            forplayer.setFallDistance(0.0F);
            BedWars.ShopCd.put(forplayer.getName(), 0);
            BedWars.Fireballcooldown.put(forplayer, 0);

            BedWars.kill.put(forplayer.getName(), 0);
            BedWars.finalkill.put(forplayer.getName(), 0);
            BedWars.deaths.put(forplayer.getName(), 0);
            BedWars.finaldeaths.put(forplayer.getName(), 0);
            BedWars.breakbed.put(forplayer.getName(), 0);
            BedWars.shoutcd.put(forplayer.getName(), 0);
            BedWars.xp.put(forplayer.getName(), 0);
            BedWars.coins.put(forplayer.getName(), 0);
            BedWars.shears.put(forplayer.getName(), false);
            BedWars.axe.put(forplayer.getName(), 0);
            BedWars.pickaxe.put(forplayer.getName(), 0);
            forplayer.setNoDamageTicks(120);
            BedWars.playeradditem.put(forplayer.getName(), "空");

            forplayer.getInventory().clear();
            ItemStack WOOD_SWORD = new ItemStack(Material.WOOD_SWORD);
            ItemMeta itemMeta = WOOD_SWORD.getItemMeta();
            itemMeta.spigot().setUnbreakable(true);

            WOOD_SWORD.setItemMeta(itemMeta);
            forplayer.getInventory().addItem(WOOD_SWORD);
            Game item = new Game();
            forplayer.getInventory().setItem(8, item.getItem("指南针"));

            if (!Selector.selectorTeamPlayer.contains(forplayer.getName())) {
                for (Team team : CreateTeam.teamList) {
                    if (team.getEntries().size() != CreateTeam.teamPlayer) {
                        addPlayerToTeam(team, forplayer, CreateTeam.teamColor.get(team.getName()));
                        team.setDisplayName("yes");
                        String[] spawn = LocationUtil.getStringLocation(config.getString("Map."+team.getName()+".Spawn"));
                        forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        break;

                    }
                }
            } else {
                Team team = getScoreboard().getEntryTeam(forplayer.getName());
                String[] spawn = LocationUtil.getStringLocation(config.getString("Map."+team.getName()+".Spawn"));
                forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                team.setDisplayName("yes");
            }
            NameTAG.setTagPrefix(forplayer.getName(), getScoreboard().getEntryTeam(forplayer.getName()).getName(), getScoreboard().getEntryTeam(forplayer.getName()).getPrefix());
            forplayer.setPlayerListName(getScoreboard().getEntryTeam(forplayer.getName()).getPrefix() + LemonNickAPI.getPlayerNick(forplayer));
            forplayer.setDisplayName(getScoreboard().getEntryTeam(forplayer.getName()).getPrefix() + LemonNickAPI.getPlayerNick(forplayer));
            if (PlayerDataManage.getPlayerLang(forplayer).equalsIgnoreCase("zhcn")) {
                TAB.set(forplayer, "     §b§l你正在§e§l" + BedWars.serverip + "§b§l上进行游戏\n", "\n§b击杀数: §e" + BedWars.kill.get(forplayer.getName()) + " §b最终击杀数: §e" + BedWars.finalkill.get(forplayer.getName()) + " §b破坏床数: §e" + BedWars.breakbed.get(forplayer.getName()) + "\n\n     §a§lRank以及更多！§c§l请访问Store." + BedWars.serverip);
            } else {
                TAB.set(forplayer, "     §b§lYou are playing on §e§l" + BedWars.serverip + "\n", "\n§bKills: §e" + BedWars.kill.get(forplayer.getName()) + " §bFlial Kills: §e" + BedWars.finalkill.get(forplayer.getName()) + " §bDestroyed Beds: §e" + BedWars.breakbed.get(forplayer.getName()) + "\n\n     §a§lRank & More! §c§lStore." + BedWars.serverip);
            }
            ShowScoreBoard(forplayer);





            if (PlayerDataManage.getPlayerLang(forplayer).equalsIgnoreCase("zhcn")) {
                forplayer.sendMessage("§a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                forplayer.sendMessage("§f                                §l起床战争");
                forplayer.sendMessage("");
                forplayer.sendMessage("§e§l      保护你的床并破坏敌人的床。通过从资源刷新点获取铁锭，金锭");
                forplayer.sendMessage("§e§l              绿宝石和钻石来升级，使自身和队伍变得更强。");
                forplayer.sendMessage("");
                forplayer.sendMessage("§a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            } else if (PlayerDataManage.getPlayerLang(forplayer).equalsIgnoreCase("en")) {
                forplayer.sendMessage("§a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                forplayer.sendMessage("§f                                §lBED WARS");
                forplayer.sendMessage("");
                forplayer.sendMessage("§e§l    Protect your bed and destroy the enemy beds.");
                forplayer.sendMessage("§e§l      Upgrade yourself and your team by collecting");
                forplayer.sendMessage("§e§l   Iron, Gold, Emerald, and Diamond from generators");
                forplayer.sendMessage("§e§l             to access powerful upgrades.");
                forplayer.sendMessage("");
                forplayer.sendMessage("§a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                            player.sendMessage("§b+25 起床战争经验 (时长奖励)");
                            player.sendMessage("§6+10 硬币 (时长奖励)");
                        } else {
                            player.sendMessage("§b+25 BedWars XP (Time Reward)");
                            player.sendMessage("§6+10 Coins (Time Reward)");
                        }

                        PlayerDataManage.addPlayerXP(player, 25);
                        PlayerDataManage.addPlayerCoins(player, 10);
                    }
                }
            }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 5000, 5000);
        }
        new BukkitRunnable() {


            @Override
            public void run() {
                if (new Random().nextInt(2) == 1) {
                    Bukkit.broadcastMessage("§c§l如果你在游玩过程中断开连接，输入/rejoin来重新进入！");
                } else {
                    Bukkit.broadcastMessage("§c§l游戏内禁止队伍联合，输入/report来举报违规玩家！");
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 200 + new Random().nextInt(3000), new Random().nextInt(6500));


        //Scoreboard scoreboard1 = Bukkit.getScoreboardManager().getNewScoreboard();
        BedWarsListener.start();
        Generator.start();



        try {

            if (plugin.getConfig().getString("Map.SpecialMode").equalsIgnoreCase("Rush")) {
                RushMode.start();
            }
        } catch (NullPointerException ignored) {

        }

        Bukkit.getServer().getPluginManager().callEvent(new GameStartEvent(config.getString("Map.Name")));
        ItemDrop.start();
        NPCcreate.NPCstart();
        // for use to send team eliminateq message
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (Team t : CreateTeam.teamList) {
                if (!t.getDisplayName().equalsIgnoreCase("yes")) {

                    player.sendMessage("");
                    if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                        player.sendMessage("§f§l团灭 > " + t.getSuffix() + t.getName() + " §c已被淘汰！");
                    } else if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("en")) {
                        player.sendMessage("§f§lTEAM ELIMINATEQ > " + t.getSuffix() + t.getName() + " §chas been eliminated!");
                    }
                    player.sendMessage("");

                } else {
                    BedWars.sharp.put(t.getName(), false);
                    BedWars.Dragon.put(t.getName(), false);
                    BedWars.protectUpgrade.put(t.getName(), 0);
                    BedWars.HasteUpgrade.put(t.getName(), 0);
                    BedWars.Trap.put(t.getName(), new ArrayList<>());
                }
            }
        }
    }
    public static void ShowScoreBoard(Player player) {
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        FileConfiguration config = plugin.getConfig();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {

            ArrayList<String> Board = new ArrayList<>();

            Calendar calendar = Calendar.getInstance();

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
            String date = "§7" + formatter.format(calendar.getTime());  //日期
            Board.add(date + " §8m" + config.getString("Map.mini"));
            Board.add("§b ");
            int fen = BedWars.Listenertime / 60;
            int miao = fen * 60;
            miao = BedWars.Listenertime - miao;
            String fenstr = String.valueOf(fen);
            String miaostr = String.valueOf(miao);
            if (fen == 9) {
                fenstr = "09";
            }
            if (fen == 8) {
                fenstr = "08";
            }
            if (fen == 7) {
                fenstr = "07";
            }
            if (fen == 6) {
                fenstr = "06";
            }
            if (fen == 5) {
                fenstr = "05";
            }
            if (fen == 4) {
                fenstr = "04";
            }
            if (fen == 3) {
                fenstr = "03";
            }
            if (fen == 2) {
                fenstr = "02";
            }
            if (fen == 1) {
                fenstr = "01";
            }
            if (fen == 0) {
                fenstr = "00";
            }
            if (miao == 9) {
                miaostr = "09";
            }
            if (miao == 8) {
                miaostr = "08";
            }
            if (miao == 7) {
                miaostr = "07";
            }
            if (miao == 6) {
                miaostr = "06";
            }
            if (miao == 5) {
                miaostr = "05";
            }
            if (miao == 4) {
                miaostr = "04";
            }
            if (miao == 3) {
                miaostr = "03";
            }
            if (miao == 2) {
                miaostr = "02";
            }
            if (miao == 1) {
                miaostr = "01";
            }
            if (miao == 0) {
                miaostr = "00";
            }

            Board.add("§f" + BedWars.Listenername + " - §a" + fenstr + ":" + miaostr);
            Board.add("§1 ");
            for (Team team : CreateTeam.teamList) {
                if (Objects.equals(team.getDisplayName(), "yes")) {
                    Board.add(team.getPrefix() + "§f" + team.getName() + ": §a✔ " + isyou(team, player.getName()));
                } else if (Objects.equals(team.getDisplayName(), "0")) {
                    Board.add(team.getPrefix() + "§f" + team.getName() + ": §c✖" + " " + isyou(team, player.getName()));
                } else {
                    Board.add(team.getPrefix() + "§f" + team.getName() + ": §a" + team.getDisplayName() + " " + isyou(team, player.getName()));
                }
            }

            if (Objects.equals(config.getString("Map.ModeType"), "4v4v4v4") && Objects.equals(config.getString("Map.ModeType"), "3v3v3v3")) {//判断是否为4v4v4v4
                Board.add("§a ");

                if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("zhcn")) {

                    Board.add("§f击杀数: §a" + BedWars.kill.get(player.getName()));
                    Board.add("§f最终击杀数: §a" + BedWars.finalkill.get(player.getName()));
                    Board.add("§f破坏床数: §a" + BedWars.breakbed.get(player.getName()));
                } else if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("en")){
                    Board.add("§fKills: §a" + BedWars.kill.get(player.getName()));
                    Board.add("§fFinal Kills: §a" + BedWars.finalkill.get(player.getName()));
                    Board.add("§fBeds Broken: §a" + BedWars.breakbed.get(player.getName()));
                }
            }
            if (Objects.equals(config.getString("Map.ModeType"), "4v4")) {
                Board.add("§a ");

                if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    Board.add("§f击杀数: §a" + BedWars.kill.get(player.getName()));
                    Board.add("§f最终击杀数: §a" + BedWars.finalkill.get(player.getName()));
                } else if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("en")) {
                    Board.add("§fKills: §a" + BedWars.kill.get(player.getName()));
                    Board.add("§fFinal Kills: §a" + BedWars.finalkill.get(player.getName()));

                }
            }
            ScoreboardManager sm = null;
            if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("en")) {
                Board.replaceAll(s -> s.replace("红队", "Red").replace("红 ", "R ").replace("蓝队", "Blue").replace("蓝 ", "B ").replace("绿队", "Green").replace("绿 ", "G ").replace("黄队", "Yellow").replace("黄 ", "Y ").replace("青队", "Aura").replace("青 ", "A ").replace("白队", "White").replace("白 ", "W ").replace("粉 ", "P ").replace("粉队", "Pink").replace("粉 ", "P ").replace("灰队", "Gray").replace("灰 ", "G ").replace("你", "YOU").replace("钻石生成点II级 -", "Diamond II in").replace("钻石生成点III级 -", "Diamond III in").replace("绿宝石生成点II级 -", "Emerald II in").replace("绿宝石生成点III级 -", "Emerald III in").replace("床自毁 -", "Bed Gone in").replace("绝杀模式 -", "Sudden Death in").replace("游戏结束 -", "Game End in"));
                Board.add("§f ");
                Board.add("§e" + BedWars.serverip);

                sm = new ScoreboardManager(plugin, "§e§lBED WARS",Board.toArray(new String[0]));
            } else if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                Board.add("§f ");
                Board.add("§e" + BedWars.serverip);
                sm = new ScoreboardManager(plugin, "§e§l起床战争",Board.toArray(new String[0]));
            }
            assert sm != null;
            sm.display(player);


        }, 0, 15L);

    }

    public static void addPlayerToTeam(Team tm, Player forplayer, Color clr){
        tm.addEntry(forplayer.getName());
        ItemStack s = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
        lch.setColor(clr);
        lch.spigot().setUnbreakable(true);
        s.setItemMeta(lch);
        ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
        lcc.setColor(clr);
        lcc.spigot().setUnbreakable(true);
        d.setItemMeta(lcc);
        ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
        lcl.setColor(clr);
        lcl.spigot().setUnbreakable(true);
        f.setItemMeta(lcl);
        ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
        lcb.spigot().setUnbreakable(true);
        lcb.setColor(clr);
        g.setItemMeta(lcb);
        forplayer.getInventory().setBoots(g);
        forplayer.getInventory().setLeggings(f);
        forplayer.getInventory().setChestplate(d);
        forplayer.getInventory().setHelmet(s);

    }
}