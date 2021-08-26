package me.refluxo.api.spigot.commands;

import me.refluxo.api.spigot.utils.player.Language;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NightCommand extends Language implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        if(commandSender.hasPermission("api.night")) {
            ((Player) commandSender).getLocation().getWorld().setTime(22000);
            commandSender.sendMessage(apiprefix+"Die Zeit wurde auf Nacht gesetzt.");
            return true;
        } else {
            commandSender.sendMessage(noPermissions);
            return false;
        }
    }
}
