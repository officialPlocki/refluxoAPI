package me.refluxo.api.commands;

import me.refluxo.api.utils.player.Language;
import me.refluxo.api.utils.server.plugin.PermissionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ServerAPICommand extends Language implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        Player p = (Player) commandSender;
        if(p.hasPermission("api.cmd")) {
            if(strings.length==0) {
                p.sendMessage(apiprefix+"Bitte benutze: §e/serverapi <showPermissions, version>");
                return true;
            } else if(strings.length==1) {
                if(strings[0].equalsIgnoreCase("showpermissions")) {
                    p.sendMessage(apiprefix+"Diese Permissions sind registriert: §e\n");
                    for(String project : PermissionManager.getProjects()) {
                        p.sendMessage("§c"+project+"\n\n");
                        p.sendMessage("§b"+PermissionManager.getPermissions(project).toString()+"\n");
                    }
                    return true;
                } else if(strings[0].equalsIgnoreCase("version")) {
                    p.sendMessage(apiprefix+"§eAPI v1, es sind "+ PermissionManager.getProjects().size()+ " Projekte registriert.");
                    return true;
                } else {
                    p.sendMessage(apiprefix+"Bitte benutze: §e/serverapi <showPermissions, version>");
                    return true;
                }
            } else {
                p.sendMessage(apiprefix+"Bitte benutze: §e/serverapi <showPermissions, version>");
                return true;
            }
        } else {
            p.sendMessage(apiprefix+"§eAPI v1, es sind "+ PermissionManager.getProjects().size()+ " Projekte registriert.");
            return false;
        }
    }
}
