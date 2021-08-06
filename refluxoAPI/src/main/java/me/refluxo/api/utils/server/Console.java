package me.refluxo.api.utils.server;

import org.bukkit.Bukkit;

public class Console {
    public Console(String msg, ConsoleClassType from) {
        Bukkit.getConsoleSender().sendMessage(from.name()+":\n\n"+msg);
    }
    public Console(String msg, String from) {
        Bukkit.getConsoleSender().sendMessage(from+":\n\n"+msg);
    }
}
