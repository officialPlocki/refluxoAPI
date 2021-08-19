package me.refluxo.api;

import me.refluxo.api.commands.*;
import me.refluxo.api.commands.activateable.MoneyCommand;
import me.refluxo.api.commands.activateable.PayCommand;
import me.refluxo.api.commands.activateable.TradeCommand;
import me.refluxo.api.utils.events.Chat;
import me.refluxo.api.utils.events.Death;
import me.refluxo.api.utils.events.Join;
import me.refluxo.api.utils.events.Quit;
import me.refluxo.api.utils.files.FileBuilder;
import me.refluxo.api.utils.mysql.MySQLService;
import me.refluxo.api.utils.pets.PetMovement;
import me.refluxo.api.utils.pets.pet.PetDB;
import me.refluxo.api.utils.player.APIPlayer;
import me.refluxo.api.utils.player.Command;
import me.refluxo.api.utils.player.Share;
import me.refluxo.api.utils.server.Console;
import me.refluxo.api.utils.server.VersionChecker;
import me.refluxo.api.utils.server.global.ems.EMSApi;
import me.refluxo.api.utils.server.local.CoinsAPI;
import me.refluxo.api.utils.server.local.VaultEconomy;
import me.refluxo.api.utils.server.plugin.ListenerManager;
import me.refluxo.api.utils.server.plugin.PermissionManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RefluxoAPI extends JavaPlugin {
    private static Plugin pl;

    private static FileBuilder fb;
    private static YamlConfiguration yml;

    public static String server;

    @Override
    public void onEnable() {
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.broadcastMessage("API is loaded for Version "+ VersionChecker.getVersion());
        new Console("API is loaded for version "+VersionChecker.getVersion(), "ServerAPI.java");
        pl = this;
        fb = new FileBuilder("commands.yml");
        yml = fb.getYaml();
        new ListenerManager(new Chat()).registerListener();
        new ListenerManager(new Death()).registerListener();
        new ListenerManager(new Join()).registerListener();
        new ListenerManager(new Quit()).registerListener();
        new ListenerManager(new EMSCommand()).registerListener();
        new ListenerManager(new Command()).registerListener();
        CoinsAPI.setup();
        registerEconomy();
        MySQLService service = new MySQLService();
        MySQLService.connect("localhost", "in", "api", "ko0l99", "3306");
        if(!MySQLService.isConnected()) {
            new Console("MySQL ist nicht verbunden. API wird deaktiviert.", "ServerAPI.java");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS bits(playerBits int(16), UUID varchar(36))");
            service.executeUpdate(ps);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS ems(level int(16), uuid varchar(36))");
            service.executeUpdate(ps);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS playerdata(servercon TEXT, uuid varchar(36), inventory LONGBLOB, level BIGINT(255), armor LONGBLOB, extra LONGBLOB)");
            service.executeUpdate(ps);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS settings(uuid varchar(36), ChatAds TEXT, InventoryAnimations TEXT, Notifications TEXT, Particles TEXT, Sound TEXT, AllowFriendRequests TEXT, AllowMessagesFromEveryone TEXT, AllowMessages TEXT)");
            service.executeUpdate(ps);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS pets(uuid varchar(36), pet TEXT, petType TEXT, active TEXT)");
            service.executeUpdate(ps);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        MySQLService.setMaxConnections();
        PermissionManager permissionManager = new PermissionManager("ServerAPI");
        permissionManager.addPermission("api.cmd");
        permissionManager.addPermission("api.gamemode.survival");
        permissionManager.addPermission("api.gamemode.creative");
        permissionManager.addPermission("api.gamemode.spectator");
        permissionManager.addPermission("api.gamemode.other");
        permissionManager.addPermission("api.gamemode");
        permissionManager.addPermission("api.ecoadminbits");
        permissionManager.addPermission("api.ecoadminmoney");
        permissionManager.addPermission("api.chat.color");
        permissionManager.addPermission("api.command.tab.bypass");
        permissionManager.addPermission("api.trade.more");
        permissionManager.addPermission("api.day");
        permissionManager.addPermission("api.night");
        permissionManager.addPermission("api.clearchat");
        permissionManager.addPermission("api.clearchat.bypass");
        permissionManager.build();
        if(!yml.isSet("sync.name")) {
            yml.set("sync.name", "sampleSync");
            yml.set("sync.active", false);
        }
        Share.enabled = yml.getBoolean("sync.active");
        if(Share.enabled) {
            server = yml.getString("sync.name");
        } else {
            server = "Sync is disabled.";
        }
        if(!yml.isSet("command.activated.pay")) {
            yml.set("command.activated.pay", false);
            yml.set("command.activated.money", false);
            fb.save();
        }
        if(!yml.isSet("command.activated.trade")) {
            yml.set("command.activated.trade", false);
            fb.save();
        }
        if(yml.getBoolean("command.activated.pay")) {
            getCommand("pay").setExecutor(new PayCommand());
            getCommand("zahlen").setExecutor(new PayCommand());
        }
        if(yml.getBoolean("command.activated.trade")) {
            getCommand("trade").setExecutor(new TradeCommand());
            getCommand("t").setExecutor(new TradeCommand());
        }
        if(yml.getBoolean("command.activated.money")) {
            getCommand("money").setExecutor(new MoneyCommand());
            getCommand("euro").setExecutor(new MoneyCommand());
            getCommand("dollar").setExecutor(new MoneyCommand());
            getCommand("balance").setExecutor(new MoneyCommand());
            getCommand("bal").setExecutor(new MoneyCommand());
            getCommand("geld").setExecutor(new MoneyCommand());
            getCommand("bargeld").setExecutor(new MoneyCommand());
            getCommand("coin").setExecutor(new MoneyCommand());
            getCommand("coins").setExecutor(new MoneyCommand());
        }
        getCommand("serverapi").setExecutor(new ServerAPICommand());
        getCommand("bapi").setExecutor(new ServerAPICommand());
        getCommand("api").setExecutor(new ServerAPICommand());
        getCommand("bits").setExecutor(new BitsCommand());
        getCommand("bit").setExecutor(new BitsCommand());
        getCommand("ecoadminmoney").setExecutor(new EcoAdminMoneyCommand());
        getCommand("ecoadminbits").setExecutor(new EcoAdminBitsCommand());
        getCommand("switch").setExecutor(new SwitchCommand());
        getCommand("eam").setExecutor(new EcoAdminMoneyCommand());
        getCommand("eab").setExecutor(new EcoAdminBitsCommand());
        getCommand("gm").setExecutor(new GamemodeCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("ems").setExecutor(new EMSCommand());
        getCommand("gamem").setExecutor(new GamemodeCommand());
        getCommand("day").setExecutor(new DayCommand());
        getCommand("night").setExecutor(new NightCommand());
        getCommand("cc").setExecutor(new ClearChatCommand());
        getCommand("clearc").setExecutor(new ClearChatCommand());
        getCommand("clearchat").setExecutor(new ClearChatCommand());
        getCommand("chatc").setExecutor(new ClearChatCommand());
        getCommand("chatclear").setExecutor(new ClearChatCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        if(Share.enabled) {
            autoSave();
        }
        autoBroadcast();
        EMSApi.generate();
        PetDB.init();
        PetMovement.init();
    }

    @Override
    public void onDisable() {
        MySQLService.disconnect();
    }

    public static Plugin getInstance() {
        return pl;
    }

    private void autoBroadcast() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(getInstance(), new Runnable() {

            int i = 0;
            @Override
            public void run() {
                switch (i) {
                    case 1:
                        i = 2;
                        Bukkit.broadcastMessage("§8»\n\n§8» §eVersuche jetzt §b/ems§e!\n\n§8»");
                        return;
                    case 2:
                        i = 3;
                        Bukkit.broadcastMessage("§8»\n\n§8» §eUnser Discord:§r https://refluxo.link/discord\n\n§8»");
                        return;
                    case 3:
                        i = 4;
                        Bukkit.broadcastMessage("§8»\n\n§8» §eUnser TeamSpeak:§r ts.refluxo.me\n\n§8»");
                        return;
                    case 4:
                        i = 5;
                        Bukkit.broadcastMessage("§8»\n\n§8» §eVerwendete Plugins:§r https://refluxo.link/plugins\n\n§8»");
                        return;
                    case 5:
                        i = 6;
                        Bukkit.broadcastMessage("§8»\n\n§8» §eUnsere Website:§r https://refluxo.me\n\n§8»");
                        return;
                    case 6:
                        i = 1;
                        Bukkit.broadcastMessage("§8»\n\n§8» §eHier kannst du unsere Partner sehen:§r https://refluxo.link/partner\n\n§8»");
                        return;
                }
            }
        }, 20, 2400);
    }

    private void autoSave() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(getInstance(), () -> {
            for(Player all : Bukkit.getOnlinePlayers()) {
                new APIPlayer(all).getPlayerShare().saveAll();
            }
        }, 20, 6000);
    }

    private void registerEconomy() {
        if (this.getServer().getPluginManager().getPlugin("Vault") != null) {
            final ServicesManager sm = this.getServer().getServicesManager();
            sm.register(Economy.class, new VaultEconomy(), this, ServicePriority.Highest);
        } else {
            System.out.println("Vault not found.");
        }
    }

}
