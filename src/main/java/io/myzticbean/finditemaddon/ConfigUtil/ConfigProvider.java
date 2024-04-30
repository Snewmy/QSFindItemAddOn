package io.myzticbean.finditemaddon.ConfigUtil;

import io.myzticbean.finditemaddon.Utils.LoggerUtils;
import io.myzticbean.finditemaddon.Utils.Utils;
import me.kodysimpson.simpapi.colors.ColorTranslator;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public class ConfigProvider {

    public final String PLUGIN_PREFIX = Utils.chat(ConfigSetup.get().getString("plugin-prefix"));
    public final String SHOP_SEARCH_LOADING_MSG = ConfigSetup.get().getString("shop-search-loading-message");
    public final int SHOP_SORTING_METHOD = ConfigSetup.get().getInt("shop-sorting-method");
    public final List<String> SHOP_GUI_ITEM_LORE = (List<String>) ConfigSetup.get().getList("shop-gui-item-lore");
    public final boolean TP_PLAYER_DIRECTLY_TO_SHOP = ConfigSetup.get().getBoolean("player-shop-teleportation.direct-shop-tp-mode.tp-player-directly-to-shop");
    public final String CLICK_TO_TELEPORT_MSG = ConfigSetup.get().getString("player-shop-teleportation.direct-shop-tp-mode.click-to-teleport-message");
    public final String SHOP_TP_NO_PERMISSION_MSG = ConfigSetup.get().getString("player-shop-teleportation.direct-shop-tp-mode.shop-tp-no-permission-message");
    public final String UNSAFE_SHOP_AREA_MSG = ConfigSetup.get().getString("player-shop-teleportation.direct-shop-tp-mode.unsafe-shop-area-message");
    public final String TP_DELAY_IN_SECONDS = ConfigSetup.get().getString("player-shop-teleportation.direct-shop-tp-mode.tp-delay-in-seconds");
    public final String TP_DELAY_MESSAGE = ConfigSetup.get().getString("player-shop-teleportation.direct-shop-tp-mode.tp-delay-message");
    public final String TP_TO_OWN_SHOP_NO_PERMISSION_MESSAGE = ConfigSetup.get().getString("player-shop-teleportation.direct-shop-tp-mode.tp-to-own-shop-no-permission-message");
    public final boolean TP_PLAYER_TO_NEAREST_WARP = ConfigSetup.get().getBoolean("player-shop-teleportation.nearest-warp-tp-mode.tp-player-to-nearest-warp");
    public final String SHOP_GUI_BACK_BUTTON_MATERIAL = ConfigSetup.get().getString("shop-gui-back-button-material");
    public final String SHOP_GUI_BACK_BUTTON_TEXT = ConfigSetup.get().getString("shop-gui-back-button-text");
    public final String SHOP_GUI_NEXT_BUTTON_MATERIAL = ConfigSetup.get().getString("shop-gui-next-button-material");
    public final String SHOP_GUI_NEXT_BUTTON_TEXT = ConfigSetup.get().getString("shop-gui-next-button-text");
    public final String SHOP_GUI_FILLER_ITEM = ConfigSetup.get().getString("shop-gui-filler-item");
    public final String SHOP_GUI_BACK_BUTTON_CMD = ConfigSetup.get().getString("shop-gui-back-button-custom-model-data");
    public final String SHOP_GUI_NEXT_BUTTON_CMD = ConfigSetup.get().getString("shop-gui-next-button-custom-model-data");
    public final String SHOP_NAV_FIRST_PAGE_ALERT_MSG = ConfigSetup.get().getString("shop-navigation-first-page-alert-message");
    public final String SHOP_NAV_LAST_PAGE_ALERT_MSG = ConfigSetup.get().getString("shop-navigation-last-page-alert-message");
    public final int SHOP_PLAYER_VISIT_COOLDOWN_IN_MINUTES = ConfigSetup.get().getInt("shop-player-visit-cooldown-in-minutes");
    public final boolean IGNORE_EMPTY_CHESTS = ConfigSetup.get().getBoolean("ignore-empty-chests");
    public final String SHOP_GUI_GOTO_FIRST_PAGE_BUTTON_MATERIAL = ConfigSetup.get().getString("shop-gui-goto-first-page-button-material");
    public final String SHOP_GUI_GOTO_FIRST_PAGE_BUTTON_TEXT = ConfigSetup.get().getString("shop-gui-goto-first-page-button-text");
    public final String SHOP_GUI_GOTO_LAST_PAGE_BUTTON_MATERIAL = ConfigSetup.get().getString("shop-gui-goto-last-page-button-material");
    public final String SHOP_GUI_GOTO_LAST_PAGE_BUTTON_TEXT = ConfigSetup.get().getString("shop-gui-goto-last-page-button-text");
    public final String SHOP_GUI_GOTO_FIRST_PAGE_BUTTON_CMD = ConfigSetup.get().getString("shop-gui-goto-first-page-button-custom-model-data");
    public final String SHOP_GUI_GOTO_LAST_PAGE_BUTTON_CMD = ConfigSetup.get().getString("shop-gui-goto-last-page-button-custom-model-data");
    public final List<String> BLACKLISTED_WORLDS = (List<String>) ConfigSetup.get().getList("blacklisted-worlds");
    public final boolean DEBUG_MODE = ConfigSetup.get().getBoolean("debug-mode");
    public final int CONFIG_VERSION = ConfigSetup.get().getInt("config-version");

    private final List<World> blacklistedWorldsList = new ArrayList<>();

    public ConfigProvider() {
        if(BLACKLISTED_WORLDS != null) {
            BLACKLISTED_WORLDS.forEach(world -> {
                World worldObj = Bukkit.getWorld(world);
                if(worldObj != null) {
                    blacklistedWorldsList.add(worldObj);
                }
            });
        }
        LoggerUtils.logInfo("Config loaded!");
    }

    public List<World> getBlacklistedWorlds() {
        return blacklistedWorldsList;
    }

    public boolean shopGUIItemLoreHasKey(String key) {
        if (SHOP_GUI_ITEM_LORE != null) {
            return SHOP_GUI_ITEM_LORE.stream().anyMatch(key::contains);
        }
        else {
            return false;
        }
    }
}
