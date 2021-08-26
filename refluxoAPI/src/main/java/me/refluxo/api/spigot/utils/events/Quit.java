package me.refluxo.api.spigot.utils.events;

import me.refluxo.api.spigot.utils.pets.pet.Pet;
import me.refluxo.api.spigot.utils.player.APIPlayer;
import me.refluxo.api.spigot.utils.player.Share;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage("");
        APIPlayer bp = new APIPlayer(e.getPlayer());
        if(Share.enabled) {
            bp.getPlayerShare().saveAll();
        }
        Pet pet = new Pet(e.getPlayer());
        pet.deSpawnPet();
    }

}
