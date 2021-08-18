package me.refluxo.api.commands;

import me.refluxo.api.utils.inventory.enums.PetInventoryTypes;
import me.refluxo.api.utils.player.Language;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class PetCommand extends Language implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return false;
    }

    public Inventory getInventory(PetInventoryTypes invType) {

    }

}
