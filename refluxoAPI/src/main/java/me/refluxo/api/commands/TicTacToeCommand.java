package me.refluxo.api.commands;

import me.refluxo.api.utils.player.Language;
import me.refluxo.api.utils.server.local.TicTacToe;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TicTacToeCommand implements CommandExecutor {

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (args.length == 2) {
                final Player target = Bukkit.getPlayer(args[1]);
                if (args[0].equalsIgnoreCase("accept")) {
                    if (target == null) {
                        final Player player = p;
                        player.sendMessage(Language.apiprefix + "§7Der Spieler §e" + args[1] + " §7wurde nicht gefunden!");
                        return true;
                    }
                    if (TicTacToe.challenge.containsKey(target) && TicTacToe.challenge.containsValue(p)) {
                        final Player player2 = p;
                        player2.sendMessage(Language.apiprefix + "§7Du hast die §bEinladung §7angenommen!");
                        final Player player3 = target;
                        player3.sendMessage(Language.apiprefix + "§e" + p.getName() + " §7hat deine Einladung angenommen!");
                        final TicTacToe game = new TicTacToe(p, target);
                        game.openBoard();
                        TicTacToe.games.put(p, game);
                        TicTacToe.games.put(target, game);
                        TicTacToe.challenge.remove(target);
                        TicTacToe.challenge.remove(p);
                    }
                    else {
                        final Player player4 = p;
                        player4.sendMessage(Language.apiprefix + "§7Du hast keine §bEinladung §7von §e" + args[1]);
                    }
                }
                else if (args[0].equalsIgnoreCase("deny")) {
                    if (target == null) {
                        final Player player5 = p;
                        player5.sendMessage(Language.apiprefix + "§7Der Spieler §e" + args[1] + " §7wurde nicht gefunden!");
                        return true;
                    }
                    if (TicTacToe.challenge.containsKey(target) && TicTacToe.challenge.containsValue(p)) {
                        TicTacToe.challenge.remove(target);
                        final Player player6 = p;
                        player6.sendMessage(Language.apiprefix + "§7Du hast die §bEinladung §7abgelehnt!");
                        final Player player7 = target;
                        player7.sendMessage(Language.apiprefix + "§e" + p.getName() + " §7hat deine Einladung abgelehnt!");
                    }
                    else {
                        final Player player8 = p;
                        player8.sendMessage(Language.apiprefix + "§7Du hast keine §bEinladung §7von §e" + args[1]);
                    }
                }
                else {
                    this.sendhelp(p);
                }
            }
            else {
                this.sendhelp(p);
            }
        }
        return false;
    }

    private void sendhelp(final Player p) {
        p.sendMessage("§8§m--------§8[ §bTicTacToe §8]§8§m--------");
        p.sendMessage(" ");
        p.sendMessage("§b/tictactoe accept <Player> §8\u00d7 §7Nimmt Einladung von Spieler an!");
        p.sendMessage("§b/tictactoe deny <Player> §8\u00d7 §7Lehnt Einladung von Spieler ab!");
        p.sendMessage(" ");
    }
}
