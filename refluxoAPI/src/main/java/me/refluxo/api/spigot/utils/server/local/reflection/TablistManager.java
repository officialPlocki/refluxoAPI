package me.refluxo.api.spigot.utils.server.local.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.Map.Entry;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TablistManager {
    private static TablistManager instance;
    private final HashMap<String, Object> teams;
    public static TablistManager getInstance() {
        if (instance == null)
            instance = new TablistManager();
        return instance;
    }
    public TablistManager() {
        teams = new HashMap<>();
        initScoreboardTeams();
    }
    @SuppressWarnings("rawtypes")
    public void updateTeams() {
        for (Entry entry : teams.entrySet()) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                try {
                    Constructor<?> scoreboardTeamConstructor = Objects.requireNonNull(getNMSClass("PacketPlayOutScoreboardTeam")).getConstructor(getNMSClass("ScoreboardTeam"), int.class);
                    sendPacket(all, scoreboardTeamConstructor.newInstance(entry.getValue(), 1));
                    sendPacket(all, scoreboardTeamConstructor.newInstance(entry.getValue(), 0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @SuppressWarnings("unchecked")
    public void addToTeam(Player player, String team) {
        teams.forEach((key, value) -> {
            Object packet = teams.get(key);
            try {
                Field f = packet.getClass().getDeclaredField("c");
                f.setAccessible(true);
                Set<String> list = new HashSet<>((Collection<? extends String>) f.get(packet));
                if (list.contains(player.getName())) {
                    list.remove(player.getName());
                    setField(packet, "c", list);
                    teams.put(key, packet);
                }
                setPlayer(player, team);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void initScoreboardTeams() {
        try {
            Constructor<?> boardConstructor = Objects.requireNonNull(getNMSClass("Scoreboard")).getConstructor();
            Object board = boardConstructor.newInstance();
            createTeam("00inhaber", "inhaber", "§4Inhaber §8» §7", board);
            createTeam("01manager", "manager", "§4Manager §8» §7", board);
            createTeam("02admin", "admin", "§cAdmin §8» §7", board);
            createTeam("03developer", "developer", "§bDeveloper §8» §7", board);
            createTeam("04moderator", "moderator", "§cModerator §8» §7", board);
            createTeam("05builder", "builder", "§3Builder §8» §7", board);
            createTeam("06supporter", "supporter", "§eSupporter §8» §7", board);
            createTeam("07freund", "freund", "§bFreund §8» §7", board);
            createTeam("09streamer", "streamer", "§5Streamer §8» §7", board);
            createTeam("10youtuber", "youtuber", "§5YouTuber §8» §7", board);
            createTeam("11fuchs", "fuchs", "§dFuchs §8» §7", board);
            createTeam("09papagei", "papagei", "§aPapagei §8» §7", board);
            createTeam("09premium", "premium", "§6Premium §8» §7", board);
            createTeam("09spieler", "spieler", "§7Spieler §8» §7", board);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void createTeam(String teamname, String hashMapName, String prefix, Object board) {
        try {
            Constructor<?> teamConstructor = Objects.requireNonNull(getNMSClass("ScoreboardTeam")).getConstructor(getNMSClass("Scoreboard"), String.class);
            Object packet = teamConstructor.newInstance(board, teamname);
            Object enumVisibility = Objects.requireNonNull(getNMSClass("ScoreboardTeamBase")).getDeclaredClasses()[0].getField("ALWAYS").get(null);
            setField(packet, "j", enumVisibility);
            setField(packet, "e", prefix);
            teams.put(hashMapName, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void setPlayer(Player p, String HashMapName) {
        Object packet = teams.get(HashMapName);
        try {
            Field f = packet.getClass().getDeclaredField("c");
            f.setAccessible(true);
            Set<String> list = new HashSet<>((Collection<? extends String>) f.get(packet));
            list.add(p.getName());
            setField(packet, "c", list);
            teams.put(HashMapName, packet);
            for (Entry e : teams.entrySet()) {
                Constructor<?> scoreboardTeamConstructor = Objects.requireNonNull(getNMSClass("PacketPlayOutScoreboardTeam")).getConstructor(getNMSClass("ScoreboardTeam"), int.class);
                Object teampacket = scoreboardTeamConstructor.newInstance(e.getValue(), 3);
                sendPacket(p, teampacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setField(Object object, String fieldname, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldname);
            field.setAccessible(true);
            field.set(object, value);
            field.setAccessible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Class<?> getNMSClass(String name) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("net.minecraft.server." + version + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
