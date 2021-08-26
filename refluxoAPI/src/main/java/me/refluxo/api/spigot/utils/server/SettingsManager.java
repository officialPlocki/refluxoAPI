package me.refluxo.api.spigot.utils.server;

import me.refluxo.api.spigot.utils.mysql.MySQLService;
import me.refluxo.api.spigot.utils.player.APIPlayer;
import me.refluxo.api.spigot.utils.server.settings.enums.*;
import me.refluxo.api.spigot.utils.server.settings.save.SettingsMySQL;
import me.refluxo.api.spigot.utils.server.settings.save.SettingsYaml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SettingsManager {

    private static final MySQLService mySQLService = new MySQLService();
    private static final Connection con = mySQLService.getConnection();

    public SettingsManager() {}

    public void toggleSetting(APIPlayer apiPlayer, Settings setting) {
        switch (setting) {
            case AllowFriendRequests:
            case Sound:
            case InventoryAnimations:
            case AllowMessages:
            case AutomaticTeleportToSpawnOnJoin:
            case NavigatorInHotbar:
                switch (getSettingValue(apiPlayer, setting)) {
                    case YES:
                        setSetting(apiPlayer, setting, Values.NO);
                        break;
                    case NO:
                        setSetting(apiPlayer, setting, Values.YES);
                        break;
                }
                break;
            case Particles:
                switch (getSettingValue(apiPlayer, setting)) {
                    case EVERY:
                        setSetting(apiPlayer, setting, Values.LESS);
                        break;
                    case LESS:
                        setSetting(apiPlayer, setting, Values.NONE);
                        break;
                    case NONE:
                        setSetting(apiPlayer, setting, Values.EVERY);
                        break;
                }
                break;
            case Notifications:
                switch (getSettingValue(apiPlayer, setting)) {
                    case EVERY:
                        setSetting(apiPlayer, setting, Values.SERVER);
                        break;
                    case SERVER:
                        setSetting(apiPlayer, setting, Values.ANNOUNCEMENTS);
                        break;
                    case ANNOUNCEMENTS:
                        setSetting(apiPlayer, setting, Values.NONE);
                        break;
                    case NONE:
                        setSetting(apiPlayer, setting, Values.EVERY);
                        break;
                }
                break;
            case ChatAds:
                switch (getSettingValue(apiPlayer, setting)) {
                    case EVERY:
                        setSetting(apiPlayer, setting, Values.SERVER);
                        break;
                    case SERVER:
                        setSetting(apiPlayer, setting, Values.PLAYERS);
                        break;
                    case PLAYERS:
                        setSetting(apiPlayer, setting, Values.NONE);
                        break;
                    case NONE:
                        setSetting(apiPlayer, setting, Values.EVERY);
                        break;
                }
                break;
            case AllowMessagesFromEveryone:
                switch (getSettingValue(apiPlayer, setting)) {
                    case Everyone:
                        setSetting(apiPlayer, setting, Values.OnlyFriends);
                        break;
                    case OnlyFriends:
                        setSetting(apiPlayer, setting, Values.Everyone);
                        break;
                }
                break;
        }
    }

    public String getSettingStatusMessage(APIPlayer apiPlayer, Settings setting) {
        Values v = getSettingValue(apiPlayer, setting);
        if(setting.equals(Settings.AllowFriendRequests)||
                setting.equals(Settings.Sound)||
                setting.equals(Settings.InventoryAnimations)||
                setting.equals(Settings.AllowMessages)||
                setting.equals(Settings.AutomaticTeleportToSpawnOnJoin)) {
            String msg;
            if(v.equals(Values.YES)) {
                msg = "§aAktiviert";
            } else {
                msg = "§cDeaktiviert";
            }
            return msg;
        } else if(setting.equals(Settings.Particles)) {
            String msg;
            if(v.equals(Values.EVERY)) {
                msg = "§aAlle";
            } else if(v.equals(Values.LESS)) {
                msg = "§6Begrenzt";
            } else {
                msg = "§cKeine";
            }
            return msg;
        } else if(setting.equals(Settings.Notifications)) {
            String msg;
            if(v.equals(Values.EVERY)) {
                msg = "§aAlle";
            } else if(v.equals(Values.SERVER)) {
                msg = "§6Server";
            } else if(v.equals(Values.ANNOUNCEMENTS)) {
                msg = "§6Ankündigungen";
            } else {
                msg = "§cKeine";
            }
            return msg;
        } else if(setting.equals(Settings.ChatAds)) {
            String msg;
            if (v.equals(Values.EVERY)) {
                msg = "§aJede Werbung";
            } else if (v.equals(Values.SERVER)) {
                msg = "§6Server Werbung";
            } else if (v.equals(Values.PLAYERS)) {
                msg = "§6Spieler Werbung";
            } else {
                msg = "§cKeine Werbung";
            }
            return msg;
        } else if(setting.equals(Settings.AllowMessagesFromEveryone)) {
            String msg;
            if(v.equals(Values.Everyone)) {
                msg = "§aVon jedem Spieler";
            } else {
                msg = "§cNur von Freunden";
            }
            return msg;
        } else {
            String msg;
            if(v.equals(Values.YES)) {
                msg = "§aItem in Inventar";
            } else {
                msg = "§cBefehle im Chat";
            }
            return msg;
        }
    }

    public Values getSettingValue(APIPlayer apiPlayer, Settings setting) {
        if(setting.equals(Settings.AllowFriendRequests)||
                setting.equals(Settings.AllowMessages)||
                setting.equals(Settings.AllowMessagesFromEveryone)||
                setting.equals(Settings.ChatAds)||
                setting.equals(Settings.Notifications)||
                setting.equals(Settings.Particles)||
                setting.equals(Settings.InventoryAnimations)||
                setting.equals(Settings.Sound)) {
            return SettingsMySQL.getSettingValue(apiPlayer, setting);
        } else {
            return SettingsYaml.getSettingValue(apiPlayer, setting);
        }
    }

    public String getSettingDisplayName(Settings setting) {
        String name = "";
        switch (setting) {
            case AllowFriendRequests:
                name = "§b§lFAs erlauben";
                break;
            case AllowMessages:
                name = "§c§lDMs erlauben";
                break;
            case AllowMessagesFromEveryone:
                name = "§c§lDMs von";
                break;
            case AutomaticTeleportToSpawnOnJoin:
                name = "§a§lAutomatisch zum Spawn Teleportieren";
                break;
            case ChatAds:
                name = "§c§lWerbung im Chat";
                break;
            case InventoryAnimations:
                name = "§a§lInventaranimationen";
                break;
            case NavigatorInHotbar:
                name = "§a§lBedienung des Servers";
                break;
            case Notifications:
                name = "§c§lBenachrichtigungen";
                break;
            case Particles:
                name = "§a§lPartikel";
                break;
            case Sound:
                name = "§a§lTöne";
                break;
        }
        return name;
    }

    public void setSetting(APIPlayer apiPlayer, Settings setting, Values value) {
        if(setting.equals(Settings.AllowFriendRequests)||
                setting.equals(Settings.AllowMessages)||
                setting.equals(Settings.AllowMessagesFromEveryone)||
                setting.equals(Settings.ChatAds)||
                setting.equals(Settings.Notifications)||
                setting.equals(Settings.Particles)||
                setting.equals(Settings.InventoryAnimations)||
                setting.equals(Settings.Sound)) {
            SettingsMySQL.setSettingToValue(apiPlayer, setting, value);
        } else {
            SettingsYaml.setSettingToValue(apiPlayer, setting, value);
        }
    }

    /**
     * @apiNote ONLY FOR SETTINGS INSTANCES!!!
     * @implNote Setting Instance (MySQL or Yaml)
     */
    public static void setup(APIPlayer apiPlayer) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO settings(uuid, ChatAds, InventoryAnimations, Notifications, Particles, Sound, AllowFriendRequests, AllowMessagesFromEveryone, AllowMessages) VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, apiPlayer.getPlayer().getUniqueId().toString());
            ps.setString(2, Values.EVERY.name());
            ps.setString(3, Values.YES.name());
            ps.setString(4, Values.EVERY.name());
            ps.setString(5, Values.EVERY.name());
            ps.setString(6, Values.YES.name());
            ps.setString(7, Values.YES.name());
            ps.setString(8, Values.Everyone.name());
            ps.setString(9, Values.YES.name());
            mySQLService.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
