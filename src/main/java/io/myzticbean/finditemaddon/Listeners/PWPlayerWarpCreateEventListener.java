package io.myzticbean.finditemaddon.Listeners;

import com.olziedev.playerwarps.api.events.PlayerWarpCreateEvent;
import io.myzticbean.finditemaddon.Dependencies.PlayerWarpsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PWPlayerWarpCreateEventListener implements Listener {
    @EventHandler
    public void onPlayerWarpCreate(PlayerWarpCreateEvent e) {
        PlayerWarpsPlugin.updateWarpsOnEventCall(e.getPlayerWarp(), false);
    }
}