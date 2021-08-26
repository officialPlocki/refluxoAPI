package me.refluxo.api.spigot.commands.activateable;

import me.refluxo.api.spigot.utils.player.APIPlayer;
import me.refluxo.api.spigot.utils.player.Language;
import me.refluxo.api.spigot.utils.server.trade.Trade;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TradeCommand extends Language implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        if(commandSender instanceof Player) {
            if(strings.length==0||strings.length>=3) {
                commandSender.sendMessage(apiprefix + "Bitte verwende: §e/trade <Bits>");
                return false;
            } else {
                if(strings[0].matches("-?\\d+")) {
                    if(strings.length==2) {
                        if(strings[1].equalsIgnoreCase("conf")||strings[1].equalsIgnoreCase("confirm")) {
                            int bits = Integer.parseInt(strings[0]);
                            APIPlayer p = new APIPlayer((Player) commandSender);
                            if(p.getBits()==bits||p.getBits()>=bits) {
                                Trade trade = new Trade(p).newTrade(bits);
                                commandSender.sendMessage(apiprefix + "Du hast erfolgreich " + bits + " zu " + trade.getFin() + " eingetauscht. Du hast auf " + trade.getRaw() + " einen Bonus von " + trade.getDup() + "% erhalten.");
                                if(p.getPlayer().hasPermission("api.trade.more")) {
                                    commandSender.sendMessage(apiprefix + "Du hast durch deinen Rang hast du 0.5% Bonus auf deinen individuellen Wechselkurs erhalten.");
                                }
                                trade.trade();
                                return true;
                            } else {
                                commandSender.sendMessage(apiprefix + "Du hast nicht genügend Bits. Du hast aktuell " + p.getBits() + " Bits.");
                                return false;
                            }
                        } else {
                            commandSender.sendMessage(apiprefix + "Bitte bestätige den Trade mit /trade " + strings[0] + " confirm");
                            return false;
                        }
                    } else {
                        commandSender.sendMessage(apiprefix + "Bitte bestätige den Trade mit /trade " + strings[0] + " confirm");
                        return false;
                    }
                } else {
                    commandSender.sendMessage(apiprefix + "Bitte gebe nur Zahlen als 1. Argument an!");
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
