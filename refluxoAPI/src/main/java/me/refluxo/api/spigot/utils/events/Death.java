package me.refluxo.api.spigot.utils.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.setDeathMessage("");
    }

}
