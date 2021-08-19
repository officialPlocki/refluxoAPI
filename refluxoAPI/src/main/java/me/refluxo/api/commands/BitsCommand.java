package me.refluxo.api.commands;

import me.refluxo.api.utils.player.Language;
import me.refluxo.api.utils.server.global.BitsAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BitsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        Player p = (Player) commandSender;
        p.sendMessage(Language.apiprefix+"Du hast §e" + BitsAPI.getBits(p) + "§7 Bits.");
        return true;
    }
}
