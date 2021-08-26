package me.refluxo.api.spigot.commands;

import me.refluxo.api.spigot.utils.player.Language;
import me.refluxo.api.spigot.utils.server.global.BitsAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EcoAdminBitsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        Player p = (Player)commandSender;
        if(commandSender.hasPermission("api.ecoadminbits")){

            if(strings.length==0) {
                p.sendMessage(Language.apiprefix + "Bitte verwende: §a/ecoadminbits <show, give, take, reset> <Spieler>  [Anzahl]");
                return false;
            } else if(strings.length==1){
                p.sendMessage(Language.apiprefix + "Bitte verwende: §a/ecoadminbits <show, give, take, reset> <Spieler>  [Anzahl]");
                return false;
            } else if(strings.length==2){

                if(strings[0].equalsIgnoreCase("show")){
                    if(Bukkit.getPlayer(strings[1]).isOnline()==false){
                        p.sendMessage(Language.apiprefix+"Der Spieler §e"+strings[1]+"§7 hat aktuell "+ BitsAPI.getBits(Bukkit.getOfflinePlayer(strings[1])) + " Bits.");
                        return true;
                    }
                    p.sendMessage(Language.apiprefix+"Der Spieler §e"+strings[1]+"§7 hat aktuell "+ BitsAPI.getBits(Bukkit.getPlayer(strings[1])) + " Bits.");
                    return true;
                } else if(strings[0].equalsIgnoreCase("reset")){
                    if(Bukkit.getPlayer(strings[1]).isOnline()==false){
                        BitsAPI.resetBits(Bukkit.getOfflinePlayer(strings[1]));
                        p.sendMessage(Language.apiprefix+"Der Spieler §e"+strings[1]+"§7 hat nun "+ BitsAPI.getBits(Bukkit.getOfflinePlayer(strings[1])) + " Bits.");
                        return true;
                    }
                    BitsAPI.resetBits(Bukkit.getPlayer(strings[1]));
                    p.sendMessage(Language.apiprefix+"Der Spieler §e"+strings[1]+"§7 hat nun "+ BitsAPI.getBits(Bukkit.getPlayer(strings[1])) + " Bits.");
                    return true;
                }
            } else if(strings.length==3){
                if(strings[0].equalsIgnoreCase("give")){
                    if(Bukkit.getPlayer(strings[1]).isOnline()==false){
                        BitsAPI.addBits(Bukkit.getOfflinePlayer(strings[1]), Integer.valueOf(strings[2]));
                        p.sendMessage(Language.apiprefix+"Der Spieler §e"+strings[1]+"$7 hat nun "+ BitsAPI.getBits(Bukkit.getPlayer(strings[1])) + " Bits.");
                        return true;
                    }
                    BitsAPI.addBits(Bukkit.getPlayer(strings[1]), Integer.valueOf(strings[2]));
                    p.sendMessage(Language.apiprefix+"Der Spieler §e"+strings[1]+"§7 hat nun "+ BitsAPI.getBits(Bukkit.getPlayer(strings[1])) + " Bits.");
                    return true;
                } else if(strings[0].equalsIgnoreCase("take")){
                    if(Bukkit.getPlayer(strings[1]).isOnline()==false){
                        BitsAPI.removeBits(Bukkit.getOfflinePlayer(strings[1]), Integer.valueOf(strings[2]));
                        p.sendMessage(Language.apiprefix+"Der Spieler §e"+strings[1]+"$7 hat nun "+ BitsAPI.getBits(Bukkit.getPlayer(strings[1])) + " Bits.");
                        return true;
                    }
                    BitsAPI.removeBits(Bukkit.getPlayer(strings[1]), Integer.valueOf(strings[2]));
                    p.sendMessage(Language.apiprefix+"Der Spieler §e"+strings[1]+"§7 hat nun "+ BitsAPI.getBits(Bukkit.getPlayer(strings[1])) + " Bits.");
                    return true;
                } else {
                    p.sendMessage(Language.apiprefix + "Bitte verwende: §a/ecoadminbits <show, give, take, reset> <Spieler>  [Anzahl]");
                    return false;
                }
            } else if(strings.length>=3){
                p.sendMessage(Language.apiprefix + "Bitte verwende: §a/ecoadminbits <show, give, take, reset> <Spieler>  [Anzahl]");
                return false;
            }
        } else {
            p.sendMessage(Language.noPermissions);
        }

        return false;
    }
}
