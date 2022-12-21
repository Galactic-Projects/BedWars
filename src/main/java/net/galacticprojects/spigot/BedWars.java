package net.galacticprojects.spigot;

import net.galacticprojects.spigot.listener.BungeeMessageListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

public class BedWars extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        registerMessenger();
        registerListener();
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    public void registerListener() {

    }

    public void registerCommands() {
    }

    public void registerMessenger() {
        Messenger messenger = this.getServer().getMessenger();
        messenger.registerOutgoingPluginChannel(this, "BungeeCord");
        messenger.registerIncomingPluginChannel(this, "BedWars", new BungeeMessageListener());
    }
}
