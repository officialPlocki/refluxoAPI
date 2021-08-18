package me.refluxo.api.utils.inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryBuilder {

    private String title;
    private int size;
    private InventoryType type;
    private ItemStack[] items = new ItemStack[size];

    public InventoryBuilder(String displayName, int size) {
        title = displayName;
        this.size = size;
        type = null;
    }

    public InventoryBuilder(String displayName, int size, InventoryType type) {
        title = displayName;
        this.size = size;
        this.type = type;
    }

    public InventoryBuilder addItemToInventory(ItemStack item) {
        int i = items.length;
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
