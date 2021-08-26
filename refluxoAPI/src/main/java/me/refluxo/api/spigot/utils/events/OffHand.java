package me.refluxo.api.spigot.utils.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class OffHand implements Listener {

    @EventHandler
    public void onSwitch(PlayerSwapHandItemsEvent e) {
        if(e.getOffHandItem() == null) {
            e.setCancelled(true);
        }
    }

}
