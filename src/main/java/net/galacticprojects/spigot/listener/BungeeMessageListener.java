package net.galacticprojects.spigot.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import eu.cloudnetservice.driver.CloudNetDriver;
import eu.cloudnetservice.modules.bridge.player.CloudPlayer;
import eu.cloudnetservice.modules.bridge.player.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.util.Objects;

public class BungeeMessageListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
        if(!(channel.equals("BedWars"))) {
            return;
        }

        ByteArrayDataInput byteArrayDataInput = ByteStreams.newDataInput(message);
        String subChannel = byteArrayDataInput.readUTF();
        PlayerManager playerManager = CloudNetDriver.instance().serviceRegistry().firstProvider(PlayerManager.class);
        @NotNull CloudPlayer cloudPlayer = Objects.requireNonNull(playerManager.onlinePlayer(player.getUniqueId()));

        if(subChannel.equals(cloudPlayer.connectedService().serviceId().toString())) {
            // TODO
        }

    }
}
