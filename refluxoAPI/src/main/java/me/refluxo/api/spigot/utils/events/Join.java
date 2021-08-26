package me.refluxo.api.spigot.utils.events;

import me.refluxo.api.spigot.utils.pets.PetManager;
import me.refluxo.api.spigot.utils.pets.pet.Pet;
import me.refluxo.api.spigot.utils.player.APIPlayer;
import me.refluxo.api.spigot.utils.player.Share;
import me.refluxo.api.spigot.utils.server.global.BitsAPI;
import me.refluxo.api.spigot.utils.server.global.ems.EMSApi;
import me.refluxo.api.spigot.utils.server.local.CoinsAPI;
import me.refluxo.api.spigot.utils.server.local.reflection.TablistManager;
import me.refluxo.api.spigot.utils.server.settings.save.SettingsMySQL;
import me.refluxo.api.spigot.utils.server.settings.save.SettingsYaml;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage("");
        BitsAPI.validateJoin(e.getPlayer());
        CoinsAPI.getCoins(e.getPlayer());
        APIPlayer bp = new APIPlayer(e.getPlayer());
        if(Share.enabled) {
            bp.getPlayerShare().loadAll();
        }
        EMSApi.test(e.getPlayer());
        PetManager.check(e.getPlayer());
        SettingsMySQL.check(bp);
        Pet pet = new Pet(e.getPlayer());
        if(pet.petIsActive()) {
            pet.spawnPet();
        }
        SettingsYaml.check(bp);
        Player player = bp.getPlayer();
        if(player.hasPermission("refluxo.inhaber")) {
            TablistManager.getInstance().addToTeam(bp.getPlayer(), "inhaber");
        } else if(player.hasPermission("refluxo.manager")) {
            TablistManager.getInstance().addToTeam(bp.getPlayer(), "manager");
        } else if(player.hasPermission("refluxo.admin")) {
            TablistManager.getInstance().addToTeam(bp.getPlayer(), "admin");
        } else if(player.hasPermission("refluxo.developer")) {
            TablistManager.getInstance().addToTeam(bp.getPlayer(), "developer");
        } else if(player.hasPermission("refluxo.moderator")) {
            TablistManager.getInstance().addToTeam(bp.getPlayer(), "moderator");
        } else if(player.hasPermission("refluxo.builder")) {
            TablistManager.getInstance().addToTeam(bp.getPlayer(), "builder");
        } else if(player.hasPermission("refluxo.supporter")) {
            TablistManager.getInstance().addToTeam(bp.getPlayer(), "supporter");
        } else if(player.hasPermission("refluxo.freund")) {
            TablistManager.getInstance().addToTeam(bp.getPlayer(), "freund");
        } else if(player.hasPermission("refluxo.streamer")) {
            TablistManager.getInstance().addToTeam(bp.getPlayer(), "streamer");
        } else if(player.hasPermission("refluxo.youtuber")) {
            TablistManager.getInstance().addToTeam(bp.getPlayer(), "youtuber");
        } else if(player.hasPermission("refluxo.fuchs")) {
            TablistManager.getInstance().addToTeam(bp.getPlayer(), "fuchs");
        } else if(player.hasPermission("refluxo.papagei")) {
            TablistManager.getInstance().addToTeam(bp.getPlayer(), "papagei");
        } else if(player.hasPermission("refluxo.premium")) {
            TablistManager.getInstance().addToTeam(bp.getPlayer(), "premium");
        } else {
            TablistManager.getInstance().addToTeam(bp.getPlayer(), "spieler");
        }
        TablistManager.getInstance().updateTeams();
    }

}
