package me.refluxo.api.utils.server.plugin;

import me.refluxo.api.RefluxoAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class ListenerManager {

    private Listener lis;

    public ListenerManager(Listener listener) {
        lis = listener;
    }

    public void registerListener() {
        Bukkit.getPluginManager().registerEvents(lis, RefluxoAPI.getInstance());
    }

}
