package me.refluxo.api.utils.inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryBuilder {

    private final String title;
    private final int size;
    private final InventoryType type;
    private final ItemStack[] items;

    public InventoryBuilder(String displayName, int size) {
        title = displayName;
        this.size = size;
        type = null;
        items = new ItemStack[size];
    }

    public InventoryBuilder(String displayName, InventoryType type) {
        title = displayName;
        this.size = 0;
        this.type = type;
        items = new ItemStack[InventoryType.HOPPER.getDefaultSize()];
    }

    public InventoryBuilder addItemToInventory(ItemStack item) {
        int i = items.length;
        if(items == null) {
            items[0] = item;
            return this;
        }
        items[i++] = item;
        return this;
    }

    public Inventory buildInventory() {
        if(type==null) {
            Inventory inv = Bukkit.createInventory(null, size, title);
            inv.setContents(items);
            return inv;
        } else {
            Inventory inv = Bukkit.createInventory(null, type, title);
            inv.setContents(items);
            return inv;
        }
    }

}
