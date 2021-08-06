package me.refluxo.api.utils.server.local;

import me.refluxo.api.utils.player.Language;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TicTacToe {
    private ItemStack player0Item;
    private ItemStack player1Item;
    private boolean isFinished;
    private Player player0;
    private Player player1;
    private Player currentPlayer;
    private Random random;
    private Inventory inventory;

    public static Map<Player, TicTacToe> games = new HashMap<>();
    public static HashMap<Player, Player> challenge = new HashMap<>();

    public TicTacToe(final Player player0, final Player player1) {
        this.random = new Random();
        this.player0 = player0;
        this.player1 = player1;
        this.player0Item = new ItemUtil("§e" + player0.getName(), Material.GOLD_INGOT, null).buildItem();
        this.player1Item = new ItemUtil("§c" + player1.getName(), Material.IRON_INGOT, null).buildItem();
        this.inventory = Bukkit.createInventory((InventoryHolder)null, InventoryType.DISPENSER, "§bTicTacToe");
        this.currentPlayer = ((this.random.nextInt(2) == 0) ? player0 : player1);
    }

    public void openBoard() {
        this.player0.openInventory(this.inventory);
        this.player1.openInventory(this.inventory);
    }

    public boolean isBoardFull() {
        boolean isFull = true;
        for (int i = 0; i <= 8; ++i) {
            if (this.inventory.getItem(i) == null || this.inventory.getItem(i).getType() == Material.AIR) {
                isFull = false;
            }
        }
        return isFull;
    }

    public boolean checkForWin() {
        return this.checkRowsForWin() || this.checkColumnsForWin() || this.checkDiagonalsForWin();
    }

    private boolean checkRowsForWin() {
        if (this.inventory.getItem(0) != null && this.inventory.getItem(1) != null && this.inventory.getItem(2) != null) {
            if (this.inventory.getItem(0).getItemMeta().getDisplayName().equalsIgnoreCase(this.player0Item.getItemMeta().getDisplayName()) && this.inventory.getItem(1).getItemMeta().getDisplayName().equalsIgnoreCase(this.player0Item.getItemMeta().getDisplayName()) && this.inventory.getItem(2).getItemMeta().getDisplayName().equalsIgnoreCase(this.player0Item.getItemMeta().getDisplayName())) {
                return true;
            }
            if (this.inventory.getItem(0).getItemMeta().getDisplayName().equalsIgnoreCase(this.player1Item.getItemMeta().getDisplayName()) && this.inventory.getItem(1).getItemMeta().getDisplayName().equalsIgnoreCase(this.player1Item.getItemMeta().getDisplayName()) && this.inventory.getItem(2).getItemMeta().getDisplayName().equalsIgnoreCase(this.player1Item.getItemMeta().getDisplayName())) {
                return true;
            }
        }
        if (this.inventory.getItem(3) != null && this.inventory.getItem(4) != null && this.inventory.getItem(5) != null) {
            if (this.inventory.getItem(3).getItemMeta().getDisplayName().equalsIgnoreCase(this.player0Item.getItemMeta().getDisplayName()) && this.inventory.getItem(4).getItemMeta().getDisplayName().equalsIgnoreCase(this.player0Item.getItemMeta().getDisplayName()) && this.inventory.getItem(5).getItemMeta().getDisplayName().equalsIgnoreCase(this.player0Item.getItemMeta().getDisplayName())) {
                return true;
            }
            if (this.inventory.getItem(3).getItemMeta().getDisplayName().equalsIgnoreCase(this.player1Item.getItemMeta().getDisplayName()) && this.inventory.getItem(4).getItemMeta().getDisplayName().equalsIgnoreCase(this.player1Item.getItemMeta().getDisplayName()) && this.inventory.getItem(5).getItemMeta().getDisplayName().equalsIgnoreCase(this.player1Item.getItemMeta().getDisplayName())) {
                return true;
            }
        }
        if (this.inventory.getItem(6) != null && this.inventory.getItem(7) != null && this.inventory.getItem(8) != null) {
            if (this.inventory.getItem(6).getItemMeta().getDisplayName().equalsIgnoreCase(this.player0Item.getItemMeta().getDisplayName()) && this.inventory.getItem(7).getItemMeta().getDisplayName().equalsIgnoreCase(this.player0Item.getItemMeta().getDisplayName()) && this.inventory.getItem(8).getItemMeta().getDisplayName().equalsIgnoreCase(this.player0Item.getItemMeta().getDisplayName())) {
                return true;
            }
            if (this.inventory.getItem(6).getItemMeta().getDisplayName().equalsIgnoreCase(this.player1Item.getItemMeta().getDisplayName()) && this.inventory.getItem(7).getItemMeta().getDisplayName().equalsIgnoreCase(this.player1Item.getItemMeta().getDisplayName()) && this.inventory.getItem(8).getItemMeta().getDisplayName().equalsIgnoreCase(this.player1Item.getItemMeta().getDisplayName())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin() {
        if (this.inventory.getItem(0) != null && this.inventory.getItem(3) != null && this.inventory.getItem(6) != null) {
            if (this.inventory.getItem(0).getItemMeta().getDisplayName().equalsIgnoreCase(this.player0Item.getItemMeta().getDisplayName()) && this.inventory.getItem(3).getItemMeta().getDisplayName().equalsIgnoreCase(this.player0Item.getItemMeta().getDisplayName()) && this.inventory.getItem(6).getItemMeta().getDisplayName().equalsIgnoreCase(this.player0Item.getItemMeta().getDisplayName())) {
                return true;
            }
            if (this.inventory.getItem(0).getItemMeta().getDisplayName().equalsIgnoreCase(this.player1Item.getItemMeta().getDisplayName()) && this.inventory.getItem(3).getItemMeta().getDisplayName().equalsIgnoreCase(this.player1Item.getItemMeta().getDisplayName()) && this.inventory.getItem(6).getItemMeta().getDisplayName().equalsIgnoreCase(this.player1Item.getItemMeta().getDisplayName())) {
                return true;
            }
        }
        if (this.inventory.getItem(1) != null && this.inventory.getItem(4) != null && this.inventory.getItem(7) != null) {
            if (this.inventory.getItem(1).getItemMeta().getDisplayName().equalsIgnoreCase(this.player0Item.getItemMeta().getDisplayName()) && this.inventory.getItem(4).getItemMeta().getDisplayName().equalsIgnoreCase(this.player0Item.getItemMeta().getDisplayName()) && this.inventory.getItem(7).getItemMeta().getDisplayName().equalsIgnoreCase(this.player0Item.getItemMeta().getDisplayName())) {
                return true;
            }
            if (this.inventory.getItem(1).getItemMeta().getDisplayName().equalsIgnoreCase(this.player1Item.getItemMeta().getDisplayName()) && this.inventory.getItem(4).getItemMeta().getDisplayName().equalsIgnoreCase(this.player1Item.getItemMeta().getDisplayName()) && this.inventory.getItem(7).getItemMeta().getDisplayName().equalsIgnoreCase(this.player1Item.getItemMeta().getDisplayName())) {
                return true;
            }
        }
        if (this.inventory.getItem(2) != null && this.inventory.getItem(5) != null && this.inventory.getItem(8) != null) {
            if (this.inventory.getItem(2).getItemMeta().getDisplayName().equalsIgnoreCase(this.player0Item.getItemMeta().getDisplayName()) && this.inventory.getItem(5).getItemMeta().getDisplayName().equalsIgnoreCase(this.player0Item.getItemMeta().getDisplayName()) && this.inventory.getItem(8).getItemMeta().getDisplayName().equalsIgnoreCase(this.player0Item.getItemMeta().getDisplayName())) {
                return true;
            }
            if (this.inventory.getItem(2).getItemMeta().getDisplayName().equalsIgnoreCase(this.player1Item.getItemMeta().getDisplayName()) && this.inventory.getItem(5).getItemMeta().getDisplayName().equalsIgnoreCase(this.player1Item.getItemMeta().getDisplayName()) && this.inventory.getItem(8).getItemMeta().getDisplayName().equalsIgnoreCase(this.player1Item.getItemMeta().getDisplayName())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin() {
        if (this.inventory.getItem(0) != null && this.inventory.getItem(4) != null && this.inventory.getItem(8) != null) {
            return this.checkRowCol(this.inventory.getItem(0).getType(), this.inventory.getItem(4).getType(), this.inventory.getItem(8).getType());
        }
        return this.inventory.getItem(2) != null && this.inventory.getItem(4) != null && this.inventory.getItem(6) != null && this.checkRowCol(this.inventory.getItem(2).getType(), this.inventory.getItem(4).getType(), this.inventory.getItem(6).getType());
    }

    private boolean checkRowCol(final Material c1, final Material c2, final Material c3) {
        return c1.getData().getName() == c2.getData().getName() && c2.getData().getName() == c3.getData().getName() && (c1 != Material.AIR && c1 == c2 && c2 == c3);
    }

    public void changePlayer() {
        if (this.currentPlayer.getName().equalsIgnoreCase(this.player0.getName())) {
            this.currentPlayer = this.player1;
        }
        else {
            this.currentPlayer = this.player0;
        }
    }

    public boolean placeMark(final Player player, final int slot) {
        if (slot >= 0 && slot <= 8 && (this.inventory.getItem(slot) == null || this.inventory.getItem(slot).getType() == Material.AIR)) {
            if (player.getName().equalsIgnoreCase(this.player0.getName())) {
                this.inventory.setItem(slot, this.player0Item);
            }
            else if (player.getName().equalsIgnoreCase(this.player1.getName())) {
                this.inventory.setItem(slot, this.player1Item);
            }
            this.player0.updateInventory();
            this.player1.updateInventory();
            return true;
        }
        return false;
    }

    public void playSound(final Player player) {
        if (player.getName().equalsIgnoreCase(this.player0.getName())) {
            this.player0.playSound(this.player0.getLocation(), Sound.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON, 1.0f, 1.0f);
            this.player1.playSound(this.player1.getLocation(), Sound.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON, 0.5f, 0.5f);
        }
        else if (player.getName().equalsIgnoreCase(this.player1.getName())) {
            this.player1.playSound(this.player1.getLocation(), Sound.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON, 1.0f, 1.0f);
            this.player0.playSound(this.player0.getLocation(), Sound.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON, 0.5f, 0.5f);
        }
    }

    public boolean isCurrentPlayer(final Player player) {
        return this.currentPlayer.getName().equalsIgnoreCase(player.getName());
    }

    public Player getWinner() {
        this.isFinished = true;
        if (this.currentPlayer.getName().equalsIgnoreCase(this.player0.getName())) {
            this.player0.playSound(this.player0.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
            this.player1.playSound(this.player1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1.0f, 1.0f);
        }
        else if (this.currentPlayer.getName().equalsIgnoreCase(this.player1.getName())) {
            this.player0.playSound(this.player0.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1.0f, 1.0f);
            this.player1.playSound(this.player1.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        }
        return this.currentPlayer;
    }

    public void stopGame(final Player winner) {
        this.player0.closeInventory();
        this.player1.closeInventory();
        challenge.remove(this.player0);
        challenge.remove(this.player1);
        final Player player0 = this.player0;
        player0.sendMessage(Language.apiprefix + "§e" + winner.getName() + " §7hat gewonnen!");
        final Player player2 = this.player1;
        player2.sendMessage(Language.apiprefix + "§e" + winner.getName() + " §7hat gewonnen!");
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public void setFinished(final boolean isFinished) {
        this.isFinished = isFinished;
    }

    public Player getPlayer0() {
        return this.player0;
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}
