package me.refluxo.api.commands.activateable;

import me.refluxo.api.utils.player.Language;
import me.refluxo.api.utils.server.local.CoinsAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage(Language.apiprefix + "Bitte benutze: §e/pay <Spieler> <Anzahl>");
                return false;
            }
            if (args.length == 1) {
                p.sendMessage(Language.apiprefix + "Bitte benutze: §e/pay <Spieler> <Anzahl>");
                return false;
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("*")) {
                    if(Double.valueOf(args[1]) <= 500) {
                        p.sendMessage("Du musst mindestens 500 Coins an jeden Spieler überweisen.");
                        return false;
                    }
                    if (CoinsAPI.getCoins(p) == Bukkit.getOnlinePlayers().size() * Double.valueOf(args[1]).intValue() || CoinsAPI.getCoins(p) >= Bukkit.getOnlinePlayers().size() * Double.valueOf(args[1]).intValue()) {
                        p.sendMessage(Language.apiprefix + "Du hast erfolgreich §e" + args[1] + " Coins §7an alle Spieler gegeben!");
                        CoinsAPI.removeCoins(p, Double.valueOf(Bukkit.getOnlinePlayers().size() * Double.valueOf(args[1]).intValue()));
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            CoinsAPI.addCoins(all, Double.valueOf(args[1]));
                            all.sendMessage(Language.apiprefix+ "Du hast von §b" + p.getName() + " §e" + args[1] + " Coins §7erhalten!");
                        }
                        return true;
                    }
                    p.sendMessage(Language.apiprefix+ "Du hast nicht genügend Geld!");
                    return false;
                }

                if(Double.valueOf(args[1]) <= 0.5) {
                    p.sendMessage("Du musst mindestens 0,50 Coins überweisen.");
                    return false;
                }

                Player target = null;
                for(Player all : Bukkit.getOnlinePlayers()) {
                    if(all.getName().equalsIgnoreCase(args[0])) {
                        target = all;
                    }
                }

                if(target == null) {
                    p.sendMessage(Language.apiprefix+"Der Spieler ist nicht online.");
                    return false;
                }

                if (!target.isOnline()) {
                    p.sendMessage(Language.apiprefix+"Der Spieler ist nicht online.");
                    return false;
                }
                if (target.isOnline()) {
                    if (CoinsAPI.getCoins(p) == Double.valueOf(args[1]) || CoinsAPI.getCoins(p) >= Double.valueOf(args[1]).intValue()) {
                        p.sendMessage(Language.apiprefix+ "Du hast erfolgreich §e" + args[1] + " Coins§7 an §b" + args[0] + " §7gezahlt!");
                        target.sendMessage(Language.apiprefix+ "Du hast von §b" + p.getName() + " §e" + args[1] + " Coins §7erhalten!");
                        CoinsAPI.removeCoins(p, Double.valueOf(args[1]));
                        CoinsAPI.addCoins(target, Double.valueOf(args[1]));
                        return true;
                    }
                    p.sendMessage(Language.apiprefix+"Du hast nicht genügend Geld!");
                    return false;
                }

                p.sendMessage(Language.apiprefix+"Der Spieler ist nicht online.");
                return false;
            }
            p.sendMessage(Language.apiprefix + "Bitte benutze: §e/pay <Spieler> <Anzahl>");
            return false;
        }

        return false;
    }
}
