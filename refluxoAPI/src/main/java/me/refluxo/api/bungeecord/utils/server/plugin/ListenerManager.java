package me.refluxo.api.bungeecord.utils.server.plugin;

import me.refluxo.api.bungeecord.RefluxoAPI;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Listener;

public class ListenerManager {

    private final Listener lis;

    public ListenerManager(Listener listener) {
        lis = listener;
    }

    public void registerListener() {
        BungeeCord.getInstance().getPluginManager().registerListener(RefluxoAPI.getInstance(), lis);
    }

}
