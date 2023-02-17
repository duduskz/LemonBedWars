package cn.lemoncraft.bedwars.Game.Arena.Menu;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Game.Arena.GameStart;
import cn.lemoncraft.bedwars.Item.ShopItem;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.minecraft.server.v1_8_R3.ChatMessage;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenWindow;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Listener {
    private Inventory inv = Bukkit.createInventory(null, 54, "快捷商店");

    @EventHandler
    public void openshopmenu(NPCRightClickEvent e) {
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        FileConfiguration config = plugin.getConfig();
        if (e.getNPC().getName().equalsIgnoreCase("§a")) {
            if (!GameStart.getcoreboard().getEntryTeam(e.getClicker().getName()).getName().equalsIgnoreCase("旁观者")) {
                ItemStack quickshop = new ItemStack(Material.NETHER_STAR);
                List<String> quicklore = new ArrayList<>();
                quicklore.add("§e点击查看");
                quickshop.getItemMeta().setLore(quicklore);
                ItemMeta quickmeta = quickshop.getItemMeta();
                quickmeta.setLore(quicklore);
                quickmeta.setDisplayName("§a快捷商店");
                quickshop.setItemMeta(quickmeta);
                inv.setItem(0, quickshop);
                ItemStack block = new ItemStack(Material.STAINED_CLAY, 1, (short) 1);
                List<String> blocklore = new ArrayList<>();
                blocklore.add("§e点击查看");
                block.getItemMeta().setLore(blocklore);
                ItemMeta blockmeta = block.getItemMeta();
                blockmeta.setLore(blocklore);
                blockmeta.setDisplayName("§a方块");
                block.setItemMeta(blockmeta);
                inv.setItem(1, block);
                ItemStack sword = new ItemStack(Material.GOLD_SWORD);
                List<String> swordlore = new ArrayList<>();
                swordlore.add("§e点击查看");
                ItemMeta swordmeta = sword.getItemMeta();
                swordmeta.setLore(swordlore);
                swordmeta.setDisplayName("§a近战武器");
                sword.setItemMeta(swordmeta);
                inv.setItem(2, sword);
                ItemStack hujia = new ItemStack(Material.CHAINMAIL_BOOTS);
                List<String> hujialore = new ArrayList<>();
                hujialore.add("§e点击查看");
                ItemMeta hujiameta = hujia.getItemMeta();
                hujiameta.setLore(hujialore);
                hujiameta.setDisplayName("§a护甲");
                hujia.setItemMeta(hujiameta);
                inv.setItem(3, hujia);
                ItemStack AXE = new ItemStack(Material.STONE_PICKAXE);
                List<String> AXElore = new ArrayList<>();
                AXElore.add("§e点击查看");
                ItemMeta AXEmeta = AXE.getItemMeta();
                AXEmeta.setLore(AXElore);
                AXEmeta.setDisplayName("§a工具");
                AXE.setItemMeta(AXEmeta);
                inv.setItem(4, AXE);
                ItemStack BOW = new ItemStack(Material.BOW);
                List<String> BOWlore = new ArrayList<>();
                BOWlore.add("§e点击查看");
                BOW.getItemMeta().setLore(BOWlore);
                ItemMeta bowmeta = BOW.getItemMeta();
                bowmeta.setLore(BOWlore);
                bowmeta.setDisplayName("§a远程武器");
                BOW.setItemMeta(bowmeta);
                inv.setItem(5, BOW);
                ItemStack potion = new ItemStack(Material.BREWING_STAND_ITEM);
                List<String> potionlore = new ArrayList<>();
                potionlore.add("§e点击查看");
                ItemMeta potionmeta = potion.getItemMeta();
                potionmeta.setLore(potionlore);
                potionmeta.setDisplayName("§a药水");
                potion.setItemMeta(potionmeta);
                inv.setItem(6, potion);
                ItemStack TNT = new ItemStack(Material.TNT);
                List<String> TNTlore = new ArrayList<>();
                TNTlore.add("§e点击查看");
                ItemMeta TNTmeta = TNT.getItemMeta();
                TNTmeta.setLore(TNTlore);
                TNTmeta.setDisplayName("§a实用道具");
                TNT.setItemMeta(TNTmeta);
                inv.setItem(7, TNT);
                ItemStack glass_1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
                ItemMeta glass_1_meta = glass_1.getItemMeta();
                glass_1_meta.setDisplayName("§8↑分类");
                ArrayList<String> glass_1_meta_lore = new ArrayList();
                glass_1_meta_lore.add("§8↓物品");
                glass_1_meta.setLore(glass_1_meta_lore);
                glass_1.setItemMeta(glass_1_meta);
                ItemStack glass_2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13);
                ItemMeta glass_2_meta = glass_2.getItemMeta();
                glass_2_meta.setDisplayName("§8↑分类");
                ArrayList<String> glass_2_meta_lore = new ArrayList();
                glass_2_meta_lore.add("§8↓物品");
                glass_2_meta.setLore(glass_2_meta_lore);
                glass_2.setItemMeta(glass_2_meta);
                inv.setItem(9, glass_2);
                inv.setItem(10, glass_1);
                inv.setItem(11, glass_1);
                inv.setItem(12, glass_1);
                inv.setItem(13, glass_1);
                inv.setItem(14, glass_1);
                inv.setItem(15, glass_1);
                inv.setItem(16, glass_1);
                inv.setItem(17, glass_1);

                ShopItem Quickitem = new ShopItem();
                inv.setItem(19, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 19), e.getClicker()));
                inv.setItem(20, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 20), e.getClicker()));
                inv.setItem(21, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 21), e.getClicker()));
                inv.setItem(22, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 22), e.getClicker()));
                inv.setItem(23, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 23), e.getClicker()));
                inv.setItem(24, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 24), e.getClicker()));
                inv.setItem(25, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 25), e.getClicker()));
                inv.setItem(28, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 28), e.getClicker()));
                inv.setItem(29, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 29), e.getClicker()));
                inv.setItem(30, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 30), e.getClicker()));
                inv.setItem(31, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 31), e.getClicker()));
                inv.setItem(32, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 32), e.getClicker()));
                inv.setItem(33, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 33), e.getClicker()));
                inv.setItem(34, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 34), e.getClicker()));
                inv.setItem(37, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 37), e.getClicker()));
                inv.setItem(38, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 38), e.getClicker()));
                inv.setItem(39, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 39), e.getClicker()));
                inv.setItem(40, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 40), e.getClicker()));
                inv.setItem(41, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 41), e.getClicker()));
                inv.setItem(42, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 42), e.getClicker()));
                inv.setItem(43, Quickitem.getItem(PlayerDataManage.getplayershop(e.getClicker(), 43), e.getClicker()));
                e.getClicker().openInventory(inv);
            }
        }
    }

    @EventHandler
    public void useitem(InventoryClickEvent event) {
        int woolcolor = 0;
        if (GameStart.getcoreboard().getEntryTeam(event.getWhoClicked().getName()).getName().equalsIgnoreCase("红队")) {
            woolcolor = 14;
        }
        if (GameStart.getcoreboard().getEntryTeam(event.getWhoClicked().getName()).getName().equalsIgnoreCase("蓝队")) {
            woolcolor = 11;
        }
        if (GameStart.getcoreboard().getEntryTeam(event.getWhoClicked().getName()).getName().equalsIgnoreCase("绿队")) {
            woolcolor = 5;
        }
        if (GameStart.getcoreboard().getEntryTeam(event.getWhoClicked().getName()).getName().equalsIgnoreCase("黄队")) {
            woolcolor = 4;
        }
        if (GameStart.getcoreboard().getEntryTeam(event.getWhoClicked().getName()).getName().equalsIgnoreCase("白队")) {
            woolcolor = 0;
        }
        if (GameStart.getcoreboard().getEntryTeam(event.getWhoClicked().getName()).getName().equalsIgnoreCase("青队")) {
            woolcolor = 9;
        }
        if (GameStart.getcoreboard().getEntryTeam(event.getWhoClicked().getName()).getName().equalsIgnoreCase("粉队")) {
            woolcolor = 6;
        }
        if (GameStart.getcoreboard().getEntryTeam(event.getWhoClicked().getName()).getName().equalsIgnoreCase("灰队")) {
            woolcolor = 8;
        }
        if (event.isShiftClick()) {
            String itemname = null;
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("羊毛")) {
                itemname = "羊毛";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("硬化粘土")) {
                itemname = "硬化粘土";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("防爆玻璃")) {
                itemname = "防爆玻璃";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("末地石")) {
                itemname = "末地石";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("梯子")) {
                itemname = "梯子";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("木板")) {
                itemname = "木板";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("黑曜石")) {
                itemname = "黑曜石";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("石剑")) {
                itemname = "石剑";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("铁剑")) {
                itemname = "铁剑";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("钻石剑")) {
                itemname = "钻石剑";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("击退棒")) {
                itemname = "击退棒";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("锁链护甲")) {
                itemname = "锁链护甲";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("铁护甲")) {
                itemname = "铁护甲";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("钻石护甲")) {
                itemname = "钻石护甲";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("镐")) {
                itemname = "镐";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("斧")) {
                itemname = "斧";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("剪刀")) {
                itemname = "剪刀";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("弓")) {
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("力量")) {
                    if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("冲击")) {
                        itemname = "力量冲击弓";
                    } else {
                        itemname = "力量弓";
                    }
                } else {
                    itemname = "弓";
                }
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("箭")) {
                itemname = "箭";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("速度药水")) {
                itemname = "速度药水";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("跳跃药水")) {
                itemname = "跳跃药水";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("隐身药水")) {
                itemname = "隐身药水";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("金苹果")) {
                itemname = "金苹果";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("TNT")) {
                itemname = "TNT";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("末影珍珠")) {
                itemname = "末影珍珠";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("搭桥蛋")) {
                itemname = "搭桥蛋";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("梦幻守卫")) {
                itemname = "梦幻守卫";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("魔法牛奶")) {
                itemname = "魔法牛奶";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("水桶")) {
                itemname = "水桶";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("海绵")) {
                itemname = "海绵";
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("海绵")) {
                itemname = "火球";
            }
            if (event.getInventory().getTitle().contains("快捷商店")) {
                PlayerDataManage.setplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), event.getSlot(), "空");
                ShopItem item = new ShopItem();
                event.getInventory().setItem(event.getSlot(), item.getItem("空", Bukkit.getPlayer(event.getWhoClicked().getName())));
            } else {
                ShopItem item = new ShopItem();
                event.getInventory().clear();
                event.getInventory().setItem(4, item.getItem(itemname, (Player) event.getWhoClicked()));
                update((Player) event.getWhoClicked(), "添加快捷购买");
                event.setCancelled(true);
                event.getInventory().setItem(19, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 19), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(20, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 20), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(21, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 21), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(22, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 22), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(23, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 23), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(24, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 24), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(25, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 25), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(28, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 28), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(29, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 29), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(30, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 30), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(31, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 31), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(32, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 32), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(33, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 33), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(34, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 34), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(37, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 37), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(38, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 38), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(39, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 39), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(40, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 40), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(41, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 41), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(42, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 42), Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.getInventory().setItem(43, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 43), Bukkit.getPlayer(event.getWhoClicked().getName())));
                BedWars.playeradditem.replace(event.getWhoClicked().getName(), itemname);
            }
        } else {
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().equalsIgnoreCase("§a方块")) {
            ShopItem item = new ShopItem();
            event.setCancelled(true);
            FileConfiguration config = BedWars.getPlugin(BedWars.class).getConfig();
            inv.setItem(19, item.getItem("羊毛", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(20, item.getItem("硬化粘土", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(21, item.getItem("防爆玻璃", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(22, item.getItem("末地石", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(23, item.getItem("梯子", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(24, item.getItem("木板", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            if (config.getString("Map.ModeType").equalsIgnoreCase("4v4")) {

                inv.setItem(25, item.getItem("黑曜石", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            }
            update(Bukkit.getPlayer(event.getWhoClicked().getName()), "方块");
            inv.setItem(28, null);
            inv.setItem(29, null);
            inv.setItem(30, null);
            inv.setItem(31, null);
            inv.setItem(32, null);
            inv.setItem(33, null);
            inv.setItem(34, null);
            inv.setItem(37, null);
            inv.setItem(38, null);
            inv.setItem(39, null);
            inv.setItem(40, null);
            inv.setItem(41, null);
            inv.setItem(42, null);
            inv.setItem(43, null);
            ItemStack glass_1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
            ItemMeta glass_1_meta = glass_1.getItemMeta();
            glass_1_meta.setDisplayName("§8↑分类");
            ArrayList<String> glass_1_meta_lore = new ArrayList();
            glass_1_meta_lore.add("§8↓物品");
            glass_1_meta.setLore(glass_1_meta_lore);
            glass_1.setItemMeta(glass_1_meta);
            ItemStack glass_2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13);
            ItemMeta glass_2_meta = glass_2.getItemMeta();
            glass_2_meta.setDisplayName("§8↑分类");
            ArrayList<String> glass_2_meta_lore = new ArrayList();
            glass_2_meta_lore.add("§8↓物品");
            glass_2_meta.setLore(glass_2_meta_lore);
            glass_2.setItemMeta(glass_2_meta);
            inv.setItem(9, glass_1);
            inv.setItem(10, glass_2);
            inv.setItem(11, glass_1);
            inv.setItem(12, glass_1);
            inv.setItem(13, glass_1);
            inv.setItem(14, glass_1);
            inv.setItem(15, glass_1);
            inv.setItem(16, glass_1);
            inv.setItem(17, glass_1);
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().equalsIgnoreCase("§a近战武器")) {
            ShopItem item = new ShopItem();
            event.setCancelled(true);
            inv.setItem(19, item.getItem("石剑", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(20, item.getItem("铁剑", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(21, item.getItem("钻石剑", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(22, item.getItem("击退棒", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            update(Bukkit.getPlayer(event.getWhoClicked().getName()), "近战武器");
            inv.setItem(23, null);
            inv.setItem(24, null);
            inv.setItem(25, null);
            inv.setItem(28, null);
            inv.setItem(29, null);
            inv.setItem(30, null);
            inv.setItem(31, null);
            inv.setItem(32, null);
            inv.setItem(33, null);
            inv.setItem(34, null);
            inv.setItem(37, null);
            inv.setItem(38, null);
            inv.setItem(39, null);
            inv.setItem(40, null);
            inv.setItem(41, null);
            inv.setItem(42, null);
            inv.setItem(43, null);
            ItemStack glass_1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
            ItemMeta glass_1_meta = glass_1.getItemMeta();
            glass_1_meta.setDisplayName("§8↑分类");
            ArrayList<String> glass_1_meta_lore = new ArrayList();
            glass_1_meta_lore.add("§8↓物品");
            glass_1_meta.setLore(glass_1_meta_lore);
            glass_1.setItemMeta(glass_1_meta);
            ItemStack glass_2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13);
            ItemMeta glass_2_meta = glass_2.getItemMeta();
            glass_2_meta.setDisplayName("§8↑分类");
            ArrayList<String> glass_2_meta_lore = new ArrayList();
            glass_2_meta_lore.add("§8↓物品");
            glass_2_meta.setLore(glass_2_meta_lore);
            glass_2.setItemMeta(glass_2_meta);
            inv.setItem(9, glass_1);
            inv.setItem(10, glass_1);
            inv.setItem(11, glass_2);
            inv.setItem(12, glass_1);
            inv.setItem(13, glass_1);
            inv.setItem(14, glass_1);
            inv.setItem(15, glass_1);
            inv.setItem(16, glass_1);
            inv.setItem(17, glass_1);
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().equalsIgnoreCase("§a护甲")) {
            ShopItem item = new ShopItem();
            event.setCancelled(true);
            inv.setItem(19, item.getItem("锁链护甲", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(20, item.getItem("铁护甲", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(21, item.getItem("钻石护甲", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            update(Bukkit.getPlayer(event.getWhoClicked().getName()), "护甲");
            inv.setItem(22, null);
            inv.setItem(23, null);
            inv.setItem(24, null);
            inv.setItem(25, null);
            inv.setItem(28, null);
            inv.setItem(29, null);
            inv.setItem(30, null);
            inv.setItem(31, null);
            inv.setItem(32, null);
            inv.setItem(33, null);
            inv.setItem(34, null);
            inv.setItem(37, null);
            inv.setItem(38, null);
            inv.setItem(39, null);
            inv.setItem(40, null);
            inv.setItem(41, null);
            inv.setItem(42, null);
            inv.setItem(43, null);
            ItemStack glass_1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
            ItemMeta glass_1_meta = glass_1.getItemMeta();
            glass_1_meta.setDisplayName("§8↑分类");
            ArrayList<String> glass_1_meta_lore = new ArrayList();
            glass_1_meta_lore.add("§8↓物品");
            glass_1_meta.setLore(glass_1_meta_lore);
            glass_1.setItemMeta(glass_1_meta);
            ItemStack glass_2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13);
            ItemMeta glass_2_meta = glass_2.getItemMeta();
            glass_2_meta.setDisplayName("§8↑分类");
            ArrayList<String> glass_2_meta_lore = new ArrayList();
            glass_2_meta_lore.add("§8↓物品");
            glass_2_meta.setLore(glass_2_meta_lore);
            glass_2.setItemMeta(glass_2_meta);
            inv.setItem(9, glass_1);
            inv.setItem(10, glass_1);
            inv.setItem(11, glass_1);
            inv.setItem(12, glass_2);
            inv.setItem(13, glass_1);
            inv.setItem(14, glass_1);
            inv.setItem(15, glass_1);
            inv.setItem(16, glass_1);
            inv.setItem(17, glass_1);
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().equalsIgnoreCase("§a工具")) {
            ShopItem item = new ShopItem();
            event.setCancelled(true);
            inv.setItem(19, item.getItem("剪刀", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(20, item.getItem("镐", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(21, item.getItem("斧", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(22, null);
            update(Bukkit.getPlayer(event.getWhoClicked().getName()), "工具");
            inv.setItem(23, null);
            inv.setItem(24, null);
            inv.setItem(25, null);
            inv.setItem(28, null);
            inv.setItem(29, null);
            inv.setItem(30, null);
            inv.setItem(31, null);
            inv.setItem(32, null);
            inv.setItem(33, null);
            inv.setItem(34, null);
            inv.setItem(37, null);
            inv.setItem(38, null);
            inv.setItem(39, null);
            inv.setItem(40, null);
            inv.setItem(41, null);
            inv.setItem(42, null);
            inv.setItem(43, null);
            ItemStack glass_1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
            ItemMeta glass_1_meta = glass_1.getItemMeta();
            glass_1_meta.setDisplayName("§8↑分类");
            ArrayList<String> glass_1_meta_lore = new ArrayList();
            glass_1_meta_lore.add("§8↓物品");
            glass_1_meta.setLore(glass_1_meta_lore);
            glass_1.setItemMeta(glass_1_meta);
            ItemStack glass_2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13);
            ItemMeta glass_2_meta = glass_2.getItemMeta();
            glass_2_meta.setDisplayName("§8↑分类");
            ArrayList<String> glass_2_meta_lore = new ArrayList();
            glass_2_meta_lore.add("§8↓物品");
            glass_2_meta.setLore(glass_2_meta_lore);
            glass_2.setItemMeta(glass_2_meta);
            inv.setItem(9, glass_1);
            inv.setItem(10, glass_1);
            inv.setItem(11, glass_1);
            inv.setItem(12, glass_1);
            inv.setItem(13, glass_2);
            inv.setItem(14, glass_1);
            inv.setItem(15, glass_1);
            inv.setItem(16, glass_1);
            inv.setItem(17, glass_1);
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().equalsIgnoreCase("§a远程武器")) {
            ShopItem item = new ShopItem();
            event.setCancelled(true);
            inv.setItem(19, item.getItem("箭", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(20, item.getItem("弓", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(21, item.getItem("力量弓", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(22, item.getItem("力量冲击弓", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(23, null);
            update(Bukkit.getPlayer(event.getWhoClicked().getName()), "远程武器");
            inv.setItem(24, null);
            inv.setItem(25, null);
            inv.setItem(28, null);
            inv.setItem(29, null);
            inv.setItem(30, null);
            inv.setItem(31, null);
            inv.setItem(32, null);
            inv.setItem(33, null);
            inv.setItem(34, null);
            inv.setItem(37, null);
            inv.setItem(38, null);
            inv.setItem(39, null);
            inv.setItem(40, null);
            inv.setItem(41, null);
            inv.setItem(42, null);
            inv.setItem(43, null);
            ItemStack glass_1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
            ItemMeta glass_1_meta = glass_1.getItemMeta();
            glass_1_meta.setDisplayName("§8↑分类");
            ArrayList<String> glass_1_meta_lore = new ArrayList();
            glass_1_meta_lore.add("§8↓物品");
            glass_1_meta.setLore(glass_1_meta_lore);
            glass_1.setItemMeta(glass_1_meta);
            ItemStack glass_2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13);
            ItemMeta glass_2_meta = glass_2.getItemMeta();
            glass_2_meta.setDisplayName("§8↑分类");
            ArrayList<String> glass_2_meta_lore = new ArrayList();
            glass_2_meta_lore.add("§8↓物品");
            glass_2_meta.setLore(glass_2_meta_lore);
            glass_2.setItemMeta(glass_2_meta);
            inv.setItem(9, glass_1);
            inv.setItem(10, glass_1);
            inv.setItem(11, glass_1);
            inv.setItem(12, glass_1);
            inv.setItem(13, glass_1);
            inv.setItem(14, glass_2);
            inv.setItem(15, glass_1);
            inv.setItem(16, glass_1);
            inv.setItem(17, glass_1);
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().equalsIgnoreCase("§a药水")) {
            ShopItem item = new ShopItem();
            event.setCancelled(true);
            inv.setItem(19, item.getItem("速度药水", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(20, item.getItem("跳跃药水", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(21, item.getItem("隐身药水", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(22, null);
            update(Bukkit.getPlayer(event.getWhoClicked().getName()), "药水");
            inv.setItem(23, null);
            inv.setItem(24, null);
            inv.setItem(25, null);
            inv.setItem(28, null);
            inv.setItem(29, null);
            inv.setItem(30, null);
            inv.setItem(31, null);
            inv.setItem(32, null);
            inv.setItem(33, null);
            inv.setItem(34, null);
            inv.setItem(37, null);
            inv.setItem(38, null);
            inv.setItem(39, null);
            inv.setItem(40, null);
            inv.setItem(41, null);
            inv.setItem(42, null);
            inv.setItem(43, null);
            ItemStack glass_1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
            ItemMeta glass_1_meta = glass_1.getItemMeta();
            glass_1_meta.setDisplayName("§8↑分类");
            ArrayList<String> glass_1_meta_lore = new ArrayList();
            glass_1_meta_lore.add("§8↓物品");
            glass_1_meta.setLore(glass_1_meta_lore);
            glass_1.setItemMeta(glass_1_meta);
            ItemStack glass_2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13);
            ItemMeta glass_2_meta = glass_2.getItemMeta();
            glass_2_meta.setDisplayName("§8↑分类");
            ArrayList<String> glass_2_meta_lore = new ArrayList();
            glass_2_meta_lore.add("§8↓物品");
            glass_2_meta.setLore(glass_2_meta_lore);
            glass_2.setItemMeta(glass_2_meta);
            inv.setItem(9, glass_1);
            inv.setItem(10, glass_1);
            inv.setItem(11, glass_1);
            inv.setItem(12, glass_1);
            inv.setItem(13, glass_1);
            inv.setItem(14, glass_1);
            inv.setItem(15, glass_2);
            inv.setItem(16, glass_1);
            inv.setItem(17, glass_1);
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().equalsIgnoreCase("§a实用道具")) {
            ShopItem item = new ShopItem();
            event.setCancelled(true);
            inv.setItem(19, item.getItem("金苹果", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(20, item.getItem("蠹虫", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(21, item.getItem("梦幻守卫", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(22, item.getItem("火球", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(23, item.getItem("TNT", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(24, item.getItem("末影珍珠", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(25, item.getItem("水桶", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(28, item.getItem("搭桥蛋", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(29, item.getItem("魔法牛奶", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(30, item.getItem("海绵", Bukkit.getPlayer(Bukkit.getPlayer(event.getWhoClicked().getName()).getName())));
            inv.setItem(31, null);
            update(Bukkit.getPlayer(event.getWhoClicked().getName()), "实用道具");
            inv.setItem(32, null);
            inv.setItem(33, null);
            inv.setItem(34, null);
            inv.setItem(37, null);
            inv.setItem(38, null);
            inv.setItem(39, null);
            inv.setItem(40, null);
            inv.setItem(41, null);
            inv.setItem(42, null);
            inv.setItem(43, null);
            ItemStack glass_1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
            ItemMeta glass_1_meta = glass_1.getItemMeta();
            glass_1_meta.setDisplayName("§8↑分类");
            ArrayList<String> glass_1_meta_lore = new ArrayList();
            glass_1_meta_lore.add("§8↓物品");
            glass_1_meta.setLore(glass_1_meta_lore);
            glass_1.setItemMeta(glass_1_meta);
            ItemStack glass_2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13);
            ItemMeta glass_2_meta = glass_2.getItemMeta();
            glass_2_meta.setDisplayName("§8↑分类");
            ArrayList<String> glass_2_meta_lore = new ArrayList();
            glass_2_meta_lore.add("§8↓物品");
            glass_2_meta.setLore(glass_2_meta_lore);
            glass_2.setItemMeta(glass_2_meta);
            inv.setItem(9, glass_1);
            inv.setItem(10, glass_1);
            inv.setItem(11, glass_1);
            inv.setItem(12, glass_1);
            inv.setItem(13, glass_1);
            inv.setItem(14, glass_1);
            inv.setItem(15, glass_1);
            inv.setItem(16, glass_2);
            inv.setItem(17, glass_1);
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().equalsIgnoreCase("§a快捷商店")) {
            ShopItem item = new ShopItem();
            event.setCancelled(true);
            inv.setItem(19, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 19), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(20, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 20), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(21, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 21), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(22, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 22), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(23, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 23), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(24, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 24), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(25, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 25), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(28, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 28), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(29, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 29), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(30, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 30), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(31, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 31), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(32, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 32), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(33, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 33), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(34, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 34), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(37, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 37), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(38, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 38), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(39, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 39), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(40, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 40), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(41, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 41), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(42, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 42), Bukkit.getPlayer(event.getWhoClicked().getName())));
            inv.setItem(43, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 43), Bukkit.getPlayer(event.getWhoClicked().getName())));
            ItemStack glass_1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
            update(Bukkit.getPlayer(event.getWhoClicked().getName()), "快捷商店");
            ItemMeta glass_1_meta = glass_1.getItemMeta();
            glass_1_meta.setDisplayName("§8↑分类");
            ArrayList<String> glass_1_meta_lore = new ArrayList();
            glass_1_meta_lore.add("§8↓物品");
            glass_1_meta.setLore(glass_1_meta_lore);
            glass_1.setItemMeta(glass_1_meta);
            ItemStack glass_2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13);
            ItemMeta glass_2_meta = glass_2.getItemMeta();
            glass_2_meta.setDisplayName("§8↑分类");
            ArrayList<String> glass_2_meta_lore = new ArrayList();
            glass_2_meta_lore.add("§8↓物品");
            glass_2_meta.setLore(glass_2_meta_lore);
            glass_2.setItemMeta(glass_2_meta);
            inv.setItem(9, glass_2);
            inv.setItem(10, glass_1);
            inv.setItem(11, glass_1);
            inv.setItem(12, glass_1);
            inv.setItem(13, glass_1);
            inv.setItem(14, glass_1);
            inv.setItem(15, glass_1);
            inv.setItem(16, glass_1);
            inv.setItem(17, glass_1);
        }

            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("羊毛")) {
                if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 4)) {
                    event.getWhoClicked().getInventory().addItem(new ItemStack(Material.WOOL, 16, (short) woolcolor));
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 4));
                    event.getWhoClicked().sendMessage("§a你购买了 §6羊毛");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                } else {
                    event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("硬化粘土")) {
                if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 12)) {
                    event.getWhoClicked().getInventory().addItem(new ItemStack(Material.STAINED_CLAY, 16, (short) woolcolor));
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 12));
                    event.getWhoClicked().sendMessage("§a你购买了 §6硬化粘土");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                } else {
                    event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("防爆玻璃")) {
                if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 12)) {
                    event.getWhoClicked().getInventory().addItem(new ItemStack(Material.STAINED_GLASS, 4, (short) woolcolor));
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 12));
                    event.getWhoClicked().sendMessage("§a你购买了 §6防爆玻璃");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                } else {
                    event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("末地石")) {
                if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 24)) {
                    event.getWhoClicked().getInventory().addItem(new ItemStack(Material.ENDER_STONE, 16));
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 24));
                    event.getWhoClicked().sendMessage("§a你购买了 §6末地石");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                } else {
                    event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("钻石剑")) {
                FileConfiguration config = BedWars.getPlugin(BedWars.class).getConfig();
                if (config.getString("Map.ModeType").equalsIgnoreCase("单人") || config.getString("Map.ModeType").equalsIgnoreCase("双人")) {
                    if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 3)) {
                        if (event.getWhoClicked().getInventory().getItem(0).getType() == Material.WOOD_SWORD || event.getWhoClicked().getInventory().getItem(0).getType() == Material.STONE_SWORD || event.getWhoClicked().getInventory().getItem(0).getType() == Material.IRON_SWORD) {
                            event.getWhoClicked().getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD, 1));
                        } else {
                            event.getWhoClicked().getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
                        }
                        event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 3));
                        event.getWhoClicked().sendMessage("§a你购买了 §6钻石剑");
                        event.setCancelled(true);
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                    } else {
                        event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                        event.setCancelled(true);
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                    }
                } else {
                    if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 4)) {
                        if (event.getWhoClicked().getInventory().getItem(0).getType() == Material.WOOD_SWORD || event.getWhoClicked().getInventory().getItem(0).getType() == Material.STONE_SWORD || event.getWhoClicked().getInventory().getItem(0).getType() == Material.IRON_SWORD) {
                            event.getWhoClicked().getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD, 1));
                        } else {
                            event.getWhoClicked().getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
                        }
                        event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 4));
                        event.getWhoClicked().sendMessage("§a你购买了 §6钻石剑");
                        event.setCancelled(true);
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                    } else {
                        event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                        event.setCancelled(true);
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                    }
                }
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("梯子")) {
                if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 4)) {
                    event.getWhoClicked().getInventory().addItem(new ItemStack(Material.LADDER, 16));
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 4));
                    event.getWhoClicked().sendMessage("§a你购买了 §6梯子");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                } else {
                    event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("木板")) {
                if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 4)) {
                    event.getWhoClicked().getInventory().addItem(new ItemStack(Material.WOOD, 16));
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 4));
                    event.getWhoClicked().sendMessage("§a你购买了 §6木板");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                } else {
                    event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("黑曜石")) {
                FileConfiguration config = BedWars.getPlugin(BedWars.class).getConfig();
                if (config.getString("Map.ModeType").equalsIgnoreCase("4v4")) {
                    if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 4)) {
                        event.getWhoClicked().getInventory().addItem(new ItemStack(Material.OBSIDIAN, 4));
                        event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 4));
                        event.getWhoClicked().sendMessage("§a你购买了 §6黑曜石");
                        event.setCancelled(true);
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                    } else {
                        event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                        event.setCancelled(true);
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                    }
                } else {
                    event.getWhoClicked().sendMessage("§c你在该模式下不能购买此物品！");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("石剑")) {
                if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 10)) {
                    if (event.getWhoClicked().getInventory().getItem(0).getType() == Material.WOOD_SWORD) {
                        event.getWhoClicked().getInventory().setItem(0, new ItemStack(Material.STONE_SWORD, 1));
                    } else {
                        event.getWhoClicked().getInventory().addItem(new ItemStack(Material.STONE_SWORD, 1));
                    }
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 10));
                    event.getWhoClicked().sendMessage("§a你购买了 §6石剑");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                } else {
                    event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("铁剑")) {
                if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 7)) {
                    if (event.getWhoClicked().getInventory().getItem(0).getType() == Material.WOOD_SWORD || event.getWhoClicked().getInventory().getItem(0).getType() == Material.STONE_SWORD) {
                        event.getWhoClicked().getInventory().setItem(0, new ItemStack(Material.IRON_SWORD, 1));
                    } else {
                        event.getWhoClicked().getInventory().addItem(new ItemStack(Material.IRON_SWORD, 1));
                    }
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 7));
                    event.getWhoClicked().sendMessage("§a你购买了 §6铁剑");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                } else {
                    event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            }

            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("空槽")) {
                if (event.getWhoClicked().getOpenInventory().getTitle().contains("添加快捷购买")){
                    event.setCancelled(true);
                    PlayerDataManage.setplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), event.getSlot(), BedWars.playeradditem.get(event.getWhoClicked().getName()));
                    ShopItem item = new ShopItem();
                    event.setCancelled(true);
                    inv.setItem(19, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 19), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(20, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 20), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(21, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 21), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(22, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 22), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(23, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 23), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(24, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 24), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(25, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 25), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(28, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 28), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(29, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 29), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(30, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 30), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(31, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 31), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(32, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 32), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(33, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 33), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(34, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 34), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(37, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 37), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(38, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 38), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(39, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 39), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(40, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 40), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(41, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 41), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(42, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 42), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    inv.setItem(43, item.getItem(PlayerDataManage.getplayershop(Bukkit.getPlayer(event.getWhoClicked().getName()), 43), Bukkit.getPlayer(event.getWhoClicked().getName())));
                    ItemStack glass_1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
                    update(Bukkit.getPlayer(event.getWhoClicked().getName()), "快捷商店");
                    ItemMeta glass_1_meta = glass_1.getItemMeta();
                    glass_1_meta.setDisplayName("§8↑分类");
                    ArrayList<String> glass_1_meta_lore = new ArrayList();
                    glass_1_meta_lore.add("§8↓物品");
                    glass_1_meta.setLore(glass_1_meta_lore);
                    glass_1.setItemMeta(glass_1_meta);
                    ItemStack glass_2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13);
                    ItemMeta glass_2_meta = glass_2.getItemMeta();
                    glass_2_meta.setDisplayName("§8↑分类");
                    ArrayList<String> glass_2_meta_lore = new ArrayList();
                    glass_2_meta_lore.add("§8↓物品");
                    glass_2_meta.setLore(glass_2_meta_lore);
                    glass_2.setItemMeta(glass_2_meta);
                    inv.setItem(9, glass_2);
                    inv.setItem(10, glass_1);
                    inv.setItem(11, glass_1);
                    inv.setItem(12, glass_1);
                    inv.setItem(13, glass_1);
                    inv.setItem(14, glass_1);
                    inv.setItem(15, glass_1);
                    inv.setItem(16, glass_1);
                    inv.setItem(17, glass_1);
                } else {
                    event.setCancelled(true);
                }
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("分类")) {
                event.setCancelled(true);
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("击退棒")) {
                if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 10)) {
                    ItemStack item = new ItemStack(Material.STICK, 1);
                    ItemMeta itemmata = item.getItemMeta();
                    itemmata.addEnchant(Enchantment.KNOCKBACK, 1, true);
                    item.setItemMeta(itemmata);
                    event.getWhoClicked().getInventory().addItem(item);
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 10));
                    event.getWhoClicked().sendMessage("§a你购买了 §6击退棒");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                } else {
                    event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            }
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("锁链护甲")) {
                event.setCancelled(true);
                if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 35)) {

                    if (event.getWhoClicked().getInventory().getBoots().getType() == Material.LEATHER_BOOTS) {
                        event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 35));
                        event.getWhoClicked().sendMessage("§a你购买了 §6永久的锁链护甲");
                        event.getWhoClicked().getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                        event.getWhoClicked().getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                        ShopItem item = new ShopItem();
                        event.getInventory().setItem(event.getSlot(), item.getItem("锁链护甲", Bukkit.getPlayer(event.getWhoClicked().getName())));
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                    } else {
                        if (event.getWhoClicked().getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS) {
                            event.getWhoClicked().sendMessage("§c你已经拥有了此护甲！");
                            Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                        } else {
                            event.getWhoClicked().sendMessage("§c你已经拥有了更高等级的护甲！");
                            Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                        }
                    }
                } else {
                    event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("铁护甲")) {
            event.setCancelled(true);
            if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 12)) {

                if (event.getWhoClicked().getInventory().getBoots().getType() == Material.LEATHER_BOOTS || event.getWhoClicked().getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS) {
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 12));
                    event.getWhoClicked().sendMessage("§a你购买了 §6永久的铁护甲");
                    event.getWhoClicked().getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                    ShopItem item = new ShopItem();
                    event.getInventory().setItem(event.getSlot(), item.getItem("铁护甲", Bukkit.getPlayer(event.getWhoClicked().getName())));
                    event.getWhoClicked().getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                    Bukkit.getPlayer(event.getWhoClicked().getName()).updateInventory();
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                } else {
                    if (event.getWhoClicked().getInventory().getBoots().getType() == Material.IRON_BOOTS) {
                        event.getWhoClicked().sendMessage("§c你已经拥有了此护甲！");
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                    } else {
                        event.getWhoClicked().sendMessage("§c你已经拥有了更高等级的护甲！");
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                    }
                }
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");

                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("钻石护甲")) {
            event.setCancelled(true);
            if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 4)) {

                if (event.getWhoClicked().getInventory().getBoots().getType() == Material.LEATHER_BOOTS || event.getWhoClicked().getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS || event.getWhoClicked().getInventory().getBoots().getType() == Material.IRON_BOOTS) {
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 4));
                    event.getWhoClicked().sendMessage("§a你购买了 §6永久的钻石护甲");
                    event.getWhoClicked().getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                    ShopItem item = new ShopItem();
                    event.getInventory().setItem(event.getSlot(), item.getItem("钻石护甲", Bukkit.getPlayer(event.getWhoClicked().getName())));
                    event.getWhoClicked().getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                    Bukkit.getPlayer(event.getWhoClicked().getName()).updateInventory();
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                } else {
                    if (event.getWhoClicked().getInventory().getBoots().getType() == Material.DIAMOND_BOOTS) {
                        event.getWhoClicked().sendMessage("§c你已经拥有了此护甲！");
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                    }
                }
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("剪刀")) {
            event.setCancelled(true);
            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 20)) {
                if (!BedWars.shears.get(event.getWhoClicked().getName())) {
                    ItemStack item = new ItemStack(Material.SHEARS, 1);
                    ItemMeta itemmata = item.getItemMeta();
                    item.setItemMeta(itemmata);
                    event.getWhoClicked().getInventory().addItem(item);
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 20));
                    BedWars.shears.replace(event.getWhoClicked().getName(), true);
                    event.getWhoClicked().sendMessage("§a你购买了 §6剪刀");
                    ShopItem shopitem = new ShopItem();
                    event.getInventory().setItem(event.getSlot(), shopitem.getItem("剪刀", Bukkit.getPlayer(event.getWhoClicked().getName())));

                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                } else {
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                    event.getWhoClicked().sendMessage("§c你已经拥有此物品了！");
                }
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");

                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }


        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("钻石镐")) {
            event.setCancelled(true);
            ShopItem shopitem = new ShopItem();
            if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 6)) {
                if (BedWars.pickaxe.get(event.getWhoClicked().getName()) == 4) {

                    event.getWhoClicked().sendMessage("§c此工具已到达最高等级！");

                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                } else {
                    ItemStack olditem = new ItemStack(Material.GOLD_PICKAXE, 1);
                    ItemMeta olditemmata = olditem.getItemMeta();
                    olditemmata.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
                    olditemmata.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    olditem.setItemMeta(olditemmata);
                    event.getWhoClicked().getInventory().removeItem(olditem);
                    ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
                    BedWars.pickaxe.replace(event.getWhoClicked().getName(), 4);
                    ItemMeta itemmata = item.getItemMeta();
                    itemmata.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
                    itemmata.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    item.setItemMeta(itemmata);
                    event.getWhoClicked().getInventory().addItem(item);
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 6));
                    event.getWhoClicked().sendMessage("§a你购买了 §6钻石镐");
                    event.getInventory().setItem(event.getSlot(), shopitem.getItem("镐", Bukkit.getPlayer(event.getWhoClicked().getName())));
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                }
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");

                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }

        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("木镐")) {
            event.setCancelled(true);
            ShopItem shopitem = new ShopItem();
            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 10)) {
                ItemStack item = new ItemStack(Material.WOOD_PICKAXE, 1);
                BedWars.pickaxe.replace(event.getWhoClicked().getName(), 1);
                ItemMeta itemmata = item.getItemMeta();
                itemmata.addEnchant(Enchantment.DIG_SPEED, 1, true);
                item.setItemMeta(itemmata);
                event.getWhoClicked().getInventory().addItem(item);
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 10));
                event.getWhoClicked().sendMessage("§a你购买了 §6木镐");
                event.getInventory().setItem(event.getSlot(), shopitem.getItem("镐", Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");

                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("金镐")) {
            event.setCancelled(true);
            ShopItem shopitem = new ShopItem();
            if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 3)) {
                ItemStack olditem = new ItemStack(Material.IRON_PICKAXE, 1);
                ItemMeta olditemmata = olditem.getItemMeta();
                olditemmata.addEnchant(Enchantment.DIG_SPEED, 2, true);
                olditem.setItemMeta(olditemmata);
                event.getWhoClicked().getInventory().removeItem(olditem);
                ItemStack item = new ItemStack(Material.GOLD_PICKAXE, 1);
                BedWars.pickaxe.replace(event.getWhoClicked().getName(), 3);
                ItemMeta itemmata = item.getItemMeta();
                itemmata.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
                itemmata.addEnchant(Enchantment.DIG_SPEED, 3, true);
                item.setItemMeta(itemmata);
                event.getWhoClicked().getInventory().addItem(item);
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 3));
                event.getWhoClicked().sendMessage("§a你购买了 §6金镐");
                event.getInventory().setItem(event.getSlot(), shopitem.getItem("镐", Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");

                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("铁镐")) {
            event.setCancelled(true);
            ShopItem shopitem = new ShopItem();
            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 10)) {

                ItemStack olditem = new ItemStack(Material.WOOD_PICKAXE, 2);
                ItemMeta olditemmata = olditem.getItemMeta();
                olditemmata.addEnchant(Enchantment.DIG_SPEED, 1, true);
                olditem.setItemMeta(olditemmata);
                event.getWhoClicked().getInventory().removeItem(olditem);
                ItemStack item = new ItemStack(Material.IRON_PICKAXE, 1);
                BedWars.pickaxe.replace(event.getWhoClicked().getName(), 2);
                ItemMeta itemmata = item.getItemMeta();
                itemmata.addEnchant(Enchantment.DIG_SPEED, 2, true);
                item.setItemMeta(itemmata);
                event.getWhoClicked().getInventory().addItem(item);
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 10));
                event.getWhoClicked().sendMessage("§a你购买了 §6铁镐");
                event.getInventory().setItem(event.getSlot(), shopitem.getItem("镐", Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");

                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("铁斧")) {
            event.setCancelled(true);
            ShopItem shopitem = new ShopItem();
            if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 3)) {
                ItemStack olditem = new ItemStack(Material.STONE_AXE, 1);
                ItemMeta olditemmata = olditem.getItemMeta();
                olditemmata.addEnchant(Enchantment.DIG_SPEED, 1, true);
                olditem.setItemMeta(olditemmata);
                event.getWhoClicked().getInventory().removeItem(olditem);
                ItemStack item = new ItemStack(Material.IRON_AXE, 1);
                BedWars.axe.replace(event.getWhoClicked().getName(), 3);
                ItemMeta itemmata = item.getItemMeta();
                itemmata.addEnchant(Enchantment.DIG_SPEED, 2, true);
                item.setItemMeta(itemmata);
                event.getWhoClicked().getInventory().addItem(item);
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 3));
                event.getWhoClicked().sendMessage("§a你购买了 §6铁斧");
                event.getInventory().setItem(event.getSlot(), shopitem.getItem("斧", Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");

                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("石斧")) {
            event.setCancelled(true);
            ShopItem shopitem = new ShopItem();
            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 10)) {

                ItemStack olditem = new ItemStack(Material.WOOD_AXE, 2);
                ItemMeta olditemmata = olditem.getItemMeta();
                olditemmata.addEnchant(Enchantment.DIG_SPEED, 1, true);
                olditem.setItemMeta(olditemmata);
                event.getWhoClicked().getInventory().removeItem(olditem);
                ItemStack item = new ItemStack(Material.STONE_AXE, 1);
                BedWars.axe.replace(event.getWhoClicked().getName(), 2);
                ItemMeta itemmata = item.getItemMeta();
                itemmata.addEnchant(Enchantment.DIG_SPEED, 2, true);
                item.setItemMeta(itemmata);
                event.getWhoClicked().getInventory().addItem(item);
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 10));
                event.getWhoClicked().sendMessage("§a你购买了 §6石斧");
                event.getInventory().setItem(event.getSlot(), shopitem.getItem("斧", Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");

                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }

        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("木斧")) {
            event.setCancelled(true);
            ShopItem shopitem = new ShopItem();
            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 10)) {
                ItemStack item = new ItemStack(Material.WOOD_AXE, 1);
                BedWars.axe.replace(event.getWhoClicked().getName(), 1);
                ItemMeta itemmata = item.getItemMeta();
                itemmata.addEnchant(Enchantment.DIG_SPEED, 1, true);
                item.setItemMeta(itemmata);
                event.getWhoClicked().getInventory().addItem(item);
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 10));
                event.getWhoClicked().sendMessage("§a你购买了 §6木斧");
                event.getInventory().setItem(event.getSlot(), shopitem.getItem("斧", Bukkit.getPlayer(event.getWhoClicked().getName())));
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");

                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("钻石斧")) {
            event.setCancelled(true);
            ShopItem shopitem = new ShopItem();
            if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 6)) {
                if (BedWars.axe.get(event.getWhoClicked().getName()) == 4) {

                    event.getWhoClicked().sendMessage("§c此工具已到达最高等级！");

                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                } else {
                    ItemStack olditem = new ItemStack(Material.IRON_AXE, 1);
                    ItemMeta olditemmata = olditem.getItemMeta();
                    olditemmata.addEnchant(Enchantment.DIG_SPEED, 2, true);
                    olditem.setItemMeta(olditemmata);
                    event.getWhoClicked().getInventory().removeItem(olditem);
                    ItemStack item = new ItemStack(Material.DIAMOND_AXE);
                    BedWars.axe.replace(event.getWhoClicked().getName(), 4);
                    ItemMeta itemmata = item.getItemMeta();
                    itemmata.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
                    itemmata.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    item.setItemMeta(itemmata);
                    event.getWhoClicked().getInventory().addItem(item);
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 6));
                    event.getWhoClicked().sendMessage("§a你购买了 §6钻石斧");
                    event.getInventory().setItem(event.getSlot(), shopitem.getItem("斧", Bukkit.getPlayer(event.getWhoClicked().getName())));
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                }
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");

                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }

        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("箭")) {
            if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 2)) {
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.ARROW, 8));
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 2));
                event.getWhoClicked().sendMessage("§a你购买了 §6箭");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("弓")) {
            if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("力量 I")) {
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("冲击 I")) {
                    if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 4)) {
                        ItemStack item = new ItemStack(Material.BOW, 1);
                        ItemMeta meta = item.getItemMeta();
                        meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
                        meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
                        item.setItemMeta(meta);
                        event.getWhoClicked().getInventory().addItem(item);
                        event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 4));
                        event.getWhoClicked().sendMessage("§a你购买了 §6弓 (力量 I 冲击 I)");
                        event.setCancelled(true);
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                    } else {
                        event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                        event.setCancelled(true);
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                    }
                } else {
                    if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 24)) {
                        ItemStack item = new ItemStack(Material.BOW, 1);
                        ItemMeta meta = item.getItemMeta();
                        meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
                        item.setItemMeta(meta);
                        event.getWhoClicked().getInventory().addItem(item);
                        event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 24));
                        event.getWhoClicked().sendMessage("§a你购买了 §6弓 (力量 I)");
                        event.setCancelled(true);
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                    } else {
                        event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                        event.setCancelled(true);
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                    }
                }
            } else {
                if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 12)) {
                    event.getWhoClicked().getInventory().addItem(new ItemStack(Material.BOW, 1));
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 12));
                    event.getWhoClicked().sendMessage("§a你购买了 §6弓");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                } else {
                    event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            }

        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("速度药水")) {
            if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 1)) {
                ItemStack item = new ItemStack(Material.POTION, 1);
                PotionMeta imm = (PotionMeta) item.getItemMeta();
                imm.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 900, 2), true);
                item.setItemMeta(imm);
                event.getWhoClicked().getInventory().addItem(item);
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 1));
                event.getWhoClicked().sendMessage("§a你购买了 §6速度药水 (45 秒)");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("跳跃药水")) {
            if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 1)) {
                ItemStack item = new ItemStack(Material.POTION, 1);
                PotionMeta imm = (PotionMeta) item.getItemMeta();
                imm.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 900, 5), true);
                item.setItemMeta(imm);
                event.getWhoClicked().getInventory().addItem(item);
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 1));
                event.getWhoClicked().sendMessage("§a你购买了 §6速度药水 (45 秒)");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("隐身药水")) {
            if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 2)) {
                ItemStack item = new ItemStack(Material.POTION, 1);
                PotionMeta imm = (PotionMeta) item.getItemMeta();
                imm.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 600, 0), true);
                item.setItemMeta(imm);
                event.getWhoClicked().getInventory().addItem(item);
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 2));
                event.getWhoClicked().sendMessage("§a你购买了 §6隐身药水 (30 秒)");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("金苹果")) {
            if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 3)) {
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 1));
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 3));
                event.getWhoClicked().sendMessage("§a你购买了 §6金苹果");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("蠹虫")) {
            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 40)) {
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.SNOW_BALL, 1));
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 40));
                event.getWhoClicked().sendMessage("§a你购买了 §6蠹虫");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("梦幻守卫")) {
            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 120)) {
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.MONSTER_EGG, 1));
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 120));
                event.getWhoClicked().sendMessage("§a你购买了 §6梦幻守卫");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("火球")) {
            if (event.getWhoClicked().getInventory().contains(Material.IRON_INGOT, 40)) {
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.FIREBALL, 1));
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 40));
                event.getWhoClicked().sendMessage("§a你购买了 §6火球");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("TNT")) {
            FileConfiguration config = BedWars.getPlugin(BedWars.class).getConfig();
            if (config.getString("Map.ModeType").equalsIgnoreCase("单人") || config.getString("Map.ModeType").equalsIgnoreCase("双人")) {
                if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 4)) {
                    event.getWhoClicked().getInventory().addItem(new ItemStack(Material.TNT, 1));
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 4));
                    event.getWhoClicked().sendMessage("§a你购买了 §6TNT");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                } else {
                    event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            } else {
                if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 8)) {
                    event.getWhoClicked().getInventory().addItem(new ItemStack(Material.TNT, 1));
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 8));
                    event.getWhoClicked().sendMessage("§a你购买了 §6TNT");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                } else {
                    event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("末影珍珠")) {
            if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 4)) {
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 4));
                event.getWhoClicked().sendMessage("§a你购买了 §6末影珍珠");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("水桶")) {
            if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 4)) {
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.WATER_BUCKET, 1));
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 4));
                event.getWhoClicked().sendMessage("§a你购买了 §6水桶");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("搭桥蛋")) {
            FileConfiguration config = BedWars.getPlugin(BedWars.class).getConfig();
            if (config.getString("Map.ModeType").equalsIgnoreCase("单人") || config.getString("Map.ModeType").equalsIgnoreCase("双人")) {
                if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 1)) {
                    event.getWhoClicked().getInventory().addItem(new ItemStack(Material.EGG, 1));
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 1));
                    event.getWhoClicked().sendMessage("§a你购买了 §6搭桥蛋");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                } else {
                    event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            } else {
                if (event.getWhoClicked().getInventory().contains(Material.EMERALD, 2)) {
                    event.getWhoClicked().getInventory().addItem(new ItemStack(Material.EGG, 1));
                    event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.EMERALD, 2));
                    event.getWhoClicked().sendMessage("§a你购买了 §6搭桥蛋");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
                } else {
                    event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                    event.setCancelled(true);
                    Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("魔法牛奶")) {
            if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 4)) {
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.MILK_BUCKET, 1));
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 4));
                event.getWhoClicked().sendMessage("§a你购买了 §6魔法牛奶");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
        if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("海绵")) {
            if (event.getWhoClicked().getInventory().contains(Material.GOLD_INGOT, 4)) {
                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.SPONGE, 1));
                event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 4));
                event.getWhoClicked().sendMessage("§a你购买了 §6魔法牛奶");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_PLING, 1, 24);
            } else {
                event.getWhoClicked().sendMessage("§c你没有足够的资源购买该物品！");
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
    }
    public void update(Player p, String title) {
        EntityPlayer ep = ((CraftPlayer)p).getHandle();
        PacketPlayOutOpenWindow packet = new PacketPlayOutOpenWindow(ep.activeContainer.windowId, "minecraft:chest", new ChatMessage(title), p.getOpenInventory().getTopInventory().getSize());
        ep.playerConnection.sendPacket(packet);
        ep.updateInventory(ep.activeContainer);
    }
}