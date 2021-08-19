package me.refluxo.api.utils.server.local;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {
    private String displayname;
    private Material material;
    private List<String> lore;
    private ItemStack item;
    private int amount = 1;
    boolean enchanted = false;

    public ItemUtil(final String displayname, ItemStack item, final String lore) {
        this.lore = new ArrayList<String>();
        this.displayname = displayname;
        this.item = item;
        if (lore != null) {
            this.lore.clear();
            this.lore.add(" ");
            this.lore.add(lore);
            this.lore.add("  ");
        }
    }

    public ItemUtil(final String displayname, final Material material, final String lore) {
        this.lore = new ArrayList<String>();
        this.displayname = displayname;
        this.material = material;
        if (lore != null) {
            this.lore.clear();
            this.lore.add(" ");
            this.lore.add(lore);
            this.lore.add("  ");
        }
    }

    public ItemUtil setAmount(int count) {
        amount = count;
        return this;
    }

    public ItemUtil setEnchanted(boolean enabled) {
        enchanted = enabled;
        return this;
    }

    public ItemStack buildItem() {
        ItemStack itemstack = null;
        if(item != null) {
            itemstack = item;
        } else {
            itemstack = new ItemStack(this.material);
        }
        final ItemMeta itemMeta = itemstack.getItemMeta();
        itemMeta.setDisplayName(this.displayname);
        if (this.lore != null) {
            itemMeta.setLore((List)this.lore);
        }
        itemstack.setItemMeta(itemMeta);
        itemstack.setAmount(amount);
        return itemstack;
    }

    public ItemStack buildSkull(final String skullowner) {
        final ItemStack itemstack = new ItemStack(this.material, 1, (short)3);
        final SkullMeta skullMeta = (SkullMeta)itemstack.getItemMeta();
        skullMeta.setDisplayName(this.displayname);
        skullMeta.setOwner(skullowner);
        if (this.lore != null) {
            skullMeta.setLore((List)this.lore);
        }
        itemstack.setItemMeta((ItemMeta)skullMeta);
        itemstack.setAmount(amount);
        return itemstack;
    }
}
