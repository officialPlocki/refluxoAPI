package me.refluxo.api.spigot.utils.events;

import me.refluxo.api.spigot.utils.player.APIPlayer;
import me.refluxo.api.spigot.utils.server.VersionChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(e.getMessage().contains(p.getName())) {
                e.getMessage().replace(p.getName(), ChatColor.BLUE+"@"+p.getName()+ChatColor.GRAY);
                if(VersionChecker.getVersion().equalsIgnoreCase("1.17")) {
                    p.playSound(p.getLocation(), "BLOCK_NOTE_BLOCK_PLING", 1, 1);
                } else if(VersionChecker.getVersion().equalsIgnoreCase("1.16")) {
                    p.playSound(p.getLocation(), "BLOCK_NOTE_BLOCK_PLING", 1, 1);
                } else if(VersionChecker.getVersion().equalsIgnoreCase("1.15")) {
                    p.playSound(p.getLocation(), "BLOCK_NOTE_BLOCK_PLING", 1, 1);
                } else if(VersionChecker.getVersion().equalsIgnoreCase("1.12")){
                    p.playSound(p.getLocation(), "BLOCK_NOTE_PLING", 1, 1);
                } else if (VersionChecker.getVersion().equalsIgnoreCase("1.8")){
                    p.playSound(p.getLocation(), "NOTE_PLING", 1, 1);
                }
            }
        }
        if(e.getPlayer().hasPermission("api.chat.color")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('§', new APIPlayer(e.getPlayer()).getSuffix() + new APIPlayer(e.getPlayer()).getPrefix()) + e.getPlayer().getName() + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        } else {
            e.setFormat(ChatColor.translateAlternateColorCodes('§', new APIPlayer(e.getPlayer()).getSuffix() + new APIPlayer(e.getPlayer()).getPrefix()) + e.getPlayer().getName() + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY + e.getMessage());
        }
    }

}
