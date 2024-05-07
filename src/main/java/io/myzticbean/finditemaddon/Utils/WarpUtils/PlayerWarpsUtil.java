package io.myzticbean.finditemaddon.Utils.WarpUtils;

import com.olziedev.playerwarps.api.warp.Warp;
import io.myzticbean.finditemaddon.Dependencies.PlayerWarpsPlugin;
import io.myzticbean.finditemaddon.Utils.CommonUtils;
import io.myzticbean.finditemaddon.Utils.LoggerUtils;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PlayerWarpsUtil {

    @Nullable
    public Warp findNearestWarp(Location shopLocation) {
        List<Warp> allWarps = PlayerWarpsPlugin.getAllWarps();
        if (!allWarps.isEmpty()) {
            Map<Double, Warp> warpDistanceMap = new TreeMap<>();
            allWarps.forEach(warp -> {
                double distance = CommonUtils.calculateDistance3D(
                        shopLocation.getX(),
                        shopLocation.getY(),
                        shopLocation.getZ(),
                        warp.getWarpLocation().getX(),
                        warp.getWarpLocation().getY(),
                        warp.getWarpLocation().getZ()
                );
                if (distance <= 115) {
                    warpDistanceMap.put(distance, warp);
                }
            });
            if (!warpDistanceMap.isEmpty()) {
                return warpDistanceMap.entrySet().iterator().next().getValue(); // don't care if warp is locked or not
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}