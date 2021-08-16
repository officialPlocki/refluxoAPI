package me.refluxo.api.utils.server.settings.save;

import me.refluxo.api.utils.files.FileBuilder;
import me.refluxo.api.utils.player.APIPlayer;
import me.refluxo.api.utils.server.SettingsManager;
import me.refluxo.api.utils.server.settings.enums.Settings;
import me.refluxo.api.utils.server.settings.enums.Values;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingsYaml {

    private static FileBuilder fb = new FileBuilder("settings.local");
    private static YamlConfiguration yml = fb.getYaml();

    private static void setYamlString(String path, Values value) {
        yml.set(path, value.toString());
        fb.save();
    }

    private static Values getYamlValue(APIPlayer apiPlayer, String path) {
        if(yml.isSet(path)) {
            return Values.valueOf(yml.getString(path));
        } else {
            yml.set(path, null);
            fb.save();
            SettingsManager.setup(apiPlayer);
            return null;
        }
    }

    public static void setSettingToValue(APIPlayer apiPlayer, Settings setting, Values value) {
        Player p = apiPlayer.getPlayer();
        setYamlString(p.getUniqueId().toString()+"."+setting.toString(), value);
    }

    public static Values getSettingValue(APIPlayer apiPlayer, Settings setting) {
        return getYamlValue(apiPlayer, apiPlayer.getPlayer().getUniqueId().toString()+"."+setting.toString());
    }
}
