package io.myzticbean.finditemaddon.Listeners;

import io.myzticbean.finditemaddon.FindItemAddOn;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;

public class PluginEnableEventListener implements Listener {
    @EventHandler
    public void onPluginEnable(PluginEnableEvent event) {
        if(!FindItemAddOn.isQSHikariInstalled()) {
//            if(!event.getPlugin().getName().equalsIgnoreCase("QuickShop")
//                    && !event.getPlugin().getName().equalsIgnoreCase("QuickShop-Hikari")) {
//                // do nothing
//            }
            if (event.getPlugin().getName().equalsIgnoreCase("QuickShop-Hikari")) {
                FindItemAddOn.setQSHikariInstalled(true);
            }
        }
    }
}
