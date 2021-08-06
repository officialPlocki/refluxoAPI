package me.refluxo.api.commands;

import me.refluxo.api.RefluxoAPI;
import me.refluxo.api.utils.player.APIPlayer;
import me.refluxo.api.utils.player.EMSPlayer;
import me.refluxo.api.utils.player.Language;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EMSCommand extends Language implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Inventory inv = Bukkit.createInventory(null, 5*9, "§b§lRe§f§lfluxo§e§lEMS");
        ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName("§a §k");
        glass.setItemMeta(glassMeta);
        for(int i = 0; i<5*9; i++) {
            inv.setItem(i, glass);
        }
        ItemStack blocked = new ItemStack(Material.BARRIER);
        ItemMeta blockedMeta = blocked.getItemMeta();
        blockedMeta.setDisplayName("§8» §c§lBereits gekauft §8«");
        blocked.setItemMeta(blockedMeta);
        APIPlayer apiPlayer = new APIPlayer((Player) commandSender);
        EMSPlayer ems = new EMSPlayer(apiPlayer);
        ItemStack info = new ItemStack(Material.GOLD_INGOT);
        ItemMeta infoMeta = info.getItemMeta();
        infoMeta.setDisplayName("§8» §6§lInfo §8«");
        List<String> infoLore = new ArrayList<>();
        infoLore.add("§e");
        infoLore.add("§8» §aLevel: §e" + ems.getLevel());
        String nextlevel;
        if(ems.getNextLevel()==6) {
            nextlevel = "§c§lMAXIMAL";
        } else {
            nextlevel = ""+ems.getNextLevel();
        }
        infoLore.add("§8» §aAktuelle Bit-Gen: §e" + ems.getBitGen() + " /§b10 Min");
        infoLore.add("§8» §aNächstes Level: §e" + nextlevel);
        infoLore.add("§8» §aKosten für nächstes Level: §e" + ems.getNextCost() + " Bits");
        infoLore.add("§e");
        infoMeta.setLore(infoLore);
        info.setItemMeta(infoMeta);
        inv.setItem(20, info);
        ItemStack upgrade = new ItemStack(Material.PAPER);
        ItemMeta upgradeMeta = upgrade.getItemMeta();
        upgradeMeta.setDisplayName("§8» §a§lUpgrades §8«");
        upgrade.setItemMeta(upgradeMeta);
        inv.setItem(24, upgrade);
        apiPlayer.getPlayer().openInventory(inv);
        return false;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        APIPlayer apiPlayer = new APIPlayer((Player) e.getWhoClicked());
        EMSPlayer ems = new EMSPlayer(apiPlayer);
        if(e.getCurrentItem() != null) {
            if(e.getView().getTitle().equalsIgnoreCase("§b§lRe§f§lfluxo§e§lEMS")) {
                e.setCancelled(true);
                String itemName = e.getCurrentItem().getItemMeta().getDisplayName();
                if(itemName.equalsIgnoreCase("§bLevel 1")) {
                    if(apiPlayer.getBits()==7500||apiPlayer.getBits()>=7500) {
                        ems.setLevel(1);
                        e.getWhoClicked().closeInventory();
                        apiPlayer.removeBits(7500);
                        e.getWhoClicked().sendMessage(apiprefix + "Du hast dir erfolgreich das EMS Level 1 für 7500 Bits freigeschaltet.");
                    } else {
                        e.getWhoClicked().sendMessage(apiprefix + "Du hast nicht genügend Bits.");
                    }
                } else if(itemName.equalsIgnoreCase("§bLevel 2")) {
                    if(apiPlayer.getBits()==15000||apiPlayer.getBits()>=15000) {
                        ems.setLevel(2);
                        e.getWhoClicked().closeInventory();
                        apiPlayer.removeBits(15000);
                        e.getWhoClicked().sendMessage(apiprefix + "Du hast dir erfolgreich das EMS Level 2 für 15000 Bits freigeschaltet.");
                    } else {
                        e.getWhoClicked().sendMessage(apiprefix + "Du hast nicht genügend Bits.");
                    }
                } else if(itemName.equalsIgnoreCase("§bLevel 3")) {
                    if(apiPlayer.getBits()==30000||apiPlayer.getBits()>=30000) {
                        ems.setLevel(3);
                        e.getWhoClicked().closeInventory();
                        apiPlayer.removeBits(30000);
                        e.getWhoClicked().sendMessage(apiprefix + "Du hast dir erfolgreich das EMS Level 3 für 30000 Bits freigeschaltet.");
                    } else {
                        e.getWhoClicked().sendMessage(apiprefix + "Du hast nicht genügend Bits.");
                    }
                } else if(itemName.equalsIgnoreCase("§bLevel 4")) {
                    if(apiPlayer.getBits()==60000||apiPlayer.getBits()>=60000) {
                        ems.setLevel(4);
                        e.getWhoClicked().closeInventory();
                        apiPlayer.removeBits(60000);
                        e.getWhoClicked().sendMessage(apiprefix + "Du hast dir erfolgreich das EMS Level 4 für 60000 Bits freigeschaltet.");
                    } else {
                        e.getWhoClicked().sendMessage(apiprefix + "Du hast nicht genügend Bits.");
                    }
                } else if(itemName.equalsIgnoreCase("§bLevel 5")) {
                    if(apiPlayer.getBits()==120000||apiPlayer.getBits()>=120000) {
                        ems.setLevel(5);
                        e.getWhoClicked().closeInventory();
                        apiPlayer.removeBits(120000);
                        e.getWhoClicked().sendMessage(apiprefix + "Du hast dir erfolgreich das EMS Level 5 für 120000 Bits freigeschaltet.");
                    } else {
                        e.getWhoClicked().sendMessage(apiprefix + "Du hast nicht genügend Bits.");
                    }
                } else if(itemName.equalsIgnoreCase("§8» §a§lUpgrades §8«")) {
                    e.getWhoClicked().closeInventory();
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> {
                        Inventory invnew = Bukkit.createInventory(null, 5*9, "§b§lRe§f§lfluxo§e§lEMS");
                        ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                        ItemMeta glassMeta = glass.getItemMeta();
                        glassMeta.setDisplayName("§a §k");
                        glass.setItemMeta(glassMeta);
                        for(int i = 0; i<5*9; i++) {
                            invnew.setItem(i, glass);
                        }
                        ItemStack blocked = new ItemStack(Material.BARRIER);
                        ItemMeta blockedMeta = blocked.getItemMeta();
                        blockedMeta.setDisplayName("§8» §c§lBereits gekauft §8«");
                        blocked.setItemMeta(blockedMeta);
                        int gen = 0;
                        int upgradea = 0;
                        int cost = 0;
                        for(int i = 20; i<25; i++) {
                            upgradea++;
                            if(gen==0) {
                                gen = 4;
                            } else {
                                gen = gen*2;
                            }
                            if(cost==0) {
                                cost = 7500;
                            } else {
                                cost = cost*2;
                            }
                            int costtmp = cost;
                            if(upgradea == ems.getNextLevel()) {
                                cost = ems.getNextCost();
                            }
                            ItemStack u = new ItemStack(Material.PAPER);
                            ItemMeta uMeta = u.getItemMeta();
                            uMeta.setDisplayName("§bLevel "+upgradea);
                            List<String> uLore = new ArrayList<>();
                            uLore.add("§e");
                            uLore.add("§8» §aKosten: §e" + cost);
                            uLore.add("§8» §aBit-Gen Rate: §e" + gen);
                            uLore.add("§e");
                            if(upgradea == ems.getNextLevel()) {
                                uLore.add("§cDu erhälst §c§l50% §cRabatt, da du kein Upgrade überspringst.");
                                uLore.add("§e");
                            }
                            uMeta.setLore(uLore);
                            u.setItemMeta(uMeta);
                            invnew.setItem(i, u);
                            cost = costtmp;
                        }
                        if(ems.getLevel()>=1) {
                            invnew.setItem(20, blocked);
                        }
                        if(ems.getLevel()>=2) {
                            invnew.setItem(21, blocked);
                        }
                        if(ems.getLevel()>=3) {
                            invnew.setItem(22, blocked);
                        }
                        if(ems.getLevel()>=4) {
                            invnew.setItem(23, blocked);
                        }
                        if(ems.getLevel()==5) {
                            invnew.setItem(24, blocked);
                        }
                        ((Player) e.getWhoClicked()).openInventory(invnew);
                    }, 20);
                }
            }
        }
    }

}
