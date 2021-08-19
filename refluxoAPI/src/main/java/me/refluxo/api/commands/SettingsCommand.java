package me.refluxo.api.commands;

import me.refluxo.api.RefluxoAPI;
import me.refluxo.api.utils.inventory.InventoryBuilder;
import me.refluxo.api.utils.player.APIPlayer;
import me.refluxo.api.utils.player.Language;
import me.refluxo.api.utils.server.SettingsManager;
import me.refluxo.api.utils.server.local.ItemUtil;
import me.refluxo.api.utils.server.settings.enums.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SettingsCommand extends Language implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        APIPlayer apiPlayer = new APIPlayer(p);
        SettingsManager settingsManager = new SettingsManager();
        settingsManager.toggleSetting(apiPlayer, Settings.ChatAds);
        InventoryBuilder builder = new InventoryBuilder("", 3*9);
        ItemStack ChatADs = null;
        String ChatADsName = null;
        String ChatADsDes = null;
        ItemStack InvAni = null;
        String InvAniName = null;
        String InvAniDes = null;
        switch (settingsManager.getSettingValue(apiPlayer, Settings.ChatAds)) {
            case EVERY:
                ChatADs = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
                ChatADsName = "Alle ChatAds";
                ChatADsDes = "\n§e§lAlle ChatADs\n§2Nur ChatADs vom Server\nNur ChatADs von Spielern\nkeine ChatADs\n§bzum ändern klicken!"
                break;
            case SERVER:
                ChatADs = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
                break;
            case PLAYERS:
                ChatADs = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
                break;
            case NONE:
                ChatADs = new ItemStack(Material.RED_STAINED_GLASS_PANE);
                ChatADsName = "§eAktivieren!";
                break;
        }

        switch (settingsManager.getSettingValue(apiPlayer, Settings.InventoryAnimations)) {
            case YES:
                InvAni = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
                break;
            case NO:
                InvAni = new ItemStack(Material.RED_STAINED_GLASS_PANE);
                break;
        }

        Inventory gsettings = builder.addItemToInventory(new ItemUtil("", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                .addItemToInventory(new ItemUtil("", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                .addItemToInventory(new ItemUtil(ChatADsName, ChatADs, ChatADsDes).buildItem())
                .addItemToInventory(new ItemUtil(InvAniName, InvAni, ChatADsDes).buildItem())
                .addItemToInventory()
        return false;
    }


    private Inventory getInventory(String inv) {

        if(inv.equalsIgnoreCase("bla")) {
            Inventory settings = Bukkit.createInventory(null, InventoryType.HOPPER, "");

            return settings;
        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getCurrentItem() != null) {
            if(e.getView().getTitle().equalsIgnoreCase("")) {
                e.setCancelled(true);
                String name = e.getCurrentItem().getDisplayName();
                Player p = (Player) e.getWhoClicked();
                if(name.equalsIgnoreCase("§aGlobale Einstellungen")) {
                    p.closeInventory();
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        p.openInventory(getInventory("bla2"));
                    }, 8);
                }
            }
        }
    }

}
