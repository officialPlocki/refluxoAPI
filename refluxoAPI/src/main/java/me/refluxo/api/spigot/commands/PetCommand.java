package me.refluxo.api.spigot.commands;

import me.refluxo.api.spigot.RefluxoAPI;
import me.refluxo.api.spigot.utils.PlayerHead;
import me.refluxo.api.spigot.utils.inventory.InventoryBuilder;
import me.refluxo.api.spigot.utils.inventory.enums.PetInventoryTypes;
import me.refluxo.api.spigot.utils.pets.PetTypes;
import me.refluxo.api.spigot.utils.pets.pet.Pet;
import me.refluxo.api.spigot.utils.pets.pet.PetDB;
import me.refluxo.api.spigot.utils.pets.pet.Types;
import me.refluxo.api.spigot.utils.player.Language;
import me.refluxo.api.spigot.utils.server.local.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PetCommand extends Language implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        Player p = (Player) commandSender;
        Pet pet = new Pet(p);
        if(pet.petIsActive()) {
            p.openInventory(getInventory(PetInventoryTypes.PET_SETTINGS_AND_DESPAWN));
        } else {
            p.openInventory(getInventory(PetInventoryTypes.PET_CHOOSE));
        }
        return false;
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        boolean t = false;
        for(int i : PetDB.getPetIDs()) {
            if(i == e.getEntity().getEntityId()) {
                t = true;
            }
        }
        if(t) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e) {
        boolean b = false;
        for(int i : PetDB.getPetIDs()) {
            if(i == e.getRightClicked().getEntityId()) {
                b = true;
            }
        }
        if(b) {
            e.setCancelled(true);
        }
    }
    @Deprecated
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getCurrentItem() != null) {
            if(e.getView().getTitle().equalsIgnoreCase("§b§lRe§f§lfluxo§a§lPets")) {
                e.setCancelled(true);
                String name = Objects.requireNonNull(e.getCurrentItem().getItemMeta().getDisplayName());
                Player p = (Player) e.getWhoClicked();
                Pet pet = new Pet(p);
                boolean des = false;
                if(name.equalsIgnoreCase("§eKatze")) {
                    pet.setPet(PetTypes.CAT);
                    p.closeInventory();
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> p.openInventory(getInventory(PetInventoryTypes.PET_TYPE_SELECT_CAT)), 4);
                } else if(name.equalsIgnoreCase("§7Stein")) {
                    pet.setPet(PetTypes.STONE);
                    p.closeInventory();
                } else if(name.equalsIgnoreCase("§aAxolotl")) {
                    pet.setPet(PetTypes.AXOLOTL);
                    p.closeInventory();
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> p.openInventory(getInventory(PetInventoryTypes.PET_TYPE_SELECT_AXOLOTL)), 4);
                } else if(name.equalsIgnoreCase("§2Schildkröte")) {
                    pet.setPet(PetTypes.TURTLE);
                    p.closeInventory();
                } else if(name.equalsIgnoreCase("§6Gorilla")) {
                    pet.setPet(PetTypes.GORILLA);
                    p.closeInventory();
                } else if(name.equalsIgnoreCase("§bBaby Yoda")) {
                    pet.setPet(PetTypes.BABY_YODA);
                    p.closeInventory();
                } else if(name.equalsIgnoreCase("§dElefant")) {
                    pet.setPet(PetTypes.ELEPHANT);
                    p.closeInventory();
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> p.openInventory(getInventory(PetInventoryTypes.PET_TYPE_SELECT_ELEPHANT)), 4);
                } else if(name.equalsIgnoreCase("§aSpawn")) {
                    pet.spawnPet();
                    p.closeInventory();
                } else if(name.equalsIgnoreCase("§aEinstellungen")) {
                    switch (pet.getPet()) {
                        case STONE:
                        case TURTLE:
                        case GORILLA:
                        case BABY_YODA:
                        case NONE:
                            p.sendMessage(apiprefix+"Für dieses Haustier sind keine Einstellungen verfügbar.");
                            break;
                        case CAT:
                            p.closeInventory();
                            Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> p.openInventory(getInventory(PetInventoryTypes.PET_TYPE_SELECT_CAT)), 3);
                            break;
                        case AXOLOTL:
                            p.closeInventory();
                            Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> p.openInventory(getInventory(PetInventoryTypes.PET_TYPE_SELECT_AXOLOTL)), 3);
                            break;
                        case ELEPHANT:
                            p.closeInventory();
                            Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> p.openInventory(getInventory(PetInventoryTypes.PET_TYPE_SELECT_ELEPHANT)), 3);
                            break;
                    }
                } else if(name.equalsIgnoreCase("§cHaustier entfernen")) {
                    pet.deSpawnPet();
                    p.closeInventory();
                    des = true;
                } else if(name.equalsIgnoreCase("§6Gold")) {
                    pet.setPetType(Types.GOLD);
                    p.closeInventory();
                } else if(name.equalsIgnoreCase("§bBlau")) {
                    pet.setPetType(Types.BLUE);
                    p.closeInventory();
                } else if(name.equalsIgnoreCase("§3Türkis")) {
                    pet.setPetType(Types.CYAN);
                    p.closeInventory();
                } else if(name.equalsIgnoreCase("§dPink")) {
                    pet.setPetType(Types.LUCY);
                    p.closeInventory();
                } else if(name.equalsIgnoreCase("§7Wild")) {
                    pet.setPetType(Types.WILD);
                    p.closeInventory();
                } else if(name.equalsIgnoreCase("§8Schwarz")) {
                    pet.setPetType(Types.BLACK);
                    p.closeInventory();
                } else if(name.equalsIgnoreCase("§aGrün")) {
                    pet.setPetType(Types.GREEN);
                    p.closeInventory();
                } else if(name.equalsIgnoreCase("§aWeiß")) {
                    pet.setPetType(Types.MONOCHROME);
                    p.closeInventory();
                } else if(name.equalsIgnoreCase("§5Lila")) {
                    pet.setPetType(Types.PURPLE);
                    p.closeInventory();
                } else if(name.equalsIgnoreCase("§cRot")) {
                    pet.setPetType(Types.RED);
                    p.closeInventory();
                } else if(name.equalsIgnoreCase("§6Ivory")) {
                    pet.setPetType(Types.IVORY);
                    p.closeInventory();
                } else {
                    pet.setPetType(Types.ONYX);
                    p.closeInventory();
                }
                if(!des) {
                    Bukkit.getScheduler().runTaskLater(RefluxoAPI.getInstance(), () -> p.openInventory(getInventory(PetInventoryTypes.CONFIRM)),3);
                }
                return;
            }
        }
    }

    public Inventory getInventory(PetInventoryTypes invType) {
        Map<Integer, ItemStack> items = new HashMap<>();
        InventoryBuilder builder = new InventoryBuilder("§b§lRe§f§lfluxo§a§lPets", 3*9);
        if(invType.equals(PetInventoryTypes.PET_CHOOSE)) {
            items.put(0, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(1, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(2, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(3, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(4, new ItemUtil("§eKatze", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjUxYjE3ZDdkZWQ2YzdlOGYzYjJkYWMxMjM3OGE2ZmM0ZTkyMjhiOTExOTg2ZjY0YzhhZjQ1ODM3YWU2ZDllMSJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(5, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(6, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(7, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(8, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(9, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(10, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(11, new ItemUtil("§7Stein", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2IyYjVkNDhlNTc1Nzc1NjNhY2EzMTczNTUxOWNiNjIyMjE5YmMwNThiMWYzNDY0OGI2N2I4ZTcxYmMwZmEifX19"), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(12, new ItemUtil("§aAxolotl", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjIwZTgxNzU3MThlYWMzOTY3MmU5ZGM2YzRlZWNjMGEzMmI0YzYyOTcwZDQ2YWJmNTIxN2FjNjUyYzU2ZjMxNyJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(13, new ItemUtil("§2Schildkröte", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjg0ZjRjNWJjYzUxNTVhNjNkNTViM2MwZGYwZGYxMDBlZTU4M2E0NGZkY2EyM2Q3ZWE1MzU0YzdiYThlZTA0MiJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(14, new ItemUtil("§6Gorilla", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzNlYjNlMzdlOTg3M2JmYzE3NmI5ZWQ4ZWY0ZmJlZjgzM2RlMTQ0NTQ2YmZhZWZkZjI0ODYzYzNlYjg3YmI4NiJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(15, new ItemUtil("§bBaby Yoda", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTQ0MTBkNDAzMjEwM2IwNzU1ZmIwMTY3OTEwOWYxMjk5MTE4MjJiNDdiYmU0YjUzM2IyNTYxYzc3ZmNmZDBhZiJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(16, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(17, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(18, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(19, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(20, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(21, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(22, new ItemUtil("§dElefant", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGJkZjBmNjI4YzA1ZTg2Y2FiZGVlMmY1ODU4ZGQ1ZGVmN2Y4YjhkOTQwY2JmMjVmOTkzN2UyZmZiNTM0MzJmNCJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(23, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(24, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(25, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(26, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
        } else if(invType.equals(PetInventoryTypes.CONFIRM)) {
            items.put(0, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(1, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(2, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(3, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(4, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(5, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(6, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(7, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(8, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(9, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(10, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(11, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(12, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(13, new ItemUtil("§aSpawn", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU2YmIyNDNmY2JkYTc4MDNlZGUxNmYwMmI2MTU2MzZkZDJkNzI1MmUxN2RkZTkxMzE0MjRjNjhhNGQ1YWNhOSJ9fX0="), "\n§aKlicken zum Spawnen des Haustiers\n").buildItem());
            items.put(14, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(15, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(16, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(17, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(18, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(19, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(20, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(21, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(22, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(23, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(24, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(25, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(26, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
        } else if(invType.equals(PetInventoryTypes.PET_SETTINGS_AND_DESPAWN)) {
            items.put(0, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(1, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(2, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(3, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(4, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(5, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(6, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(7, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(8, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(9, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(10, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(11, new ItemUtil("§aEinstellungen", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWM1ZDczY2I0ZGMwNjUzMTQ2MzBiN2RjNTBmZDQ0ZjE4MzM2MzdmMDdkZGRjYTdmZWRkYzIwOGMyMTUwNGQ2NSJ9fX0="), "\n§aKlicke um die Einstellungen anzupassen.\n").buildItem());
            items.put(12, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(13, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(14, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(15, new ItemUtil("§cHaustier entfernen", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTgxMTkxN2M2NDRmMDRmNTZiNjhjOWM3MWIzM2FjMDgyYzI2MDFjMjQ0MDliZTc4YzIwNjNkNGRmMmE4NjUyNSJ9fX0="), "\n§cKlicke um dein Haustier aus der Welt zu entfernen.\n").buildItem());
            items.put(16, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(17, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(18, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(19, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(20, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(21, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(22, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(23, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(24, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(25, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(26, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
        } else if(invType.equals(PetInventoryTypes.PET_TYPE_SELECT_AXOLOTL)) {
            items.put(0, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(1, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(2, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(3, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(4, new ItemUtil("§6Gold", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2Y4MGNjMTQ5MmU0NDY2OGNjY2RiNDAxNzhjM2E2Njg5ZThkZmMwZDIzNGU5ODU1M2ZiN2RlYmMyNmZjYWVhYyJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(5, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(6, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(7, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(8, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(9, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(10, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(11, new ItemUtil("§bBlau", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDU0M2JmYTRlYTIzMzQ2NjdiNmFiZmU0MGQ4NTJlY2Y1NWY4NTA2YTljMGM1YWQ4ZGQxZTczMmY1OGQ5YzZjMyJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(12, new ItemUtil("§3Türkis", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U2OGRjNTlkYmU3NjdhNTI4MzE2NGUzYzk0YWNjMTRiYWRkMzRhODU3Y2VjMWE2YzlmMjZhMjhjZWQyOWM4MyJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(13, new ItemUtil("§dPink", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2I5MTBmYmMyMTZmNzI0ZDI5NjU1MTU1YjJhMzg1OGE4MGYyMzRhMGNmZWQ2MDllMjJmYzY3MDY4M2FiNzc3YSJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(14, new ItemUtil("§7Wild", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGRiMjJhMGVhNjJkNWQyMjg2N2Q4NTJkMDFkMzk2MTc3YjdhMGQ2M2UxOGNkYzVlMjlhYjM5ZjUyNDljNTA3NCJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(15, new ItemUtil("§8Schwarz", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWY1NjFmNDAzYTljMjdhNWU3MmE0Njg0MzY5MGViNDM3ODg1MjI5YTlkZTZjMjVlYmMyMWI5M2U2ZmRjZDliZCJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(16, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(17, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(18, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(19, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(20, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(21, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(22, new ItemUtil("§aGrün", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjQ0NWNkNjNjNjQ1NGI1YTg0Yjg1NmUzZjJiNzAwMjE3ZDQ2ODhiYjM3NWRkZTE0MjdjZTVlNTNmOTA1MDBhMSJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(23, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(24, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(25, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(26, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
        } else if(invType.equals(PetInventoryTypes.PET_TYPE_SELECT_CAT)) {
            items.put(0, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(1, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(2, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(3, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(4, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(5, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(6, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(7, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(8, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(9, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(10, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(11, new ItemUtil("§6Ivory", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjUxYjE3ZDdkZWQ2YzdlOGYzYjJkYWMxMjM3OGE2ZmM0ZTkyMjhiOTExOTg2ZjY0YzhhZjQ1ODM3YWU2ZDllMSJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(12, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(13, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(14, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(15, new ItemUtil("§7Onyx", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmU5MjQxMTVkM2E4YmJhY2ZkNGZhZmI2Y2M3MGY5OWEyZjc1ODBlNDU4M2E1MGZhOWI5YzI4NWE5OGFjMGM1NiJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(16, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(17, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(18, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(19, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(20, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(21, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(22, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(23, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(24, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(25, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(26, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
        } else {
            items.put(0, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(1, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(2, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(3, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(4, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(5, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(6, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(7, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(8, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(9, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(10, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(11, new ItemUtil("§fWeiß", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGJkZjBmNjI4YzA1ZTg2Y2FiZGVlMmY1ODU4ZGQ1ZGVmN2Y4YjhkOTQwY2JmMjVmOTkzN2UyZmZiNTM0MzJmNCJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(12, new ItemUtil("§aGrün", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzYwYzEyMmFkZTViMmZlZGNhMTRhYTc4YzgzNGE3YjBhYzljYjVkYTJhMGM5MzExMjE2MzA4NmY5MGMxM2I2OCJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(13, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(14, new ItemUtil("§5Lila", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWZmOWRmMjkwYjZjNWE0OTg0ZmM2ZTUxNjYwNWY5ODE2Yjk4ODJmN2JmMDRkYjA4ZDNmN2VlMzJkMTk2OWE0NCJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(15, new ItemUtil("§cRot", PlayerHead.getItemStackWithTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmE1YzY2ZWM2NmNiNmI0YjU1NTAwODVmNTgzYjRlNWMxY2VlNTI0N2JlYzVmYmNjNWMzMThjMzBjNjZjYWI0MiJ9fX0="), "\n§aKlicke zum Auswählen\n").buildItem());
            items.put(16, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(17, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(18, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(19, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(20, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(21, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(22, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(23, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(24, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(25, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
            items.put(26, new ItemUtil("§k", Material.BLACK_STAINED_GLASS_PANE, "").buildItem());
        }
        return builder.buildInventory(items);
    }
}
