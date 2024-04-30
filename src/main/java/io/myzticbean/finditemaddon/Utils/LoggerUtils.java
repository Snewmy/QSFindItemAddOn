package io.myzticbean.finditemaddon.Utils;

import io.myzticbean.finditemaddon.FindItemAddOn;
import org.bukkit.Bukkit;

public class LoggerUtils {
    public static void logDebugInfo(String text) {
        if(FindItemAddOn.getConfigProvider().DEBUG_MODE) {
            Bukkit.getLogger().warning(Utils.chat("[CloudShopSearch-DebugLog] " + text));
        }
    }
    public static void logInfo(String text) {
        Bukkit.getLogger().info(Utils.chat("[CloudShopSearch] " + text));
    }
    public static void logError(String text) {
        Bukkit.getLogger().severe(Utils.chat("[CloudShopSearch] " + text));
    }
    public static void logError(Exception e) {
        Bukkit.getLogger().severe(Utils.chat("[CloudShopSearch] " + e.getMessage()));
        e.printStackTrace();
    }
    public static void logWarning(String text) {
//        Bukkit.getConsoleSender().sendMessage(Utils.chat("[QSFindItemAddOn] &6" + text));
        Bukkit.getLogger().warning(Utils.chat("[CloudShopSearch] " + text));
    }
}
