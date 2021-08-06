package me.refluxo.api.utils.server.local;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {
    public String displayname;
    public Material material;
    public List<String> lore;

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

    public ItemStack buildItem() {
        final ItemStack itemstack = new ItemStack(this.material);
        final ItemMeta itemMeta = itemstack.getItemMeta();
        itemMeta.setDisplayName(this.displayname);
        if (this.lore != null) {
            itemMeta.setLore((List)this.lore);
        }
        itemstack.setItemMeta(itemMeta);
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
        return itemstack;
    }

    public ItemStack buildItemWithShort(final int id) {
        final ItemStack itemstack = new ItemStack(this.material, 1, (short)id);
        final ItemMeta itemMeta = itemstack.getItemMeta();
        itemMeta.setDisplayName(this.displayname);
        if (this.lore != null) {
            itemMeta.setLore((List)this.lore);
        }
        itemstack.setItemMeta(itemMeta);
        return itemstack;
    }
}
