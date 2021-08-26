package me.refluxo.api.spigot.utils.server.trade;

import me.refluxo.api.spigot.utils.player.APIPlayer;

import java.util.concurrent.ThreadLocalRandom;

public class Trade {

    private APIPlayer player;
    private int amount;
    private double dup;
    private double raw;
    private double fin;

    public Trade(APIPlayer p) {
        player = p;
    }

    public Trade newTrade(int bits) {
        amount = bits;
        double d = (ThreadLocalRandom.current().nextDouble() * (3 - 1)) + 1;
        if(player.getPlayer().hasPermission("api.trade.more")) {
            d = d+0.5;
        }
        dup = d;
        fin = (amount*2.6)*d;
        raw = amount*2.6;
        return this;
    }

    public void trade() {
        player.removeBits(amount);
        player.addCoins(fin);
    }

    public double getDup() {
        return dup;
    }

    public double getRaw() {
        return raw;
    }

    public double getFin() {
        return fin;
    }

}
