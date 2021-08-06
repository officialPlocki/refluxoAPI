package me.refluxo.api.utils.server.global;

import me.refluxo.api.utils.mysql.MySQLService;
import me.refluxo.api.utils.server.Console;
import me.refluxo.api.utils.server.ConsoleClassType;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BitsAPI {

    public static void addBits(OfflinePlayer p, int amount) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE bits SET playerBits = ? WHERE UUID = ?");
            ps.setInt(1, getBits(p)+amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException throwables) {
            new Console(throwables.getMessage(), ConsoleClassType.BitsAPI);
        }
    }
    public static void resetBits(OfflinePlayer p) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE bits SET playerBits = ? WHERE UUID = ?");
            ps.setInt(1, 0);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException throwables) {
            new Console(throwables.getMessage(), ConsoleClassType.BitsAPI);
        }
    }
    public static void setBits(OfflinePlayer p, int amount) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE bits SET playerBits = ? WHERE UUID = ?");
            ps.setInt(1, amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException throwables) {
            new Console(throwables.getMessage(), ConsoleClassType.BitsAPI);
        }
    }
    public static void removeBits(OfflinePlayer p, int amount) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE bits SET playerBits = ? WHERE UUID = ?");
            ps.setInt(1, getBits(p)-amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException throwables) {
            new Console(throwables.getMessage(), ConsoleClassType.BitsAPI);
        }
    }

    public static int getBits(OfflinePlayer p) {
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
        } catch (SQLException throwables) {
            new Console(throwables.getMessage(), ConsoleClassType.BitsAPI);
        }
        return -1;
    }

    public static void validateJoin(OfflinePlayer p) {
        if(getBits(p) == -1) {
            setupPlayer(p);
        }
    }

    public static void setupPlayer(OfflinePlayer p) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("INSERT INTO bits(playerBits,UUID) VALUES (?,?)");
            ps.setInt(1, 0);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (Exception throwables) {
            new Console(throwables.getMessage(), ConsoleClassType.BitsAPI);
        }
    }

    /////////////////////////////////


    public static void addBits(Player p, int amount) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE bits SET playerBits = ? WHERE UUID = ?");
            ps.setInt(1, getBits(p)+amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException throwables) {
            new Console(throwables.getMessage(), ConsoleClassType.BitsAPI);
        }
    }
    public static void resetBits(Player p) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE bits SET playerBits = ? WHERE UUID = ?");
            ps.setInt(1, 0);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException throwables) {
            new Console(throwables.getMessage(), ConsoleClassType.BitsAPI);
        }
    }
    public static void setBits(Player p, int amount) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE bits SET playerBits = ? WHERE UUID = ?");
            ps.setInt(1, amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException throwables) {
            new Console(throwables.getMessage(), ConsoleClassType.BitsAPI);
        }
    }
    public static void removeBits(Player p, int amount) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE bits SET playerBits = ? WHERE UUID = ?");
            ps.setInt(1, getBits(p)-amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException throwables) {
            new Console(throwables.getMessage(), ConsoleClassType.BitsAPI);
        }
    }

    public static int getBits(Player p) {
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
        } catch (SQLException throwables) {
            new Console(throwables.getMessage(), ConsoleClassType.BitsAPI);
        }
        return -1;
    }

    public static void validateJoin(Player p) {
        if(getBits(p) == -1) {
            setupPlayer(p);
        }
    }

    public static void setupPlayer(Player p) {
        try {
            MySQLService service = new MySQLService();
            PreparedStatement ps = service.getConnection().prepareStatement("INSERT INTO bits(playerBits,UUID) VALUES (?,?)");
            ps.setInt(1, 0);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (Exception throwables) {
            new Console(throwables.getMessage(), ConsoleClassType.BitsAPI);
        }
    }

}
