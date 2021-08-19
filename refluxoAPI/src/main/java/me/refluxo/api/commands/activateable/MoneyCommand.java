package me.refluxo.api.commands.activateable;

import me.refluxo.api.utils.player.Language;
import me.refluxo.api.utils.server.local.CoinsAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MoneyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        Player p = (Player) commandSender;
        p.sendMessage(Language.apiprefix+"Du hast §e" + CoinsAPI.getCoins(p) + "§7 Coins.");
        return true;
    }
}
