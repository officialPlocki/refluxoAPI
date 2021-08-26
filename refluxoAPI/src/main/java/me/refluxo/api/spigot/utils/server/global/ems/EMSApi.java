package me.refluxo.api.spigot.utils.server.global.ems;

import me.refluxo.api.spigot.utils.mysql.MySQLService;
import me.refluxo.api.spigot.utils.player.APIPlayer;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EMSApi {

    private static MySQLService service = new MySQLService();
    private static Connection con = service.getConnection();

    public static int getLevel(APIPlayer apiPlayer) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT level FROM ems WHERE uuid = ?");
            ps.setString(1, apiPlayer.getPlayer().getUniqueId().toString());
            ResultSet rs = service.getResult(ps);
            if(rs.next()) {
                return rs.getInt("level");
            } else {
                inject(apiPlayer);
                return -1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public static int getBitGen(APIPlayer apiPlayer) {
        switch (getLevel(apiPlayer)) {
            case 1:
                return 4;
            case 2:
                return 8;
            case 3:
                return 16;
            case 4:
                return 32;
            case 5:
                return 64;
            default:
                return 0;
        }
    }

    public static void setLevel(APIPlayer apiPlayer, int level) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE ems SET level = ? WHERE uuid = ?");
            ps.setInt(1, level);
            ps.setString(2, apiPlayer.getPlayer().getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int getNextCost(APIPlayer apiPlayer) {
        return getCost(getNextLevel(apiPlayer))/2;
    }

    public static int getNextLevel(APIPlayer apiPlayer) {
        return getLevel(apiPlayer)+1;
    }

    public static int getCost(int level) {
        switch (level) {
            case 1:
                return 7500;
            case 2:
                return 15000;
            case 3:
                return 30000;
            case 4:
                return 60000;
            case 5:
                return 120000;
            default:
                return -1;
        }
    }

    public static void test(Player p) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT level FROM ems WHERE uuid = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = service.getResult(ps);
            if(!rs.next()) {
                inject(new APIPlayer(p));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void inject(APIPlayer apiPlayer) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO ems(level,uuid) VALUES (?,?)");
            ps.setInt(1, 0);
            ps.setString(2, apiPlayer.getPlayer().getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
