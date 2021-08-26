package me.refluxo.api.bungeecord.utils.events;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class CommandTab implements Listener {

    @EventHandler
    public void onTab(net.md_5.bungee.api.event.TabCompleteEvent e) {
        e.getSuggestions().clear();
        e.getSuggestions().add("friend");
        e.getSuggestions().add("setting");
        e.getSuggestions().add("settings");
        e.getSuggestions().add("friends");
        e.getSuggestions().add("coins");
        e.getSuggestions().add("coin");
        e.getSuggestions().add("money");
        e.getSuggestions().add("help");
        e.getSuggestions().add("hilfe");
        e.getSuggestions().add("refluxoapi");
        e.getSuggestions().add("pay");
        e.getSuggestions().add("trade");
        e.getSuggestions().add("bit");
        e.getSuggestions().add("bits");
        e.getSuggestions().add("ems");
        e.getSuggestions().add("switch");
    }

}
