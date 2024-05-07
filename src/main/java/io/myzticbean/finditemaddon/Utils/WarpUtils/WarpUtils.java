package io.myzticbean.finditemaddon.Utils.WarpUtils;

import io.myzticbean.finditemaddon.Dependencies.PlayerWarpsPlugin;
import io.myzticbean.finditemaddon.FindItemAddOn;

public class WarpUtils {
    public static void updateWarps() {
        if (FindItemAddOn.getConfigProvider().shopGUIItemLoreHasKey("{NEAREST_WARP}")) {
            if (PlayerWarpsPlugin.getIsEnabled()) {
                PlayerWarpsPlugin.updateAllWarpsFromAPI();
            }
        }
    }
}