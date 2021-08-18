package me.refluxo.api.utils.pets.pet;

import me.refluxo.api.RefluxoAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PetDB {

    private static HashMap<Player, Entity> pets = new HashMap<>();

    public static void init() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(RefluxoAPI.getInstance(), () -> {
            for(Player p : Bukkit.getOnlinePlayers()) {
                Pet pet = new Pet(p);
                if(pet.petIsActive()) {
                    addPet(p, pet.getPetEntity());
                }
            }
        }, 20, 30);
    }

    public static void addPet(Player p, Entity pet) {
        pets.put(p, pet);
    }

    public static Entity getPetEntity(Player p) {
        if(pets.containsKey(p)) {
            return pets.get(p);
        } else {
            return null;
        }
    }

    public static int getPetEntityID(Player p) {
        if(pets.containsKey(p)) {
            return pets.get(p).getEntityId();
        } else {
            return -1;
        }
    }

    public static ArmorStand getPetArmorStand(Player p) {
        return (ArmorStand) pets.get(p);
    }

    public static void removePet(Player p) {
        if(pets.containsKey(p)) {
            pets.remove(p);
        }
    }

}
