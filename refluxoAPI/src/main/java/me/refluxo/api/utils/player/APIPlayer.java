package me.refluxo.api.utils.player;

import me.refluxo.api.ServerAPI;
import me.refluxo.api.utils.server.global.BitsAPI;
import me.refluxo.api.utils.server.local.CoinsAPI;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class APIPlayer {

    private Player player;

    private String prefix;
    private String suffix;
    private Share share;

    public APIPlayer(Player p) {
        suffix = "";
        this.player = p;
        prefix = getPrefixInit(p);
    }

    private String getPrefixInit(Player p) {
        share = new Share(p, ServerAPI.server);
        if(player.hasPermission("refluxo.inhaber")) {
            return "§4Inhaber §8» §7";
        } else if(player.hasPermission("refluxo.admin")) {
            return "§cAdmin §8» §7";
        } else if(player.hasPermission("refluxo.developer")) {
            return "§bDeveloper §8» §7";
        } else if(player.hasPermission("refluxo.moderator")) {
            return "§cModerator §8» §7";
        } else if(player.hasPermission("refluxo.builder")) {
            return "§3Builder §8» §7";
        } else if(player.hasPermission("refluxo.supporter")) {
            return "§eSupporter §8» §7";
        } else if(player.hasPermission("refluxo.freund")) {
            return "§bFreund §8» §7";
        } else if(player.hasPermission("refluxo.streamer")) {
            return "§5Streamer §8» §7";
        } else if(player.hasPermission("refluxo.youtuber")) {
            return "§5YouTuber §8» §7";
        } else if(player.hasPermission("refluxo.fuchs")) {
            return "§dFuchs §8» §7";
        } else if(player.hasPermission("refluxo.papagei")) {
            return "§aPapagei §8» §7";
        } else if(player.hasPermission("refluxo.premium")) {
            return "§6Premium §8» §7";
        } else {
            return "§7Spieler §8» §7";
        }
    }

    public Player getPlayer() {
        return player;
    }

    public String getName() {
        return player.getName();
    }

    public Share getPlayerShare() {
        return share;
    }

    public int getBits() {
        return BitsAPI.getBits(player);
    }

    public void addBits(int amount) {
        BitsAPI.addBits(player, amount);
    }

    public void removeBits(int amount) {
        BitsAPI.removeBits(player, amount);
    }

    public void setBits(int amount) {
        BitsAPI.setBits(player, amount);
    }

    public void resetBits() {
        BitsAPI.setBits(player, 0);
    }

    public double getCoins() {
        return CoinsAPI.getCoins(player);
    }

    public void addCoins(double amount) {
        CoinsAPI.addCoins(player, amount);
    }

    public void removeCoins(int amount) {
        CoinsAPI.removeCoins(player, amount);
    }

    public void setCoins(int amount) {
        CoinsAPI.setCoins(player, amount);
    }

    public void resetCoins() {
        CoinsAPI.setCoins(player, 0.0);
    }

    public GameMode getGameMode() {
        return player.getGameMode();
    }

    public boolean allowFlight() {
        return player.getAllowFlight();
    }

    public void setAllowFlight(boolean allow) {
        player.setAllowFlight(allow);
    }

    public String getPrefix() {
        return prefix.replaceAll("&", "§");
    }

    public String getSuffix() {
        return suffix.replaceAll("&", "§");
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix.replaceAll("&", "§");
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix.replaceAll("&", "§");
    }

}
