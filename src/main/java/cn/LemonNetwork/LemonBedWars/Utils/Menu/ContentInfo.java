package cn.lemonnetwork.lemonbedwars.Utils.Menu;

public class ContentInfo {

    private final InventoryButton button;
    private final int slot;

    public ContentInfo(InventoryButton button, int slot) {
        this.button = button;
        this.slot = slot;
    }

    public InventoryButton getButton() {
        return button;
    }

    public int getSlot() {
        return slot;
    }
}
