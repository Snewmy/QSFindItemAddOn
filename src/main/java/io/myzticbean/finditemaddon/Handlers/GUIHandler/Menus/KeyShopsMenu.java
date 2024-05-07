package io.myzticbean.finditemaddon.Handlers.GUIHandler.Menus;

import io.myzticbean.finditemaddon.FindItemAddOn;
import io.myzticbean.finditemaddon.Handlers.GUIHandler.PaginatedMenu;
import io.myzticbean.finditemaddon.Handlers.GUIHandler.PlayerMenuUtility;
import io.myzticbean.finditemaddon.Handlers.GUIHandler.SearchType;
import io.myzticbean.finditemaddon.Handlers.GUIHandler.ShopCategory;
import io.myzticbean.finditemaddon.Models.FoundShopItemModel;
import io.myzticbean.finditemaddon.Utils.Defaults.PlayerPerms;
import io.myzticbean.finditemaddon.Utils.Defaults.ShopLorePlaceholders;
import io.myzticbean.finditemaddon.Utils.FormatItem;
import io.myzticbean.finditemaddon.Utils.JsonStorageUtils.ShopSearchActivityStorageUtil;
import io.myzticbean.finditemaddon.Utils.LocationUtils;
import io.myzticbean.finditemaddon.Utils.LoggerUtils;
import io.myzticbean.finditemaddon.Utils.Utils;
import io.papermc.lib.PaperLib;
import me.kodysimpson.simpapi.colors.ColorTranslator;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

/**
 * Handler class for FoundShops GUI
 *
 * @author myzticbean
 */
public class KeyShopsMenu extends FoundShopsMenu {


    public KeyShopsMenu(PlayerMenuUtility playerMenuUtility, List<FoundShopItemModel> searchResult, ShopCategory shopCategory, boolean isBuying, SearchType searchType) {
        super(playerMenuUtility, searchResult, shopCategory, isBuying, searchType);
    }

    @Override
    public String getMenuName() {
        /*
        if (!StringUtils.isEmpty(FindItemAddOn.getConfigProvider().SHOP_SEARCH_GUI_TITLE)) {
            return Utils.chat(FindItemAddOn.getConfigProvider().SHOP_SEARCH_GUI_TITLE);
        } else {
            return Utils.chat("&l» &rShops");
        }
         */

        return Utils.chat("&l» &rCrate Key Shops");
    }
}
