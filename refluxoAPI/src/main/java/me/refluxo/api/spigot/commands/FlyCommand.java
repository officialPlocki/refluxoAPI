package me.refluxo.api.spigot.commands;

import me.refluxo.api.spigot.utils.player.Language;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FlyCommand extends Language implements CommandExecutor {
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
    if (!(sender instanceof Player))
      return false; 
    Player player = (Player)sender;
    if (!player.hasPermission("api.fly")) {
      player.sendMessage(noPermissions);
      return false;
    } 
    if (args.length == 0) {
      if (player.getAllowFlight()) {
        player.setAllowFlight(false);
        player.sendMessage(apiprefix + "Du kannst nun §cnicht mehr fliegen§7.");
      } else {
        player.setAllowFlight(true);
        player.sendMessage(apiprefix + "Du kannst nun §afliegen§7.");
      } 
      return false;
    } 
    if (args.length == 1) {
      if (!player.hasPermission("api.fly.other")) {
        player.sendMessage(noPermissions);
        return false;
      } 
      if (Bukkit.getPlayer(args[0]) != null) {
        Player target = Bukkit.getPlayer(args[0]);
        if (player == target) {
          player.sendMessage(apiprefix + "Du kannst das nicht bei dir selber machen!");
          return false;
        }
        assert target != null;
        if (target.getAllowFlight()) {
          target.setAllowFlight(false);
          target.sendMessage(apiprefix + "Du kannst nun §cnicht mehr fliegen§7.");
          player.sendMessage(apiprefix + "§e" + target.getName() + " §7kann nun §cnicht mehr fliegen§7.");
        } else {
          target.setAllowFlight(true);
          target.sendMessage(apiprefix + "Du kannst nun §afliegen§7.");
          player.sendMessage(apiprefix + "§e" + target.getName() + " §7kann nun §afliegen§7.");
        } 
      } else {
        player.sendMessage(apiprefix+"Dieser Spieler ist nicht online.");
      } 
      return false;
    } 
    player.sendMessage(apiprefix+"Bitte benutze: §e/fly");
    if (!player.hasPermission("api.fly.other"))
      player.sendMessage(apiprefix+"Bitte benutze: §e/fly <Spieler>");
    return false;
  }
}
