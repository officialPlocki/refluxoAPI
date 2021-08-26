package me.refluxo.api.spigot.utils.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

public class Command implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if(!e.isCancelled()) {
            Player p = e.getPlayer();
            String msg = e.getMessage().split(" ")[0];
            HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
            if(topic == null) {
                p.sendMessage(Language.noPermissions);
                e.setCancelled(true);
            }
        } else {
            Player p = e.getPlayer();
            String msg = e.getMessage().split(" ")[0];
            HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
            if(topic == null) {
                p.sendMessage(Language.noPermissions);
                e.setCancelled(true);
            }
        }
        if( e.getMessage().equalsIgnoreCase("ver")||
            e.getMessage().equalsIgnoreCase("bukkit:ver")||
            e.getMessage().equalsIgnoreCase("version")||
            e.getMessage().equalsIgnoreCase("bukkit:version")||
            e.getMessage().equalsIgnoreCase("bukkit:help")||
            e.getMessage().equalsIgnoreCase("help")||
            e.getMessage().equalsIgnoreCase("?")||
            e.getMessage().equalsIgnoreCase("bukkit:?")||
            e.getMessage().equalsIgnoreCase("minecraft:me")||
            e.getMessage().equalsIgnoreCase("me")||
            e.getMessage().equalsIgnoreCase("tell")||
            e.getMessage().equalsIgnoreCase("tellraw")||
            e.getMessage().equalsIgnoreCase("minecraft:tell")||
            e.getMessage().equalsIgnoreCase("minecraft:tellraw")||
            e.getMessage().equalsIgnoreCase("/calculate")||
            e.getMessage().equalsIgnoreCase("//calculate")||
            e.getMessage().equalsIgnoreCase("/calc")||
            e.getMessage().equalsIgnoreCase("//calc") ) {
            if(!e.getPlayer().hasPermission("api.command.block.bypass")) {
                 e.setCancelled(true);
                 e.getPlayer().sendMessage(Language.noPermissions);
                 return;
            }
            return;
        } else if( e.getMessage().equalsIgnoreCase("pl")||
                   e.getMessage().equalsIgnoreCase("plugins")||
                   e.getMessage().equalsIgnoreCase("bukkit:pl")||
                   e.getMessage().equalsIgnoreCase("bukkit:plugins") ) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(Language.apiprefix+"Unsere Plugins kannst du unter: https://refluxo.link/plugins einsehen.");
            return;
        } else {
            e.setCancelled(false);
            return;
        }
    }
}
