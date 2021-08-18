package me.refluxo.api.utils.server.settings.save;

import me.refluxo.api.utils.mysql.MySQLService;
import me.refluxo.api.utils.player.APIPlayer;
import me.refluxo.api.utils.server.SettingsManager;
import me.refluxo.api.utils.server.settings.enums.Settings;
import me.refluxo.api.utils.server.settings.enums.Values;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingsMySQL {

    private static MySQLService service = new MySQLService();
    private static Connection con = service.getConnection();

    public static void setSettingToValue(APIPlayer apiPlayer, Settings setting, Values value) {
        Player p = apiPlayer.getPlayer();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE settings SET " + setting.name() + " TO `" + value.toString() + "` WHERE uuid = ?");
            ps.setString(1, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Values getSettingValue(APIPlayer apiPlayer, Settings setting) {
        Player p = apiPlayer.getPlayer();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT " + setting.name() + " FROM settings WHERE uuid = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = service.getResult(ps);
            if(rs.next()) {
                return Values.valueOf(rs.getString(setting.name()));
            } else {
                check(apiPlayer);
                return getSettingValue(apiPlayer, setting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        check(apiPlayer);
        return getSettingValue(apiPlayer, setting);
    }

    public static void check(APIPlayer apiPlayer) {
        Player p = apiPlayer.getPlayer();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM settings WHERE uuid = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = service.getResult(ps);
            if(!rs.next()) {
                SettingsManager.setup(apiPlayer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
