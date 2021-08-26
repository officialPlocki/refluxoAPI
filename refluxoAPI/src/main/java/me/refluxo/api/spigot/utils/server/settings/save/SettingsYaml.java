package me.refluxo.api.spigot.utils.server.settings.save;

import me.refluxo.api.spigot.utils.files.FileBuilder;
import me.refluxo.api.spigot.utils.player.APIPlayer;
import me.refluxo.api.spigot.utils.server.settings.enums.Settings;
import me.refluxo.api.spigot.utils.server.settings.enums.Values;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SettingsYaml {

    private static FileBuilder fb = new FileBuilder("settings.local");
    private static YamlConfiguration yml = fb.getYaml();

    private static void setYamlString(String path, Values value) {
        yml.set(path, value.name());
        fb.save();
    }

    private static Values getYamlValue(APIPlayer apiPlayer, String path) {
        if(yml.isSet(path)) {
            return Values.valueOf(yml.getString(path));
        } else {
            yml.set(apiPlayer.getPlayer().getUniqueId().toString()+"."+Settings.NavigatorInHotbar, Values.YES.name());
            yml.set(apiPlayer.getPlayer().getUniqueId().toString()+"."+Settings.AutomaticTeleportToSpawnOnJoin, Values.YES.name());
            fb.save();
            return null;
        }
    }

    public static void setSettingToValue(APIPlayer apiPlayer, Settings setting, Values value) {
        Player p = apiPlayer.getPlayer();
        setYamlString(p.getUniqueId().toString()+"."+setting.name(), value);
    }

    public static Values getSettingValue(APIPlayer apiPlayer, Settings setting) {
        return getYamlValue(apiPlayer, apiPlayer.getPlayer().getUniqueId().toString()+"."+setting.name());
    }

    public static void check(APIPlayer p) {
        getYamlValue(p, "a");
    }

}
