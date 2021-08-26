package me.refluxo.api.spigot.utils.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

public class CommandTab implements Listener {

    @EventHandler
    public void onTab(PlayerChatTabCompleteEvent e) {
        if(!e.getPlayer().hasPermission("api.command.tab.bypass")) {
            e.getTabCompletions().clear();
            for(Player p : Bukkit.getOnlinePlayers()) {
                e.getTabCompletions().add(p.getName());
            }
        }
    }

}
