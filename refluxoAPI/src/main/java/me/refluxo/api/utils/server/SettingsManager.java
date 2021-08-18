package me.refluxo.api.utils.server;

import me.refluxo.api.utils.player.APIPlayer;
import me.refluxo.api.utils.server.settings.Instance;
import me.refluxo.api.utils.server.settings.enums.*;
import me.refluxo.api.utils.server.settings.save.SettingsMySQL;
import me.refluxo.api.utils.server.settings.save.SettingsYaml;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SettingsManager {


    public SettingsManager() {}

    public void toggleSetting(APIPlayer apiPlayer, Settings setting) {
        Values value = null;
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
        String msg = "";

        switch (setting) {
            case AllowFriendRequests:
            case Sound:
            case InventoryAnimations:
            case AllowMessages:
            case AutomaticTeleportToSpawnOnJoin:
                switch (getSettingValue(apiPlayer, setting)) {
                    case YES:
                        msg = "§aAktiviert";
                        break;
                    case NO:
                        msg = "§cDeaktiviert";
                        break;
                }
                break;
            case Particles:
                switch (getSettingValue(apiPlayer, setting)) {
                    case EVERY:
                        msg = "§aAlle";
                        break;
                    case LESS:
                        msg = "§6Wenige";
                        break;
                    case NONE:
                        msg = "§cKeine";
                        break;
                }
                break;
            case Notifications:
                switch (getSettingValue(apiPlayer, setting)) {
                    case EVERY:
                        msg = "§aAlle";
                        break;
                    case SERVER:
                        msg = "§6Stats Informationen";
                        break;
                    case ANNOUNCEMENTS:
                        msg = "§6Ankündigungen";
                        break;
                    case NONE:
                        msg = "§cKeine";
                        break;
                }
                break;
            case ChatAds:
                switch (getSettingValue(apiPlayer, setting)) {
                    case EVERY:
                        msg = "§aAlles";
                        break;
                    case SERVER:
                        msg = "§6Refluxo Werbung";
                        break;
                    case PLAYERS:
                        msg = "§6Spieler Werbung";
                        break;
                    case NONE:
                        msg = "§cNichts";
                        break;
                }
                break;
            case AllowMessagesFromEveryone:
                switch (getSettingValue(apiPlayer, setting)) {
                    case Everyone:
                        msg = "§aVon Jedem";
                        break;
                    case OnlyFriends:
                        msg = "§cNur von Freunden";
                        break;
                }
                break;
            case NavigatorInHotbar:
                switch (getSettingValue(apiPlayer, setting)) {
                    case YES:
                        msg = "§aItem in Inventar";
                        break;
                    case NO:
                        msg = "§cBefehle im Chat";
                        break;
                }
                break;
        }
        return msg;
    }

    public Values getSettingValue(APIPlayer apiPlayer, Settings setting) {
        switch (Instance.getRightInstanceForSetting(setting)) {
            case MYSQL:
                return SettingsMySQL.getSettingValue(apiPlayer, setting);
            case YAML:
                return SettingsYaml.getSettingValue(apiPlayer, setting);
        }
        return null;
    }

    public String getSettingDisplayName(Settings setting) {
        String name = "";

        switch (setting) {
            case AllowFriendRequests:
                name = "Anfragen";
                break;
            case AllowMessages:
                name = "Direktnachrichten erlauben";
                break;
            case AllowMessagesFromEveryone:
                name = "Direktnachrichten erlauben für ...";
                break;
            case AutomaticTeleportToSpawnOnJoin:
                name = "Nach Join zum Spawn teleportieren";
                break;
            case ChatAds:
                name = "Chat Werbung";
                break;
            case InventoryAnimations:
                name = "Inventaranimationen";
                break;
            case NavigatorInHotbar:
                name = "Bedienung des Servers";
                break;
            case Notifications:
                name = "Benachrichtigungen";
                break;
            case Particles:
                name = "Partikel";
                break;
            case Sound:
                name = "Töne";
                break;
        }
        return name;
    }

    public void setSetting(APIPlayer apiPlayer, Settings setting, Values value) {
        switch (Instance.getRightInstanceForSetting(setting)) {
            case MYSQL:
                SettingsMySQL.setSettingToValue(apiPlayer, setting, value);
            case YAML:
                SettingsYaml.setSettingToValue(apiPlayer, setting, value);
        }
    }

    /**
     * @apiNote ONLY FOR SETTINGS INSTANCES!!!
     * @implNote Setting Instance (MySQL or Yaml)
     */
    public static void setup(APIPlayer apiPlayer) {
        SettingsManager m = new SettingsManager();
        for(Settings s : Settings.values()) {
            Values v = null;
            switch (s) {
                case AllowFriendRequests:
                case NavigatorInHotbar:
                case AllowMessages:
                case AutomaticTeleportToSpawnOnJoin:
                case InventoryAnimations:
                case Sound:
                    v = Values.YES;
                    break;
                case Particles:
                case ChatAds:
                case Notifications:
                    v = Values.EVERY;
                    break;
                case AllowMessagesFromEveryone:
                    v = Values.Everyone;
                    break;
            }
            m.setSetting(apiPlayer, s, v);
        }
    }

}
