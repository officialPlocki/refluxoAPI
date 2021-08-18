package me.refluxo.api.commands;

import me.refluxo.api.utils.PlayerHead;
import me.refluxo.api.utils.inventory.InventoryBuilder;
import me.refluxo.api.utils.inventory.enums.PetInventoryTypes;
import me.refluxo.api.utils.player.Language;
import me.refluxo.api.utils.server.local.ItemUtil;
import org.bukkit.Material;
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

        switch (invType) {
            case PET_SETTINGS_AND_DESPAWN:
                InventoryBuilder builder = new InventoryBuilder("§b§lRe§f§lfluxo§a§lPets", 3*9);
                Inventory inv = builder.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§eKatze", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjUxYjE3ZDdkZWQ2YzdlOGYzYjJkYWMxMjM3OGE2ZmM0ZTkyMjhiOTExOTg2ZjY0YzhhZjQ1ODM3YWU2ZDllMSJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§7Stein", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2IyYjVkNDhlNTc1Nzc1NjNhY2EzMTczNTUxOWNiNjIyMjE5YmMwNThiMWYzNDY0OGI2N2I4ZTcxYmMwZmEifX19"), "\n§aKlicke zum Auswählen\n").buildItem())
                        .addItemToInventory(new ItemUtil("§aAxolotl", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjIwZTgxNzU3MThlYWMzOTY3MmU5ZGM2YzRlZWNjMGEzMmI0YzYyOTcwZDQ2YWJmNTIxN2FjNjUyYzU2ZjMxNyJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem())
                        .addItemToInventory(new ItemUtil("§2Schildkröte", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjg0ZjRjNWJjYzUxNTVhNjNkNTViM2MwZGYwZGYxMDBlZTU4M2E0NGZkY2EyM2Q3ZWE1MzU0YzdiYThlZTA0MiJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem())
                        .addItemToInventory(new ItemUtil("§6Gorilla", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzNlYjNlMzdlOTg3M2JmYzE3NmI5ZWQ4ZWY0ZmJlZjgzM2RlMTQ0NTQ2YmZhZWZkZjI0ODYzYzNlYjg3YmI4NiJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem())
                        .addItemToInventory(new ItemUtil("§bBaby Yoda", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTQ0MTBkNDAzMjEwM2IwNzU1ZmIwMTY3OTEwOWYxMjk5MTE4MjJiNDdiYmU0YjUzM2IyNTYxYzc3ZmNmZDBhZiJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§dElefant", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGJkZjBmNjI4YzA1ZTg2Y2FiZGVlMmY1ODU4ZGQ1ZGVmN2Y4YjhkOTQwY2JmMjVmOTkzN2UyZmZiNTM0MzJmNCJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem()).buildInventory();
                return inv;
            case CONFIRM:
                InventoryBuilder builder2 = new InventoryBuilder("§b§lRe§f§lfluxo§a§lPets", 3*9);
                Inventory inv2 = builder2.addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem()) //
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem())
                        .addItemToInventory(new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem()).buildInventory();
                return inv2;

        }

    }

}
