package me.refluxo.api.spigot.utils.pets;

import me.refluxo.api.spigot.RefluxoAPI;
import me.refluxo.api.spigot.utils.pets.pet.Pet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PetMovement {

    public static void init() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(RefluxoAPI.getInstance(), () -> {
            for(Player p : Bukkit.getOnlinePlayers()) {
                Pet pet = new Pet(p);
                if(pet.getPetEntity() == null) {
                    return;
                }
                if(pet.petIsActive()) {
                    Entity e = pet.getPetEntity();
                    if((e.getLocation().distance(p.getLocation())) >= 1.5) {
                        e.teleport(p.getLocation().subtract(0,0.5,0));
                        e.getLocation().setDirection(p.getLocation().add(0,0.5,0).toVector());
                    }
                }
            }
        }, 20, 10);
    }

}
