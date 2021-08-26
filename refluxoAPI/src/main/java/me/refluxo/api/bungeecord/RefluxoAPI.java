package me.refluxo.api.bungeecord;

import me.refluxo.api.bungeecord.utils.events.CommandTab;
import me.refluxo.api.bungeecord.utils.mysql.MySQLService;
import me.refluxo.api.bungeecord.utils.server.global.ems.EMSApi;
import me.refluxo.api.bungeecord.utils.server.plugin.ListenerManager;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

public class RefluxoAPI extends Plugin {

    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        MySQLService.connect("localhost", "in", "api", "ko0l99", "3306");
        if(!MySQLService.isConnected()) {
            BungeeCord.getInstance().getConsole().sendMessage(TextComponent.fromLegacyText("MySQL ist nicht verbunden. API ist nun deaktiviert. Es wird ein neustart empfohlen-"));
            this.onDisable();
        }
        new ListenerManager(new CommandTab()).registerListener();
        EMSApi.generate();
    }

    @Override
    public void onDisable() {

    }

    public static Plugin getInstance() {
        return instance;
    }

}
