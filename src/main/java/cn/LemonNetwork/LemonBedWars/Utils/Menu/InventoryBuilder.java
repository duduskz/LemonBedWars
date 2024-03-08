package cn.lemonnetwork.lemonbedwars.Utils.Menu;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryBuilder {

    private final String title;
    private final int size;
    private final List<ContentInfo> info;

    public InventoryBuilder(String title, int size, ContentInfo... info) {
        this.title = title;
        this.size = size;
        this.info = new ArrayList<>(Arrays.asList(info));
    }

    public Inventory build() {
        Inventory inv = Bukkit.createInventory(null, size, title);
        for (ContentInfo i : info) {
            inv.setItem(i.getSlot(), i.getButton().getItemStack());
        }
        return inv;
    }

    public void addContentInfo(ContentInfo... info) {
        this.info.addAll(Arrays.asList(info));
    }

    public List<ContentInfo> getInfo() {
        return info;
    }
}
