package me.refluxo.api.spigot.utils.pets;

import me.refluxo.api.spigot.utils.mysql.MySQLService;
import me.refluxo.api.spigot.utils.pets.pet.ActiveTypes;
import me.refluxo.api.spigot.utils.pets.pet.Types;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PetManager {

    private static final MySQLService mySQLService = new MySQLService();
    private static Connection con = mySQLService.getConnection();

    public static void check(Player p) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pets WHERE uuid = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = mySQLService.getResult(ps);
            if(!rs.next()) {
                PreparedStatement in = con.prepareStatement("INSERT INTO pets(uuid, pet, petType, active) VALUES (?,?,?,?)");
                in.setString(1, p.getUniqueId().toString());
                in.setString(2, PetTypes.NONE.name());
                in.setString(3, Types.NONE.name());
                in.setString(4, ActiveTypes.FALSE.name());
                mySQLService.executeUpdate(in);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
