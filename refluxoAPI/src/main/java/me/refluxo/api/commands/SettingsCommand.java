package me.refluxo.api.commands;

import me.refluxo.api.RefluxoAPI;
import me.refluxo.api.utils.inventory.InventoryBuilder;
import me.refluxo.api.utils.inventory.enums.SettingsInventoryTypes;
import me.refluxo.api.utils.player.APIPlayer;
import me.refluxo.api.utils.player.Language;
import me.refluxo.api.utils.server.SettingsManager;
import me.refluxo.api.utils.server.local.ItemUtil;
import me.refluxo.api.utils.server.settings.enums.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import static me.refluxo.api.utils.server.settings.enums.Values.EVERY;

public class SettingsCommand extends Language implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        p.openInventory(getInventory(new APIPlayer(p), SettingsInventoryTypes.MAIN));
        return false;
    }

    private Inventory getInventory(APIPlayer apiPlayer, SettingsInventoryTypes type) {
        if(type == SettingsInventoryTypes.MAIN) {
            InventoryBuilder builder = new InventoryBuilder("§b§lRe§f§lfluxo§c§lSettings", 3*9);
            SettingsManager settingsManager = new SettingsManager();
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§a§lGlobale Einstellungen", Material.ENDER_CHEST, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            switch (settingsManager.getSettingValue(apiPlayer, Settings.NavigatorInHotbar)) {
                case YES:
                    builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.NavigatorInHotbar), Material.COMPASS, "\n§7Status: " + settingsManager.getSettingStatusMessage(apiPlayer, Settings.NavigatorInHotbar) + "\n").setEnchanted(true).buildItem());
                    builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
                    break;
                case NO:
                    builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.NavigatorInHotbar), Material.COMPASS, "\n§7Status: " + settingsManager.getSettingStatusMessage(apiPlayer, Settings.NavigatorInHotbar) + "\n").buildItem());
                    builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
                    break;
            }
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§6§lLokale Einstellungen", Material.CHEST, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            return builder.buildInventory();
        } else if(type ==SettingsInventoryTypes.GLOBAL) {
            InventoryBuilder builder = new InventoryBuilder("§b§lRe§f§lfluxo§c§lSettings", 3*9);
            SettingsManager settingsManager = new SettingsManager();
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            switch (settingsManager.getSettingValue( apiPlayer, Settings.ChatAds)) {
                case EVERY:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.ChatAds), Material.PAPER, "\n§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds)+"\n").setEnchanted(true).buildItem());
                    break;
                case SERVER:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.ChatAds), Material.PAPER, "\n§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds)+"\n").setEnchanted(true).buildItem());
                    break;
                case PLAYERS:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.ChatAds), Material.PAPER, "\n§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds)+"\n").setEnchanted(true).buildItem());
                    break;
                case NONE:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.ChatAds), Material.PAPER, "\n§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds)+"\n").buildItem());
                    break;
            }

            switch (settingsManager.getSettingValue( apiPlayer, Settings.InventoryAnimations)) {
                case YES:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.InventoryAnimations), Material.CHEST, "\n§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds)+"\n").setEnchanted(true).buildItem());
                    break;
                case NO:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.InventoryAnimations), Material.CHEST, "\n§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds)+"\n").buildItem());
                    break;
            }

            switch (settingsManager.getSettingValue( apiPlayer, Settings.Notifications)) {
                case EVERY:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.Notifications), Material.ENDER_EYE, "\n§7Status: " + settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds) + "\n").setEnchanted(true).buildItem());
                    break;
                case SERVER:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.Notifications), Material.ENDER_EYE, "\n§7Status: " + settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds) + "\n").setEnchanted(true).buildItem());
                    break;
                case ANNOUNCEMENTS:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.Notifications), Material.ENDER_EYE, "\n§7Status: " + settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds) + "\n").setEnchanted(true).buildItem());
                    break;
                case NONE:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.Notifications), Material.ENDER_PEARL, "\n§7Status: " + settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds) + "\n").buildItem());
                    break;
            }

            switch (settingsManager.getSettingValue( apiPlayer, Settings.Particles)) {
                case EVERY:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.Particles), Material.EXPERIENCE_BOTTLE, "\n§7Status: " + settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds) + "\n").setEnchanted(true).buildItem());
                    break;
                case LESS:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.Particles), Material.EXPERIENCE_BOTTLE, "\n§7Status: " + settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds) + "\n").setEnchanted(true).buildItem());
                    break;
                case NONE:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.Particles), Material.EXPERIENCE_BOTTLE, "\n§7Status: " + settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds) + "\n").buildItem());
                    break;
            }

            switch (settingsManager.getSettingValue( apiPlayer, Settings.Sound)) {
                case YES:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.Sound), Material.MUSIC_DISC_13, "\n§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds)+"\n").setEnchanted(true).buildItem());
                    break;
                case NO:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.Sound), Material.MUSIC_DISC_13, "\n§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds)+"\n").buildItem());
                    break;
            }

            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            return builder.buildInventory();
        } else if(type == SettingsInventoryTypes.LOCAL) {
            InventoryBuilder builder = new InventoryBuilder("§b§lRe§f§lfluxo§c§lSettings", InventoryType.DISPENSER);
            SettingsManager settingsManager = new SettingsManager();
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            switch (settingsManager.getSettingValue(apiPlayer, Settings.NavigatorInHotbar)) {
                case YES:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.NavigatorInHotbar), Material.COMPASS, "\n§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.NavigatorInHotbar)+"\n").setEnchanted(true).buildItem());
                    break;
                case NO:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.NavigatorInHotbar), Material.COMPASS, "\n§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.NavigatorInHotbar)+"\n").buildItem());
                    break;
            }


            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());

            switch (settingsManager.getSettingValue(apiPlayer, Settings.AutomaticTeleportToSpawnOnJoin)) {
                case YES:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.AutomaticTeleportToSpawnOnJoin), Material.BEACON, "§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.AutomaticTeleportToSpawnOnJoin)+"\n").setEnchanted(true).buildItem());
                    break;
                case NO:
                    builder.addItemToInventory(new ItemUtil(settingsManager.getSettingDisplayName(Settings.AutomaticTeleportToSpawnOnJoin), Material.BEACON, "§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.AutomaticTeleportToSpawnOnJoin)+"\n").buildItem());
                    break;
            }

            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            return builder.buildInventory();
        } else {
            return Bukkit.createInventory(null, 3*9, "§c§lERROR");
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase("§b§lRe§f§lfluxo§c§lSettings")) {
            if(!e.getView().getTopInventory().contains(new ItemUtil("§a§lGlobale Einstellungen", Material.ENDER_CHEST, "").buildItem()) && !e.getView().getBottomInventory().contains(new ItemUtil("§a§lGlobale Einstellungen", Material.ENDER_CHEST, "").buildItem())) {
                Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                    e.getPlayer().openInventory(getInventory(new APIPlayer((Player) e.getPlayer()), SettingsInventoryTypes.MAIN));
                }, 20);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getCurrentItem() != null) {
            if(e.getView().getTitle().equalsIgnoreCase("§b§lRe§f§lfluxo§c§lSettings")) {
                e.setCancelled(true);
                String dName = e.getCurrentItem().getI18NDisplayName();
                Player p = (Player) e.getWhoClicked();
                APIPlayer apiPlayer = new APIPlayer(p);
                SettingsManager settingsManager = new SettingsManager();
                if(dName.equalsIgnoreCase("§a§lGlobale Einstellungen")) {
                    p.closeInventory();
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        p.openInventory(getInventory(new APIPlayer(p), SettingsInventoryTypes.GLOBAL));
                    }, 8);
                } else if(dName.equalsIgnoreCase("§6§lLokale Einstellungen")) {
                    p.closeInventory();
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        p.openInventory(getInventory(new APIPlayer(p), SettingsInventoryTypes.LOCAL));
                    }, 8);
                } else if(dName.equalsIgnoreCase(settingsManager.getSettingDisplayName(Settings.AllowMessages))) {
                    p.closeInventory();
                    settingsManager.toggleSetting(apiPlayer, Settings.AllowMessages);
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        p.openInventory(getInventory(apiPlayer, SettingsInventoryTypes.GLOBAL));
                    }, 8);
                } else if(dName.equalsIgnoreCase(settingsManager.getSettingDisplayName(Settings.))) {
                    p.closeInventory();
                    settingsManager.toggleSetting(apiPlayer, Settings.);
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        p.openInventory(getInventory(apiPlayer, SettingsInventoryTypes.));
                    }, 8);
                }
            }
        }
    }
    /*
                else if(dName.equalsIgnoreCase(settingsManager.getSettingDisplayName(Settings.))) {
                    p.closeInventory();
                    settingsManager.toggleSetting(apiPlayer, Settings.);
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        p.openInventory(getInventory(apiPlayer, SettingsInventoryTypes.));
                    }, 8);
                }
     */
}
