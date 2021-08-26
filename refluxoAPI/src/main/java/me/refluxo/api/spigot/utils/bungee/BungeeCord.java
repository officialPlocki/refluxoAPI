package me.refluxo.api.spigot.utils.bungee;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.refluxo.api.spigot.RefluxoAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;

public class BungeeCord {

    public BungeeCord() {}

    public void sendPlayer(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF(Objects.requireNonNull(player.getPlayer()).getName());
        out.writeUTF(server);
        Bukkit.getServer().sendPluginMessage(RefluxoAPI.getInstance(), "BungeeCord", out.toByteArray());
    }
    public void kickPlayer(Player player, String message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("KickPlayer");
        out.writeUTF(Objects.requireNonNull(player.getPlayer()).getName());
        out.writeUTF(message);
        Bukkit.getServer().sendPluginMessage(RefluxoAPI.getInstance(), "BungeeCord", out.toByteArray());
    }

}
