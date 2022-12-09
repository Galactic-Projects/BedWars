package net.galacticprojects.spigot.channel;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.galacticprojects.spigot.BedWars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BungeeMessage {

    BedWars system;
    Player player;

    public BungeeMessage(Player player, BedWars system) {
        this.player = player;
        this.system = system;
    }

    public void sendMessageData(int type, String data) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Proxy");
        out.writeUTF("Argument");
        if(player == null) {
            player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        }
        player.sendPluginMessage(system, "BungeeCord", out.toByteArray());
    }

    public void sendMultipleMessageData(int type, String... data) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Proxy");
        for(String utf : data) {
            out.writeUTF(utf);
        }
        if(player == null) {
            player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        }
        player.sendPluginMessage(system, "BungeeCord", out.toByteArray());

    }

    public void sendMultipleMessageData(int type, Integer... data) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Proxy");
        for(int utf : data) {
            out.writeInt(utf);
        }
        if(player == null) {
            player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        }
        player.sendPluginMessage(system, "BungeeCord", out.toByteArray());

    }

    public void sendMessageData(int type, int data) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Proxy");
        out.writeUTF("Argument");
        if(player == null) {
            player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        }
        player.sendPluginMessage(system, "BungeeCord", out.toByteArray());
    }

}
