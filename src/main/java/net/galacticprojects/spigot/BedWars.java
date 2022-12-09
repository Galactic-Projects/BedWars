package net.galacticprojects.spigot;

import net.galacticprojects.spigot.channel.BungeeMessage;
import net.galacticprojects.spigot.listener.BungeeMessageListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class BedWars extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BedWars", new BungeeMessageListener());
    }

    @Override
    public void onDisable() {

    }
}
