package me.refluxo.api.utils.server.settings;

import me.refluxo.api.utils.server.settings.enums.SettingInstances;
import me.refluxo.api.utils.server.settings.enums.Settings;

public class Instance {

    public static SettingInstances getRightInstanceForSetting(Settings setting) {

        switch (setting) {
            case AllowFriendRequests:
            case Sound:
            case Particles:
            case Notifications:
            case InventoryAnimations:
            case ChatAds:
            case AllowMessagesFromEveryone:
            case AllowMessages:
                return SettingInstances.MYSQL;
            case NavigatorInHotbar:
            case AutomaticTeleportToSpawnOnJoin:
                return SettingInstances.YAML;
        }

        return null;
    }

}
