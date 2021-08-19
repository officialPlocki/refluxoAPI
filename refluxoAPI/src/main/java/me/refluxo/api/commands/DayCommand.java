package me.refluxo.api.commands;

import me.refluxo.api.utils.player.Language;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DayCommand extends Language implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        if(commandSender.hasPermission("api.day")) {
            ((Player) commandSender).getLocation().getWorld().setTime(0);
            commandSender.sendMessage(apiprefix+"Die Zeit wurde auf Tag gesetzt.");
            return true;
        } else {
            commandSender.sendMessage(noPermissions);
            return false;
        }
    }
}
