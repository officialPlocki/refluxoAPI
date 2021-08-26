package me.refluxo.api.bungeecord.utils.server.global;

import me.refluxo.api.bungeecord.utils.mysql.MySQLService;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BitsAPI {

    public static void addBits(ProxiedPlayer p, int amount) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE bits SET playerBits = ? WHERE UUID = ?");
            ps.setInt(1, getBits(p)+amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException ignored) {
        }
    }
    public static void resetBits(ProxiedPlayer p) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE bits SET playerBits = ? WHERE UUID = ?");
            ps.setInt(1, 0);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException ignored) {
        }
    }
    public static void setBits(ProxiedPlayer p, int amount) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE bits SET playerBits = ? WHERE UUID = ?");
            ps.setInt(1, amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException ignored) {
        }
    }
    public static void removeBits(ProxiedPlayer p, int amount) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE bits SET playerBits = ? WHERE UUID = ?");
            ps.setInt(1, getBits(p)-amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException ignored) {
        }
    }

    public static int getBits(ProxiedPlayer p) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("SELECT playerBits FROM bits WHERE UUID = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = service.getResult(ps);
            if(rs.next()) {
                return rs.getInt("playerBits");
            } else {
                setupPlayer(p);
                return getBits(p);
            }
        } catch (SQLException ignored) {
        }
        return -1;
    }

    public static void validateJoin(ProxiedPlayer p) {
        if(getBits(p) == -1) {
            setupPlayer(p);
        }
    }

    public static void setupPlayer(ProxiedPlayer p) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("INSERT INTO bits(playerBits,UUID) VALUES (?,?)");
            ps.setInt(1, 0);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (Exception ignored) {
        }
    }

}
