package me.refluxo.api.utils.player;

import me.refluxo.api.utils.mysql.MySQLService;
import me.refluxo.api.utils.mysql.playershare.InventoryString;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Share {

    public static boolean enabled;

    private Player player;
    private String s;
    private MySQLService mySQLService = new MySQLService();
    private Connection con = mySQLService.getConnection();

    public Share(Player p, String server) {
        player = p;
        s = server;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM playerdata WHERE uuid = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = mySQLService.getResult(ps);
            if(!rs.next()) {
                inject();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveInventory() {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE playerdata SET inventory = ? WHERE uuid = ? AND servercon = ?");
            ps.setString(1, InventoryString.ItemsToString(player.getInventory().getContents()));
            ps.setString(2, player.getUniqueId().toString());
            ps.setString(3, s);
            mySQLService.executeUpdate(ps);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveExtra() {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE playerdata SET extra = ? WHERE uuid = ? AND servercon = ?");
            ps.setString(1, InventoryString.ItemsToString(player.getInventory().getExtraContents()));
            ps.setString(2, player.getUniqueId().toString());
            ps.setString(3, s);
            mySQLService.executeUpdate(ps);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadExtra() {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT extra FROM playerdata WHERE uuid = ? AND servercon = ?");
            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, s);
            ResultSet rs = mySQLService.getResult(ps);
            if(rs.next()) {
                player.getInventory().setExtraContents(InventoryString.StringToItems(rs.getString("extra")));
            } else {
                inject();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveArmor() {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE playerdata SET armor = ? WHERE uuid = ? AND servercon = ?");
            ps.setString(1, InventoryString.ItemsToString(player.getInventory().getArmorContents()));
            ps.setString(2, player.getUniqueId().toString());
            ps.setString(3, s);
            mySQLService.executeUpdate(ps);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadArmor() {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT armor FROM playerdata WHERE uuid = ? AND servercon = ?");
            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, s);
            ResultSet rs = mySQLService.getResult(ps);
            if(rs.next()) {
                player.getInventory().setArmorContents(InventoryString.StringToItems(rs.getString("armor")));
            } else {
                inject();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadInventory() {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT inventory FROM playerdata WHERE uuid = ? AND servercon = ?");
            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, s);
            ResultSet rs = mySQLService.getResult(ps);
            if(rs.next()) {
                player.getInventory().setContents(InventoryString.StringToItems(rs.getString("inventory")));
            } else {
                inject();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveEXP() {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE playerdata SET level = ? WHERE uuid = ? AND servercon = ?");
            ps.setInt(1, player.getLevel());
            ps.setString(2, player.getUniqueId().toString());
            ps.setString(3, s);
            mySQLService.executeUpdate(ps);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadEXP() {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT level FROM playerdata WHERE uuid = ? AND servercon = ?");
            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, s);
            ResultSet rs = mySQLService.getResult(ps);
            if(rs.next()) {
                player.setLevel(rs.getInt("level"));
            } else {
                inject();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveAll() {
        saveEXP();
        saveInventory();
        saveArmor();
        saveExtra();
    }

    public void loadAll() {
        loadArmor();
        loadEXP();
        loadExtra();
        loadInventory();
    }

    private void inject() {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO playerdata(servercon,uuid,inventory,level,armor,extra) VALUES (?,?,?,?,?,?)");
            ps.setString(1, s);
            ps.setString(2, player.getUniqueId().toString());
            ps.setString(3, InventoryString.ItemsToString(player.getInventory().getContents()));
            ps.setInt(4, player.getLevel());
            ps.setString(5, InventoryString.ItemsToString(player.getInventory().getArmorContents()));
            ps.setString(6, InventoryString.ItemsToString(player.getInventory().getExtraContents()));
            mySQLService.executeUpdate(ps);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
