package io.myzticbean.finditemaddon.Listeners;

import io.myzticbean.finditemaddon.FindItemAddOn;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerCommandSendEventListener implements Listener {
    @EventHandler
    public void onPlayerCommandTab(PlayerCommandSendEvent event) {
        if(!event.getPlayer().isOp()) {
            List<String> blockedCommands = new ArrayList<>();
            blockedCommands.add("finditem:finditem");
            blockedCommands.add("finditemadmin:finditemadmin");
            blockedCommands.add("finditemadmin:fiadmin");
            blockedCommands.add("finditemadmin");
            blockedCommands.add("fiadmin");
            event.getCommands().removeAll(blockedCommands);
        }
    }
}
