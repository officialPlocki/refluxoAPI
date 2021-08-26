package me.refluxo.api.spigot.utils.pets.pet;

import me.refluxo.api.spigot.RefluxoAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public static List<Integer> getPetIDs() {
        List<Integer> ints = new ArrayList<>();
        for(Entity e : pets.values()) {
            ints.add(e.getEntityId());
        }
        return ints;
    }

    public static void addPet(Player p, Entity pet) {
        pets.put(p, pet);
    }

    public static Entity getPetEntity(Player p) {
        return pets.getOrDefault(p, null);
    }

    public static int getPetEntityID(Player p) {
        if(pets.containsKey(p)) {
            if(pets.get(p) != null) {
                return pets.get(p).getEntityId();
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public static ArmorStand getPetArmorStand(Player p) {
        return (ArmorStand) pets.get(p);
    }

    public static void removePet(Player p) {
        pets.remove(p);
    }

}
