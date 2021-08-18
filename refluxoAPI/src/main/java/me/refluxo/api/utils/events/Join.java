package me.refluxo.api.utils.events;

import me.refluxo.api.utils.pets.PetManager;
import me.refluxo.api.utils.pets.pet.Pet;
import me.refluxo.api.utils.player.APIPlayer;
import me.refluxo.api.utils.player.Share;
import me.refluxo.api.utils.server.SettingsManager;
import me.refluxo.api.utils.server.global.BitsAPI;
import me.refluxo.api.utils.server.global.ems.EMSApi;
import me.refluxo.api.utils.server.local.CoinsAPI;
import me.refluxo.api.utils.server.settings.enums.Settings;
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
        new SettingsManager().getSettingValue(bp, Settings.AllowMessages);
        Pet pet = new Pet(e.getPlayer());
        if(pet.petIsActive()) {
            pet.spawnPet();
        }
    }

}
