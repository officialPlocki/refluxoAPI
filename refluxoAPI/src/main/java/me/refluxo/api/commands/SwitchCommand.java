package me.refluxo.api.commands;

import me.refluxo.api.utils.bungee.BungeeCord;
import me.refluxo.api.utils.player.Language;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SwitchCommand extends Language implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        if(commandSender instanceof Player) {
            if(strings.length==0) {
                commandSender.sendMessage(apiprefix+"Du musst einen Server angeben.");
                return false;
            } else {
                commandSender.sendMessage(apiprefix+"Versuche auf Server zu senden...");
                new BungeeCord().sendPlayer((Player) commandSender, strings[0]);
                return true;
            }
        } else {
            return false;
        }
    }

}
