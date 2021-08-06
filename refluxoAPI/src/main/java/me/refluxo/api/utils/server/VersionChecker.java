package me.refluxo.api.utils.server;

import org.bukkit.Bukkit;

public class VersionChecker {

    public static String getVersion() {
        if(Bukkit.getBukkitVersion().contains("8")) {
            return "1.8";
        } else if(Bukkit.getBukkitVersion().contains("12")) {
            return "1.12";
        } else if(Bukkit.getBukkitVersion().contains("15")){
            return "1.15";
        } else if(Bukkit.getBukkitVersion().contains("16")){
            return "1.16";
        } else if(Bukkit.getBukkitVersion().contains("17")){
            return "1.17";
        } else {
            return "1.16";
        }
    }

}
