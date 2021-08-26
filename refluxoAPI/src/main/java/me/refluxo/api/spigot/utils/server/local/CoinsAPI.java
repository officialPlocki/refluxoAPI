package me.refluxo.api.spigot.utils.server.local;

import me.refluxo.api.spigot.utils.files.FileBuilder;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class CoinsAPI {

    private static FileBuilder fileBuilder;
    private static YamlConfiguration yml;

    public static void setup() {
        fileBuilder = new FileBuilder("coins.data");
        yml = fileBuilder.getYaml();
    }

    public static double getCoins(Player p) {
        if(yml.isSet(p.getUniqueId().toString())) {
            return yml.getDouble(p.getUniqueId().toString());
        } else {
            yml.set(p.getUniqueId().toString(), 0.0);
            fileBuilder.save();
            return 0.0;
        }
    }

    public static String getVisualCoins(Player p) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        if(yml.isSet(p.getUniqueId().toString())) {
            double a = yml.getDouble(p.getUniqueId().toString());
            return df.format(a);
        } else {
            yml.set(p.getUniqueId().toString(), 0.0);
            fileBuilder.save();
            return df.format(0.0);
        }
    }

    public static void setCoins(Player p, double amount) {
        yml.set(p.getUniqueId().toString(), amount);
        fileBuilder.save();
    }

    public static void addCoins(Player p, double amount) {
        setCoins(p, getCoins(p)+amount);
    }

    public static void removeCoins(Player p, double amount) {
        setCoins(p, getCoins(p)-amount);
    }

    public static void resetCoins(Player p) {
        yml.set(p.getUniqueId().toString(), 0);
        fileBuilder.save();
    }
    public static boolean hasCoins(Player p, double amount) {
        if(getCoins(p) == amount || getCoins(p) >= amount) {
            return true;
        } else {
            return false;
        }
    }

    public static double getCoins(OfflinePlayer p) {
        if(yml.isSet(p.getUniqueId().toString())) {
            return yml.getDouble(p.getUniqueId().toString());
        } else {
            yml.set(p.getUniqueId().toString(), 0.0);
            fileBuilder.save();
            return 0.0;
        }
    }

    public static String getVisualCoins(OfflinePlayer p) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        if(yml.isSet(p.getUniqueId().toString())) {
            double a = yml.getDouble(p.getUniqueId().toString());
            return df.format(a);
        } else {
            yml.set(p.getUniqueId().toString(), 0.0);
            fileBuilder.save();
            return df.format(0.0);
        }
    }

    public static void setCoins(OfflinePlayer p, double amount) {
        yml.set(p.getUniqueId().toString(), amount);
        fileBuilder.save();
    }
    public static void resetCoins(OfflinePlayer p) {
        yml.set(p.getUniqueId().toString(), 0);
        fileBuilder.save();
    }
    public static void addCoins(OfflinePlayer p, double amount) {
        setCoins(p, getCoins(p)+amount);
    }

    public static void removeCoins(OfflinePlayer p, double amount) {
        setCoins(p, getCoins(p)-amount);
    }

    public static boolean hasCoins(OfflinePlayer p, double amount) {
        if(getCoins(p) == amount || getCoins(p) >= amount) {
            return true;
        } else {
            return false;
        }
    }

}
