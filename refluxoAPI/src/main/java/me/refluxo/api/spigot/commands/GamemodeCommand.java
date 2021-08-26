package me.refluxo.api.spigot.commands;

import me.refluxo.api.spigot.utils.player.Language;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GamemodeCommand extends Language implements CommandExecutor{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        Player p = (Player)sender;

        if(p.hasPermission("api.gamemode")) {
            if(args.length==0) {
                p.sendMessage(apiprefix+"Bitte verwende §e/gm <0, 1, 3> <Spieler>");
            } else if(args.length==1){
                if(args[0].equalsIgnoreCase("0")) {
                    if(p.hasPermission("api.gamemode.survival")) {
                        p.setGameMode(GameMode.SURVIVAL);
                    } else {
                        p.sendMessage(noPermissions);
                    }
                } else if(args[0].equalsIgnoreCase("1")) {
                    if(p.hasPermission("api.gamemode.creative")) {
                        p.setGameMode(GameMode.CREATIVE);
                    } else {
                        p.sendMessage(noPermissions);
                    }
                } else if(args[0].equalsIgnoreCase("3")) {
                    if(p.hasPermission("api.gamemode.spectator")) {
                        p.setGameMode(GameMode.SPECTATOR);
                    } else {
                        p.sendMessage(noPermissions);
                    }
                } else {
                    p.sendMessage(apiprefix+"Bitte verwende: §e/gm <0, 1, 3> <Spieler>");
                }
            } else if(args.length==2) {
                if(Objects.requireNonNull(Bukkit.getPlayer(args[1])).isOnline()) {
                    if(p.hasPermission("api.gamemode.other")) {
                        Player t = Bukkit.getPlayer(args[1]);
                        if(args[0].equalsIgnoreCase("0")) {
                            if(p.hasPermission("api.gamemode.survival")) {
                                assert t != null;
                                t.setGameMode(GameMode.SURVIVAL);
                            } else {
                                p.sendMessage(noPermissions);
                            }
                        } else if(args[0].equalsIgnoreCase("1")) {
                            if(p.hasPermission("api.gamemode.creative")) {
                                assert t != null;
                                t.setGameMode(GameMode.CREATIVE);
                            } else {
                                p.sendMessage(noPermissions);
                            }
                        } else if(args[0].equalsIgnoreCase("3")) {
                            if(p.hasPermission("api.gamemode.spectator")) {
                                assert t != null;
                                t.setGameMode(GameMode.SPECTATOR);
                            } else {
                                p.sendMessage(noPermissions);
                            }
                        } else {
                            p.sendMessage(apiprefix+"Bitte verwende §e/gm <0, 1, 3> <Spieler>");
                        }
                    } else {
                        p.sendMessage(noPermissions);
                    }
                } else {
                    p.sendMessage(apiprefix+"Der Spieler ist nicht online.");
                }
            } else {
                p.sendMessage(apiprefix+"Bitte verwende §e/gm <0, 1, 3> <Spieler>");
            }
        } else {
            p.sendMessage(noPermissions);
        }

        return false;
    }

}
