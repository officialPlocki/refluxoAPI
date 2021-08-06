package me.refluxo.api.commands;

import me.refluxo.api.utils.player.Language;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand extends Language implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(p.hasPermission("api.clearchat")) {
            for(Player all : Bukkit.getOnlinePlayers()) {
                if(!all.hasPermission("api.clearchat.bypass")) {
                    all.sendMessage("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n§8» §eDer Chat wurde von einem Teammitglied geleert.\n");
                } else {
                    all.sendMessage("\n§8» §eDer Chat wurde von §c" + p.getName() + "§e geleert.\n");
                }
            }
            p.sendMessage(apiprefix+"Du hast erfolgreich den Chat geleert.");
            return true;
        } else {
            p.sendMessage(noPermissions);
            return false;
        }
    }
}
