package me.refluxo.api.bungeecord.utils.player;

import me.refluxo.api.bungeecord.utils.server.global.ems.EMSApi;

public class EMSPlayer {

    private APIPlayer player;

    public EMSPlayer(APIPlayer p) {
        player = p;
    }

    public int getLevel() {
        return EMSApi.getLevel(player);
    }

    public int getNextLevel() {
        return EMSApi.getNextLevel(player);
    }

    public int getCost(int level) {
        return EMSApi.getCost(level);
    }

    public int getNextCost() {
        return EMSApi.getNextCost(player);
    }

    public void setLevel(int level) {
        EMSApi.setLevel(player, level);
    }

    public int getBitGen() {
        return EMSApi.getBitGen(player);
    }

}
