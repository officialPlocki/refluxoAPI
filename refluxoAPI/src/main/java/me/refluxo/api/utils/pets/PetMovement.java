package me.refluxo.api.utils.pets;

import me.refluxo.api.RefluxoAPI;
import me.refluxo.api.utils.pets.pet.Pet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PetMovement {

    public static void init() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(RefluxoAPI.getInstance(), () -> {
            for(Player p : Bukkit.getOnlinePlayers()) {
                Pet pet = new Pet(p);
                if(pet.petIsActive()) {
                    Entity e = pet.getPetEntity();
                    if((e.getLocation().distance(p.getLocation())) >= 1.5) {
                        e.teleport(p.getLocation().toCenterLocation());
                        e.getLocation().setDirection(p.getLocation().toHighestLocation().toVector());
                    }
                }
            }
        }, 20, 10);
    }

}
