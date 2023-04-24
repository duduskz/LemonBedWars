package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Item.Game;
import cn.lemoncraft.bedwars.Utils.NameTAG;
import cn.lemoncraft.bedwars.Utils.TAB;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import static cn.lemoncraft.bedwars.Game.Arena.GameStart.isyou;
import static cn.lemoncraft.bedwars.Utils.LocationUtil.getStringLocation;

public class PlayerJoin implements Listener {
    Plugin plugin = BedWars.getPlugin(BedWars.class);
    @EventHandler
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();
        if (Objects.equals(config.getString("BungeeMode"), "Game")) {
            BedWars.backlobby.put(event.getPlayer().getName(), false);
            if (Objects.equals(BedWars.state, "Play")) {
                NameTAG.setTagPrefix(player.getName(), GameStart.getcoreboard().getEntryTeam(player.getName()).getName(), GameStart.getcoreboard().getEntryTeam(player.getName()).getPrefix());
                if (GameStart.getcoreboard().getEntryTeam(player.getName()) == null || Objects.equals(GameStart.getcoreboard().getEntryTeam(player.getName()).getName(), "旁观者")) {
                    TAB.set(player, "     §b§l你正在§e§lLemonCraft.cn§b§l上进行游戏\n", "\n§b击杀数: §e0"+" §b最终击杀数: §e0"+" §b破坏床数: §e0"+"\n\n     §a§lRank以及更多！§c§l请访问Store.LemonCraft.cn");
                    player.sendMessage("§a由于这场游戏还没结束，已为你切换为旁观者");
                    new BukkitRunnable() {
                        Scoreboard scoreboard1 = Bukkit.getScoreboardManager().getNewScoreboard();
                        Objective objective = scoreboard1.registerNewObjective(player.getName(), player.getName());

                        @Override
                        public void run() {
                            ArrayList<String> Board = new ArrayList<>();

                            try {
                                objective.unregister();
                                objective = scoreboard1.registerNewObjective(player.getName(), player.getName());
                            } catch (NullPointerException n) {

                            }

                            objective.setDisplaySlot(DisplaySlot.SIDEBAR);



                            Calendar calendar = Calendar.getInstance();

                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
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
                            Team red = GameStart.getcoreboard().getTeam("红队");
                            Team blue = GameStart.getcoreboard().getTeam("蓝队");
                            if (Objects.equals(red.getDisplayName(), "yes")) {
                                Board.add(red.getPrefix() + "§f" + red.getName() + ": §a✔ ");
                            } else if (Objects.equals(red.getDisplayName(), "0")) {
                                Board.add(red.getPrefix() + "§f" + red.getName() + ": §c✖" );
                            } else {
                                Board.add(red.getPrefix() + "§f" + red.getName() + ": §a" + red.getDisplayName() );
                            }
                            if (Objects.equals(blue.getDisplayName(), "yes")) {
                                Board.add(blue.getPrefix() + "§f" + blue.getName() + ": §a✔ ");
                            } else if (Objects.equals(blue.getDisplayName(), "0")) {
                                Board.add(blue.getPrefix() + "§f" + blue.getName() + ": §c✖");
                            } else {
                                Board.add(blue.getPrefix() + "§f" + blue.getName() + ": §a" + blue.getDisplayName() + " " + isyou(blue, player.getName()));
                            }
                            if (!Objects.equals(config.getString("Map.ModeType"), "4v4")) {//判断是否为4v4v4v4
                                Team green = GameStart.getcoreboard().getTeam("绿队");
                                Team yellow = GameStart.getcoreboard().getTeam("黄队");
                                if (Objects.equals(green.getDisplayName(), "yes")) {
                                    Board.add(green.getPrefix() + "§f" + green.getName() + ": §a✔ ");
                                } else if (Objects.equals(green.getDisplayName(), "0")) {
                                    Board.add(green.getPrefix() + "§f" + green.getName() + ": §c✖ ");
                                } else {
                                    Board.add(green.getPrefix() + "§f" + green.getName() + ": §a" + green.getDisplayName());
                                }
                                if (Objects.equals(yellow.getDisplayName(), "yes")) {
                                    Board.add(yellow.getPrefix() + "§f" + yellow.getName() + ": §a✔ ");
                                } else if (Objects.equals(yellow.getDisplayName(), "0")) {
                                    Board.add(yellow.getPrefix() + "§f" + yellow.getName() + ": §c✖");
                                } else {
                                    Board.add(yellow.getPrefix() + "§f" + yellow.getName() + ": §a" + yellow.getDisplayName() + " ");
                                }
                                if (!Objects.equals(config.getString("Map.ModeType"), "4v4v4v4")) {//判断是否为4v4v4v4
                                    Team arua = GameStart.getcoreboard().getTeam("青队");
                                    Team white = GameStart.getcoreboard().getTeam("白队");
                                    Team pink = GameStart.getcoreboard().getTeam("粉队");
                                    Team gray = GameStart.getcoreboard().getTeam("灰队");

                                    if (Objects.equals(arua.getDisplayName(), "yes")) {
                                        Board.add(arua.getPrefix() + "§f" + arua.getName() + ": §a✔ ");
                                    } else if (Objects.equals(arua.getDisplayName(), "0")) {
                                        Board.add(arua.getPrefix() + "§f" + arua.getName() + ": §c✖ ");
                                    } else {
                                        Board.add(arua.getPrefix() + "§f" + arua.getName() + ": §a" + arua.getDisplayName());
                                    }
                                    if (Objects.equals(white.getDisplayName(), "yes")) {
                                        Board.add(white.getPrefix() + "§f" + white.getName() + ": §a✔ ");
                                    } else if (Objects.equals(white.getDisplayName(), "0")) {
                                        Board.add(white.getPrefix() + "§f" + white.getName() + ": §c✖ ");
                                    } else {
                                        Board.add(white.getPrefix() + "§f" + white.getName() + ": §a" + white.getDisplayName());
                                    }
                                    if (Objects.equals(pink.getDisplayName(), "yes")) {
                                        Board.add(pink.getPrefix() + "§f" + pink.getName() + ": §a✔ ");
                                    } else if (Objects.equals(pink.getDisplayName(), "0")) {
                                        Board.add(pink.getPrefix() + "§f" + pink.getName() + ": §c✖ ");
                                    } else {
                                        Board.add(pink.getPrefix() + "§f" + pink.getName() + ": §a" + pink.getDisplayName());
                                    }
                                    if (Objects.equals(gray.getDisplayName(), "yes")) {
                                        Board.add(gray.getPrefix() + "§f" + gray.getName() + ": §a✔ " + isyou(gray, player.getName()));
                                    } else if (Objects.equals(gray.getDisplayName(), "0")) {
                                        Board.add(gray.getPrefix() + "§f" + gray.getName() + ": §c✖ " + isyou(gray, player.getName()));
                                    } else {
                                        Board.add(gray.getPrefix() + "§f" + gray.getName() + ": §a" + gray.getDisplayName() + " " + isyou(gray, player.getName()));
                                    }
                                }
                            }

                            if (Objects.equals(config.getString("Map.ModeType"), "4v4v4v4") || Objects.equals(config.getString("Map.ModeType"), "3v3v3v3")) {//判断是否为4v4v4v4
                                Board.add("§a ");
                                Board.add("§f击杀数: §a0");
                                Board.add("§f最终击杀数: §a0");
                                Board.add("§f破坏床数: §a0");
                            }
                            if (Objects.equals(config.getString("Map.ModeType"), "4v4")) {
                                Board.add("§f击杀数: §a0");
                                Board.add("§f最终击杀数: §a0");
                            }
                            Board.add("§f ");
                            Board.add("§eLemonCraft.cn");

                            objective.setDisplayName("§e§l起床战争");
                            for (int i = 0; i < Board.size(); i++) {
                                objective.getScore(Board.get(i)).setScore(-i + Board.size());

                            }
                            player.setScoreboard(scoreboard1);

                        }


                    }.runTaskTimer(plugin, 15L, 15L);
                    String[] spawn = getStringLocation(config.getString("Map.Spectator"));
                    player.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    BedWars.kill.put(player.getName(),0);
                    BedWars.finalkill.put(player.getName(),0);
                    BedWars.breakbed.put(player.getName(),0);
                    player.removePotionEffect(PotionEffectType.INVISIBILITY);
                    player.getInventory().clear();
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    Game item = new Game();
                    player.getInventory().setItem(4, item.getItem("旁观者设置"));
                    player.getInventory().setItem(8, item.getItem("返回大厅"));
                    player.getInventory().setItem(0, item.getItem("追踪玩家"));
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.hidePlayer(player);
                    }
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 0));
                    GameStart.getcoreboard().getTeam("旁观者").addEntry(player.getName());
                } else {
                    if (!Objects.equals(GameStart.getcoreboard().getEntryTeam(player.getName()).getDisplayName(), "yes")) {

                        player.sendMessage("§c你的床已被摧毁，已为你切换为旁观者");
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                TAB.set(player, "     §b§l你正在§e§lLemonCraft.cn§b§l上进行游戏\n", "\n§b击杀数: §e" + BedWars.kill.get(player.getName()) + " §b最终击杀数: §e" + BedWars.finalkill.get(player.getName()) + " §b破坏床数: §e" + BedWars.breakbed.get(player.getName()) + "\n\n     §a§lRank以及更多！§c§l请访问Store.LemonCraft.cn");

                            }
                        }.runTaskTimer(plugin,0L,80L);
                        new BukkitRunnable() {
                            Scoreboard scoreboard1 = Bukkit.getScoreboardManager().getNewScoreboard();
                            Objective objective = scoreboard1.registerNewObjective(player.getName(), player.getName());

                            @Override
                            public void run() {
                                ArrayList<String> Board = new ArrayList<>();

                                try {
                                    objective.unregister();
                                    objective = scoreboard1.registerNewObjective(player.getName(), player.getName());
                                } catch (NullPointerException n) {

                                }

                                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                               

                                Calendar calendar = Calendar.getInstance();

                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
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
                                Team red = GameStart.getcoreboard().getTeam("红队");
                                Team blue = GameStart.getcoreboard().getTeam("蓝队");
                                if (Objects.equals(red.getDisplayName(), "yes")) {
                                    Board.add(red.getPrefix() + "§f" + red.getName() + ": §a✔ " + isyou(red, player.getName()));
                                } else if (Objects.equals(red.getDisplayName(), "0")) {
                                    Board.add(red.getPrefix() + "§f" + red.getName() + ": §c✖" + " " + isyou(red, player.getName()));
                                } else {
                                    Board.add(red.getPrefix() + "§f" + red.getName() + ": §a" + red.getDisplayName() + " " + isyou(red, player.getName()));
                                }
                                if (Objects.equals(blue.getDisplayName(), "yes")) {
                                    Board.add(blue.getPrefix() + "§f" + blue.getName() + ": §a✔ " + isyou(blue, player.getName()));
                                } else if (Objects.equals(blue.getDisplayName(), "0")) {
                                    Board.add(blue.getPrefix() + "§f" + blue.getName() + ": §c✖" + " " + isyou(blue, player.getName()));
                                } else {
                                    Board.add(blue.getPrefix() + "§f" + blue.getName() + ": §a" + blue.getDisplayName() + " " + isyou(blue, player.getName()));
                                }
                                if (!Objects.equals(config.getString("Map.ModeType"), "4v4")) {//判断是否为4v4v4v4
                                    Team green = GameStart.getcoreboard().getTeam("绿队");
                                    Team yellow = GameStart.getcoreboard().getTeam("黄队");
                                    if (Objects.equals(green.getDisplayName(), "yes")) {
                                        Board.add(green.getPrefix() + "§f" + green.getName() + ": §a✔ " + isyou(green, player.getName()));
                                    } else if (Objects.equals(green.getDisplayName(), "0")) {
                                        Board.add(green.getPrefix() + "§f" + green.getName() + ": §c✖ " + isyou(green, player.getName()));
                                    } else {
                                        Board.add(green.getPrefix() + "§f" + green.getName() + ": §a" + green.getDisplayName() + " " + isyou(green, player.getName()));
                                    }
                                    if (Objects.equals(yellow.getDisplayName(), "yes")) {
                                        Board.add(yellow.getPrefix() + "§f" + yellow.getName() + ": §a✔ " + isyou(yellow, player.getName()));
                                    } else if (Objects.equals(yellow.getDisplayName(), "0")) {
                                        Board.add(yellow.getPrefix() + "§f" + yellow.getName() + ": §c✖" + " " + isyou(yellow, player.getName()));
                                    } else {
                                        Board.add(yellow.getPrefix() + "§f" + yellow.getName() + ": §a" + yellow.getDisplayName() + " " + isyou(yellow, player.getName()));
                                    }
                                    if (!Objects.equals(config.getString("Map.ModeType"), "4v4v4v4")) {//判断是否为4v4v4v4
                                        Team arua = GameStart.getcoreboard().getTeam("青队");
                                        Team white = GameStart.getcoreboard().getTeam("白队");
                                        Team pink = GameStart.getcoreboard().getTeam("粉队");
                                        Team gray = GameStart.getcoreboard().getTeam("灰队");

                                        if (Objects.equals(arua.getDisplayName(), "yes")) {
                                            Board.add(arua.getPrefix() + "§f" + arua.getName() + ": §a✔ " + isyou(arua, player.getName()));
                                        } else if (Objects.equals(arua.getDisplayName(), "0")) {
                                            Board.add(arua.getPrefix() + "§f" + arua.getName() + ": §c✖ " + isyou(arua, player.getName()));
                                        } else {
                                            Board.add(arua.getPrefix() + "§f" + arua.getName() + ": §a" + arua.getDisplayName() + " " + isyou(arua, player.getName()));
                                        }
                                        if (Objects.equals(white.getDisplayName(), "yes")) {
                                            Board.add(white.getPrefix() + "§f" + white.getName() + ": §a✔ " + isyou(white, player.getName()));
                                        } else if (Objects.equals(white.getDisplayName(), "0")) {
                                            Board.add(white.getPrefix() + "§f" + white.getName() + ": §c✖ " + isyou(white, player.getName()));
                                        } else {
                                            Board.add(white.getPrefix() + "§f" + white.getName() + ": §a" + white.getDisplayName() + " " + isyou(white, player.getName()));
                                        }
                                        if (Objects.equals(pink.getDisplayName(), "yes")) {
                                            Board.add(pink.getPrefix() + "§f" + pink.getName() + ": §a✔ " + isyou(pink, player.getName()));
                                        } else if (Objects.equals(pink.getDisplayName(), "0")) {
                                            Board.add(pink.getPrefix() + "§f" + pink.getName() + ": §c✖ " + isyou(pink, player.getName()));
                                        } else {
                                            Board.add(pink.getPrefix() + "§f" + pink.getName() + ": §a" + pink.getDisplayName() + " " + isyou(pink, player.getName()));
                                        }
                                        if (Objects.equals(gray.getDisplayName(), "yes")) {
                                            Board.add(gray.getPrefix() + "§f" + gray.getName() + ": §a✔ " + isyou(gray, player.getName()));
                                        } else if (Objects.equals(gray.getDisplayName(), "0")) {
                                            Board.add(gray.getPrefix() + "§f" + gray.getName() + ": §c✖ " + isyou(gray, player.getName()));
                                        } else {
                                            Board.add(gray.getPrefix() + "§f" + gray.getName() + ": §a" + gray.getDisplayName() + " " + isyou(gray, player.getName()));
                                        }
                                    }
                                }

                                if (Objects.equals(config.getString("Map.ModeType"), "4v4v4v4") || Objects.equals(config.getString("Map.ModeType"), "3v3v3v3")) {//判断是否为4v4v4v4
                                    Board.add("§a ");
                                    Board.add("§f击杀数: §a" + BedWars.kill.get(player.getName()));
                                    Board.add("§f最终击杀数: §a" + BedWars.finalkill.get(player.getName()));
                                    Board.add("§f破坏床数: §a" + BedWars.breakbed.get(player.getName()));
                                }
                                if (Objects.equals(config.getString("Map.ModeType"), "4v4")) {
                                    Board.add("§f击杀数: §a" + BedWars.kill.get(player.getName()));
                                    Board.add("§f最终击杀数: §a" + BedWars.finalkill.get(player.getName()));
                                }
                                Board.add("§f ");
                                Board.add("§eLemonCraft.cn");

                                objective.setDisplayName("§e§l起床战争");
                                for (int i = 0; i < Board.size(); i++) {
                                    objective.getScore(Board.get(i)).setScore(-i + Board.size());

                                }
                                player.setScoreboard(scoreboard1);

                            }


                        }.runTaskTimer(plugin, 15L, 15L);
                        String[] spawn = getStringLocation(config.getString("Map.Spectator"));
                        player.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        player.getInventory().clear();
                        player.removePotionEffect(PotionEffectType.INVISIBILITY);
                        player.setAllowFlight(true);
                        player.setFlying(true);
                        Game item = new Game();
                        player.getInventory().setItem(0, item.getItem("追踪玩家"));
                        player.getInventory().setItem(4, item.getItem("旁观者设置"));
                        player.getInventory().setItem(8, item.getItem("返回大厅"));
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.hidePlayer(player);
                        }
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 0));
                        GameStart.getcoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
                        GameStart.getcoreboard().getTeam("旁观者").addEntry(player.getName());
                    } else {
                        String[] spawn = getStringLocation(config.getString("Map.Spectator"));
                        player.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        player.getInventory().clear();
                        player.setAllowFlight(true);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                TAB.set(player, "     §b§l你正在§e§lLemonCraft.cn§b§l上进行游戏\n", "\n§b击杀数: §e" + BedWars.kill.get(player.getName()) + " §b最终击杀数: §e" + BedWars.finalkill.get(player.getName()) + " §b破坏床数: §e" + BedWars.breakbed.get(player.getName()) + "\n\n     §a§lRank以及更多！§c§l请访问Store.LemonCraft.cn");

                            }
                        }.runTaskTimer(plugin,0L,80L);
                        new BukkitRunnable() {
                            Scoreboard scoreboard1 = Bukkit.getScoreboardManager().getNewScoreboard();
                            Objective objective = scoreboard1.registerNewObjective(player.getName(), player.getName());

                            @Override
                            public void run() {
                                ArrayList<String> Board = new ArrayList<>();

                                try {
                                    objective.unregister();
                                    objective = scoreboard1.registerNewObjective(player.getName(), player.getName());
                                } catch (NullPointerException n) {

                                }

                                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                                

                                Calendar calendar = Calendar.getInstance();

                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
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
                                Team red = GameStart.getcoreboard().getTeam("红队");
                                Team blue = GameStart.getcoreboard().getTeam("蓝队");
                                if (Objects.equals(red.getDisplayName(), "yes")) {
                                    Board.add(red.getPrefix() + "§f" + red.getName() + ": §a✔ " + isyou(red, player.getName()));
                                } else if (Objects.equals(red.getDisplayName(), "0")) {
                                    Board.add(red.getPrefix() + "§f" + red.getName() + ": §c✖" + " " + isyou(red, player.getName()));
                                } else {
                                    Board.add(red.getPrefix() + "§f" + red.getName() + ": §a" + red.getDisplayName() + " " + isyou(red, player.getName()));
                                }
                                if (Objects.equals(blue.getDisplayName(), "yes")) {
                                    Board.add(blue.getPrefix() + "§f" + blue.getName() + ": §a✔ " + isyou(blue, player.getName()));
                                } else if (Objects.equals(blue.getDisplayName(), "0")) {
                                    Board.add(blue.getPrefix() + "§f" + blue.getName() + ": §c✖" + " " + isyou(blue, player.getName()));
                                } else {
                                    Board.add(blue.getPrefix() + "§f" + blue.getName() + ": §a" + blue.getDisplayName() + " " + isyou(blue, player.getName()));
                                }
                                if (!Objects.equals(config.getString("Map.ModeType"), "4v4")) {//判断是否为4v4v4v4
                                    Team green = GameStart.getcoreboard().getTeam("绿队");
                                    Team yellow = GameStart.getcoreboard().getTeam("黄队");
                                    if (Objects.equals(green.getDisplayName(), "yes")) {
                                        Board.add(green.getPrefix() + "§f" + green.getName() + ": §a✔ " + isyou(green, player.getName()));
                                    } else if (Objects.equals(green.getDisplayName(), "0")) {
                                        Board.add(green.getPrefix() + "§f" + green.getName() + ": §c✖ " + isyou(green, player.getName()));
                                    } else {
                                        Board.add(green.getPrefix() + "§f" + green.getName() + ": §a" + green.getDisplayName() + " " + isyou(green, player.getName()));
                                    }
                                    if (Objects.equals(yellow.getDisplayName(), "yes")) {
                                        Board.add(yellow.getPrefix() + "§f" + yellow.getName() + ": §a✔ " + isyou(yellow, player.getName()));
                                    } else if (Objects.equals(yellow.getDisplayName(), "0")) {
                                        Board.add(yellow.getPrefix() + "§f" + yellow.getName() + ": §c✖" + " " + isyou(yellow, player.getName()));
                                    } else {
                                        Board.add(yellow.getPrefix() + "§f" + yellow.getName() + ": §a" + yellow.getDisplayName() + " " + isyou(yellow, player.getName()));
                                    }
                                    if (!Objects.equals(config.getString("Map.ModeType"), "4v4v4v4")) {//判断是否为4v4v4v4
                                        Team arua = GameStart.getcoreboard().getTeam("青队");
                                        Team white = GameStart.getcoreboard().getTeam("白队");
                                        Team pink = GameStart.getcoreboard().getTeam("粉队");
                                        Team gray = GameStart.getcoreboard().getTeam("灰队");

                                        if (Objects.equals(arua.getDisplayName(), "yes")) {
                                            Board.add(arua.getPrefix() + "§f" + arua.getName() + ": §a✔ " + isyou(arua, player.getName()));
                                        } else if (Objects.equals(arua.getDisplayName(), "0")) {
                                            Board.add(arua.getPrefix() + "§f" + arua.getName() + ": §c✖ " + isyou(arua, player.getName()));
                                        } else {
                                            Board.add(arua.getPrefix() + "§f" + arua.getName() + ": §a" + arua.getDisplayName() + " " + isyou(arua, player.getName()));
                                        }
                                        if (Objects.equals(white.getDisplayName(), "yes")) {
                                            Board.add(white.getPrefix() + "§f" + white.getName() + ": §a✔ " + isyou(white, player.getName()));
                                        } else if (Objects.equals(white.getDisplayName(), "0")) {
                                            Board.add(white.getPrefix() + "§f" + white.getName() + ": §c✖ " + isyou(white, player.getName()));
                                        } else {
                                            Board.add(white.getPrefix() + "§f" + white.getName() + ": §a" + white.getDisplayName() + " " + isyou(white, player.getName()));
                                        }
                                        if (Objects.equals(pink.getDisplayName(), "yes")) {
                                            Board.add(pink.getPrefix() + "§f" + pink.getName() + ": §a✔ " + isyou(pink, player.getName()));
                                        } else if (Objects.equals(pink.getDisplayName(), "0")) {
                                            Board.add(pink.getPrefix() + "§f" + pink.getName() + ": §c✖ " + isyou(pink, player.getName()));
                                        } else {
                                            Board.add(pink.getPrefix() + "§f" + pink.getName() + ": §a" + pink.getDisplayName() + " " + isyou(pink, player.getName()));
                                        }
                                        if (Objects.equals(gray.getDisplayName(), "yes")) {
                                            Board.add(gray.getPrefix() + "§f" + gray.getName() + ": §a✔ " + isyou(gray, player.getName()));
                                        } else if (Objects.equals(gray.getDisplayName(), "0")) {
                                            Board.add(gray.getPrefix() + "§f" + gray.getName() + ": §c✖ " + isyou(gray, player.getName()));
                                        } else {
                                            Board.add(gray.getPrefix() + "§f" + gray.getName() + ": §a" + gray.getDisplayName() + " " + isyou(gray, player.getName()));
                                        }
                                    }
                                }

                                if (Objects.equals(config.getString("Map.ModeType"), "4v4v4v4") || Objects.equals(config.getString("Map.ModeType"), "3v3v3v3")) {//判断是否为4v4v4v4
                                    Board.add("§a ");
                                    Board.add("§f击杀数: §a" + BedWars.kill.get(player.getName()));
                                    Board.add("§f最终击杀数: §a" + BedWars.finalkill.get(player.getName()));
                                    Board.add("§f破坏床数: §a" + BedWars.breakbed.get(player.getName()));
                                }
                                if (Objects.equals(config.getString("Map.ModeType"), "4v4")) {
                                    Board.add("§f击杀数: §a" + BedWars.kill.get(player.getName()));
                                    Board.add("§f最终击杀数: §a" + BedWars.finalkill.get(player.getName()));
                                }
                                Board.add("§f ");
                                Board.add("§eLemonCraft.cn");

                                objective.setDisplayName("§e§l起床战争");
                                for (int i = 0; i < Board.size(); i++) {
                                    objective.getScore(Board.get(i)).setScore(-i + Board.size());

                                }
                                player.setScoreboard(scoreboard1);

                            }


                        }.runTaskTimer(plugin, 15L, 15L);
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.hidePlayer(player);

                        }
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 0));
                        player.setFlying(true);
                        Bukkit.broadcastMessage(GameStart.getcoreboard().getEntryTeam(player.getName()).getSuffix()+player.getName()+" §7重新连接！");
                        player.sendMessage("§e你将在 §c10 §e秒后重生！");
                        player.sendTitle("§c你死了", "§e你将在 §c10 §e秒后重生");
                        new BukkitRunnable() {
                            public void run() {
                                player.sendMessage("§e你将在 §c9 §e秒后重生！");
                                player.sendTitle("§c你死了", "§e你将在 §c9 §e秒后重生");
                            }
                        }.runTaskLater(plugin, 20L);
                        new BukkitRunnable() {
                            public void run() {
                                player.sendMessage("§e你将在 §c8 §e秒后重生！");
                                player.sendTitle("§c你死了", "§e你将在 §c8 §e秒后重生");
                            }
                        }.runTaskLater(plugin, 40L);
                        new BukkitRunnable() {
                            public void run() {
                                player.sendMessage("§e你将在 §c7 §e秒后重生！");
                                player.sendTitle("§c你死了", "§e你将在 §c7 §e秒后重生");
                            }
                        }.runTaskLater(plugin, 60L);
                        new BukkitRunnable() {
                            public void run() {
                                player.sendMessage("§e你将在 §c6 §e秒后重生！");
                                player.sendTitle("§c你死了", "§e你将在 §c6 §e秒后重生");
                            }
                        }.runTaskLater(plugin, 80L);
                        new BukkitRunnable() {
                            public void run() {
                                player.sendMessage("§e你将在 §c5 §e秒后重生！");
                                player.sendTitle("§c你死了", "§e你将在 §c5 §e秒后重生");
                            }
                        }.runTaskLater(plugin, 100L);
                        new BukkitRunnable() {
                            public void run() {
                                player.sendMessage("§e你将在 §c4 §e秒后重生！");
                                player.sendTitle("§c你死了", "§e你将在 §c4 §e秒后重生");
                            }
                        }.runTaskLater(plugin, 120L);
                        new BukkitRunnable() {
                            public void run() {
                                player.sendMessage("§e你将在 §c3 §e秒后重生！");
                                player.sendTitle("§c你死了", "§e你将在 §c3 §e秒后重生");
                            }
                        }.runTaskLater(plugin, 140L);
                        new BukkitRunnable() {
                            public void run() {
                                player.sendMessage("§e你将在 §c2 §e秒后重生！");
                                player.sendTitle("§c你死了", "§e你将在 §c2 §e秒后重生");
                            }
                        }.runTaskLater(plugin, 160L);
                        new BukkitRunnable() {
                            public void run() {
                                player.sendMessage("§e你将在 §c1 §e秒后重生！");
                                player.sendTitle("§c你死了", "§e你将在 §c1 §e秒后重生");
                            }
                        }.runTaskLater(plugin, 180L);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                player.setGameMode(GameMode.SURVIVAL);
                                player.sendTitle("§a已重生", "");
                                player.sendMessage("§a你已重生！");
                                String[] spawn = getStringLocation(config.getString("Map." + GameStart.getcoreboard().getEntryTeam(player.getName()).getName() + ".Spawn"));
                                player.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                                player.setAllowFlight(false);
                                player.setFlying(false);
                                for (Player players : Bukkit.getOnlinePlayers()) {
                                    players.showPlayer(player);
                                }
                                player.getInventory().clear();
                                ItemStack WOOD_SWORD = new ItemStack(Material.WOOD_SWORD);
                                ItemMeta itemMeta = WOOD_SWORD.getItemMeta();
                                itemMeta.spigot().setUnbreakable(true);
                                WOOD_SWORD.setItemMeta(itemMeta);
                                player.getInventory().addItem(WOOD_SWORD);
                                Game item = new Game();
                                player.getInventory().setItem(8, item.getItem("指南针"));
                                player.removePotionEffect(PotionEffectType.INVISIBILITY);
                            }
                        }.runTaskLater(plugin, 200L);
                    }
                }
            }
        }
    }
}