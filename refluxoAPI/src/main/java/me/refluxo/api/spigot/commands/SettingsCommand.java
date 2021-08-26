package me.refluxo.api.spigot.commands;

import me.refluxo.api.spigot.RefluxoAPI;
import me.refluxo.api.spigot.utils.inventory.InventoryBuilder;
import me.refluxo.api.spigot.utils.inventory.enums.SettingsInventoryTypes;
import me.refluxo.api.spigot.utils.player.APIPlayer;
import me.refluxo.api.spigot.utils.player.Language;
import me.refluxo.api.spigot.utils.server.SettingsManager;
import me.refluxo.api.spigot.utils.server.local.ItemUtil;
import me.refluxo.api.spigot.utils.server.settings.enums.Settings;
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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SettingsCommand extends Language implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        p.openInventory(getInventory(new APIPlayer(p), SettingsInventoryTypes.MAIN));
        return false;
    }

    private Inventory getInventory(APIPlayer apiPlayer, SettingsInventoryTypes type) {
        InventoryBuilder builder = new InventoryBuilder("§b§lRe§f§lfluxo§c§lSettings", 3*9);
        SettingsManager settingsManager = new SettingsManager();
        Map<Integer, ItemStack> items = new HashMap<>();
        if(type == SettingsInventoryTypes.MAIN) {
            items.put(0, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(1, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(2, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(3, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(4, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(5, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(6, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(7, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(8, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(9, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(10, new ItemUtil("§a§lGlobale Einstellungen", Material.ENDER_CHEST, "").buildItem());
            items.put(11, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            switch (settingsManager.getSettingValue(apiPlayer, Settings.NavigatorInHotbar)) {
                case YES:
                    items.put(12, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
                    items.put(13, new ItemUtil(settingsManager.getSettingDisplayName(Settings.NavigatorInHotbar), Material.COMPASS, "§7Status: " + settingsManager.getSettingStatusMessage(apiPlayer, Settings.NavigatorInHotbar) + "").setEnchanted(true).buildItem());
                    items.put(14, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
                    break;
                case NO:
                    items.put(12, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
                    items.put(13, new ItemUtil(settingsManager.getSettingDisplayName(Settings.NavigatorInHotbar), Material.COMPASS, "§7Status: " + settingsManager.getSettingStatusMessage(apiPlayer, Settings.NavigatorInHotbar) + "").buildItem());
                    items.put(14, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
                    break;
            }
            items.put(15, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(16, new ItemUtil("§6§lLokale Einstellungen", Material.CHEST, "").buildItem());
            items.put(17, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(18, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(19, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(20, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(21, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(22, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(23, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(24, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(25, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(26, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
        } else if(type ==SettingsInventoryTypes.GLOBAL) {
            items.put(0, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(1, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(2, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(3, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(4, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(5, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(6, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(7, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(8, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(9, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(10, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            switch (settingsManager.getSettingValue( apiPlayer, Settings.ChatAds)) {
                case EVERY:
                    items.put(11, new ItemUtil(settingsManager.getSettingDisplayName(Settings.ChatAds), Material.PAPER, "§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds)+"").setEnchanted(true).buildItem());
                    break;
                case SERVER:
                    items.put(11, new ItemUtil(settingsManager.getSettingDisplayName(Settings.ChatAds), Material.PAPER, "§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds)+"").setEnchanted(true).buildItem());
                    break;
                case PLAYERS:
                    items.put(11, new ItemUtil(settingsManager.getSettingDisplayName(Settings.ChatAds), Material.PAPER, "§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds)+"").setEnchanted(true).buildItem());
                    break;
                case NONE:
                    items.put(11, new ItemUtil(settingsManager.getSettingDisplayName(Settings.ChatAds), Material.PAPER, "§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.ChatAds)+"").buildItem());
                    break;
            }
            switch (settingsManager.getSettingValue( apiPlayer, Settings.InventoryAnimations)) {
                case YES:
                    items.put(12, new ItemUtil(settingsManager.getSettingDisplayName(Settings.InventoryAnimations), Material.CHEST, "§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.InventoryAnimations)+"").setEnchanted(true).buildItem());
                    break;
                case NO:
                    items.put(12, new ItemUtil(settingsManager.getSettingDisplayName(Settings.InventoryAnimations), Material.CHEST, "§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.InventoryAnimations)+"").buildItem());
                    break;
            }
            switch (settingsManager.getSettingValue( apiPlayer, Settings.Notifications)) {
                case EVERY:
                    items.put(13, new ItemUtil(settingsManager.getSettingDisplayName(Settings.Notifications), Material.ENDER_EYE, "§7Status: " + settingsManager.getSettingStatusMessage(apiPlayer, Settings.Notifications) + "").setEnchanted(true).buildItem());
                    break;
                case SERVER:
                    items.put(13, new ItemUtil(settingsManager.getSettingDisplayName(Settings.Notifications), Material.ENDER_EYE, "§7Status: " + settingsManager.getSettingStatusMessage(apiPlayer, Settings.Notifications) + "").setEnchanted(true).buildItem());
                    break;
                case ANNOUNCEMENTS:
                    items.put(13, new ItemUtil(settingsManager.getSettingDisplayName(Settings.Notifications), Material.ENDER_EYE, "§7Status: " + settingsManager.getSettingStatusMessage(apiPlayer, Settings.Notifications) + "").setEnchanted(true).buildItem());
                    break;
                case NONE:
                    items.put(13, new ItemUtil(settingsManager.getSettingDisplayName(Settings.Notifications), Material.ENDER_PEARL, "§7Status: " + settingsManager.getSettingStatusMessage(apiPlayer, Settings.Notifications) + "").buildItem());
                    break;
            }
            switch (settingsManager.getSettingValue( apiPlayer, Settings.Particles)) {
                case EVERY:
                    items.put(14, new ItemUtil(settingsManager.getSettingDisplayName(Settings.Particles), Material.EXPERIENCE_BOTTLE, "§7Status: " + settingsManager.getSettingStatusMessage(apiPlayer, Settings.Particles) + "").setEnchanted(true).buildItem());
                    break;
                case LESS:
                    items.put(14, new ItemUtil(settingsManager.getSettingDisplayName(Settings.Particles), Material.EXPERIENCE_BOTTLE, "§7Status: " + settingsManager.getSettingStatusMessage(apiPlayer, Settings.Particles) + "").setEnchanted(true).buildItem());
                    break;
                case NONE:
                    items.put(14, new ItemUtil(settingsManager.getSettingDisplayName(Settings.Particles), Material.EXPERIENCE_BOTTLE, "§7Status: " + settingsManager.getSettingStatusMessage(apiPlayer, Settings.Particles) + "").buildItem());
                    break;
            }
            switch (settingsManager.getSettingValue( apiPlayer, Settings.Sound)) {
                case YES:
                    items.put(15, new ItemUtil(settingsManager.getSettingDisplayName(Settings.Sound), Material.MUSIC_DISC_13, "§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.Sound)+"").setEnchanted(true).buildItem());
                    break;
                case NO:
                    items.put(15, new ItemUtil(settingsManager.getSettingDisplayName(Settings.Sound), Material.MUSIC_DISC_13, "§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.Sound)+"").buildItem());
                    break;
            }
            items.put(16, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(17, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(18, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(19, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(20, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(21, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(22, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(23, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(24, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(25, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(26, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
        } else if(type == SettingsInventoryTypes.LOCAL) {
            items.put(0, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(1, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(2, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(3, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(4, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(5, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(6, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(7, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(8, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(9, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            switch (settingsManager.getSettingValue(apiPlayer, Settings.NavigatorInHotbar)) {
                case YES:
                    items.put(10, new ItemUtil(settingsManager.getSettingDisplayName(Settings.NavigatorInHotbar), Material.COMPASS, "§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.NavigatorInHotbar)+"").setEnchanted(true).buildItem());
                    break;
                case NO:
                    items.put(10, new ItemUtil(settingsManager.getSettingDisplayName(Settings.NavigatorInHotbar), Material.COMPASS, "§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.NavigatorInHotbar)+"").buildItem());
                    break;
            }
            items.put(11, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());

            switch (settingsManager.getSettingValue(apiPlayer, Settings.AutomaticTeleportToSpawnOnJoin)) {
                case YES:
                    items.put(12, new ItemUtil(settingsManager.getSettingDisplayName(Settings.AutomaticTeleportToSpawnOnJoin), Material.BEACON, "§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.AutomaticTeleportToSpawnOnJoin)+"").setEnchanted(true).buildItem());
                    break;
                case NO:
                    items.put(12, new ItemUtil(settingsManager.getSettingDisplayName(Settings.AutomaticTeleportToSpawnOnJoin), Material.BEACON, "§7Status: "+settingsManager.getSettingStatusMessage(apiPlayer, Settings.AutomaticTeleportToSpawnOnJoin)+"").buildItem());
                    break;
            }
            items.put(13, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(14, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(15, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(16, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(17, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(18, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(19, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(20, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(21, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(22, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(23, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(24, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(25, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(26, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
        }
        return builder.buildInventory(items);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase("§b§lRe§f§lfluxo§c§lSettings")) {
            if(!e.getView().getTopInventory().contains(new ItemUtil("§a§lGlobale Einstellungen", Material.ENDER_CHEST, "").buildItem()) && !e.getView().getBottomInventory().contains(new ItemUtil("§a§lGlobale Einstellungen", Material.ENDER_CHEST, "").buildItem())) {
                Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                    e.getPlayer().openInventory(getInventory(new APIPlayer((Player) e.getPlayer()), SettingsInventoryTypes.MAIN));
                }, 3);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getCurrentItem() != null) {
            if(e.getView().getTitle().equalsIgnoreCase("§b§lRe§f§lfluxo§c§lSettings")) {
                e.setCancelled(true);
                String dName = Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getDisplayName();
                Player p = (Player) e.getWhoClicked();
                APIPlayer apiPlayer = new APIPlayer(p);
                SettingsManager settingsManager = new SettingsManager();
                if(dName.equalsIgnoreCase("§a§lGlobale Einstellungen")) {
                    p.closeInventory();
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        p.openInventory(getInventory(new APIPlayer(p), SettingsInventoryTypes.GLOBAL));
                    }, 3);
                } else if(dName.equalsIgnoreCase("§6§lLokale Einstellungen")) {
                    p.closeInventory();
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        p.openInventory(getInventory(new APIPlayer(p), SettingsInventoryTypes.LOCAL));
                    }, 3);
                } else if(dName.equalsIgnoreCase(settingsManager.getSettingDisplayName(Settings.AllowMessages))) {
                    p.closeInventory();
                    settingsManager.toggleSetting(apiPlayer, Settings.AllowMessages);
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        p.openInventory(getInventory(apiPlayer, SettingsInventoryTypes.GLOBAL));
                    }, 3);
                } else if(dName.equalsIgnoreCase(settingsManager.getSettingDisplayName(Settings.AllowFriendRequests))) {
                    p.closeInventory();
                    settingsManager.toggleSetting(apiPlayer, Settings.AllowFriendRequests);
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        p.openInventory(getInventory(apiPlayer, SettingsInventoryTypes.GLOBAL));
                    }, 3);
                } else if(dName.equalsIgnoreCase(settingsManager.getSettingDisplayName(Settings.AllowMessagesFromEveryone))) {
                    p.closeInventory();
                    settingsManager.toggleSetting(apiPlayer, Settings.AllowMessagesFromEveryone);
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        p.openInventory(getInventory(apiPlayer, SettingsInventoryTypes.GLOBAL));
                    }, 3);
                } else if(dName.equalsIgnoreCase(settingsManager.getSettingDisplayName(Settings.ChatAds))) {
                    p.closeInventory();
                    settingsManager.toggleSetting(apiPlayer, Settings.ChatAds);
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        p.openInventory(getInventory(apiPlayer, SettingsInventoryTypes.GLOBAL));
                    }, 3);
                } else if(dName.equalsIgnoreCase(settingsManager.getSettingDisplayName(Settings.InventoryAnimations))) {
                    p.closeInventory();
                    settingsManager.toggleSetting(apiPlayer, Settings.InventoryAnimations);
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        p.openInventory(getInventory(apiPlayer, SettingsInventoryTypes.GLOBAL));
                    }, 3);
                } else if(dName.equalsIgnoreCase(settingsManager.getSettingDisplayName(Settings.Notifications))) {
                    p.closeInventory();
                    settingsManager.toggleSetting(apiPlayer, Settings.Notifications);
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        p.openInventory(getInventory(apiPlayer, SettingsInventoryTypes.GLOBAL));
                    }, 3);
                } else if(dName.equalsIgnoreCase(settingsManager.getSettingDisplayName(Settings.Particles))) {
                    p.closeInventory();
                    settingsManager.toggleSetting(apiPlayer, Settings.Particles);
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        p.openInventory(getInventory(apiPlayer, SettingsInventoryTypes.GLOBAL));
                    }, 3);
                } else if(dName.equalsIgnoreCase(settingsManager.getSettingDisplayName(Settings.Sound))) {
                    p.closeInventory();
                    settingsManager.toggleSetting(apiPlayer, Settings.Sound);
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        p.openInventory(getInventory(apiPlayer, SettingsInventoryTypes.GLOBAL));
                    }, 3);
                } else if(dName.equalsIgnoreCase(settingsManager.getSettingDisplayName(Settings.NavigatorInHotbar))) {
                    p.closeInventory();
                    settingsManager.toggleSetting(apiPlayer, Settings.NavigatorInHotbar);
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        if(e.getClickedInventory().contains(new ItemUtil("§a§lGlobale Einstellungen", Material.ENDER_CHEST, "").buildItem())) {
                            p.openInventory(getInventory(apiPlayer, SettingsInventoryTypes.MAIN));
                        } else {
                            p.openInventory(getInventory(apiPlayer, SettingsInventoryTypes.LOCAL));
                        }
                    }, 3);
                } else if(dName.equalsIgnoreCase(settingsManager.getSettingDisplayName(Settings.AutomaticTeleportToSpawnOnJoin))) {
                    p.closeInventory();
                    settingsManager.toggleSetting(apiPlayer, Settings.AutomaticTeleportToSpawnOnJoin);
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        p.openInventory(getInventory(apiPlayer, SettingsInventoryTypes.LOCAL));
                    }, 3);
                }
            }
        }
    }

}
