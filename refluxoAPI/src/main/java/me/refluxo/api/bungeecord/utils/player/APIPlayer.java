package me.refluxo.api.bungeecord.utils.player;

import me.refluxo.api.bungeecord.utils.server.global.BitsAPI;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class APIPlayer {

    private final ProxiedPlayer player;

    public APIPlayer(ProxiedPlayer p) {
        this.player = p;
    }

    public ProxiedPlayer getPlayer() {
        return player;
    }

    public String getName() {
        return player.getName();
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

}
