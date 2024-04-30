package io.myzticbean.finditemaddon.Handlers.GUIHandler.Menus;

import io.myzticbean.finditemaddon.FindItemAddOn;
import io.myzticbean.finditemaddon.Handlers.GUIHandler.PaginatedMenu;
import io.myzticbean.finditemaddon.Handlers.GUIHandler.PlayerMenuUtility;
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


    public KeyShopsMenu(PlayerMenuUtility playerMenuUtility, List<FoundShopItemModel> searchResult) {
        super(playerMenuUtility, searchResult);
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

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        // Previous Page
        if (event.getSlot() == 45) {
            handleMenuClickForNavToPrevPage(event);
        }
        // First Page
        else if (event.getSlot() == 46) {
            handleFirstPageClick(event);
        }
        // Last Page
        else if (event.getSlot() == 52) {
            handleLastPageClick(event);
        }
        // Next Page
        else if (event.getSlot() == 53) {
            handleMenuClickForNavToNextPage(event);
        }
        // Issue #31: Removing condition 'event.getCurrentItem().getType().equals(Material.BARRIER)'
        else if (event.getSlot() == 49) {
            ShopMenu shopMenu = new ShopMenu(FindItemAddOn.getPlayerMenuUtility((Player) event.getWhoClicked()));
            shopMenu.open();
        } else if (event.getCurrentItem().getType().equals(Material.AIR)) {
            // do nothing
            LoggerUtils.logDebugInfo(event.getWhoClicked().getName() + " just clicked on AIR!");
        } else {
            Player player = (Player) event.getWhoClicked();
            ItemStack item = event.getCurrentItem();
            ItemMeta meta = item.getItemMeta();
            NamespacedKey key = new NamespacedKey(FindItemAddOn.getInstance(), "locationData");
            if (!meta.getPersistentDataContainer().isEmpty() && meta.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
                String locData = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
                List<String> locDataList = Arrays.asList(locData.split("\\s*,\\s*"));
                if (FindItemAddOn.getConfigProvider().TP_PLAYER_DIRECTLY_TO_SHOP) {
                    if (playerMenuUtility.getOwner().hasPermission(PlayerPerms.FINDITEM_SHOPTP.value())) {
                        World world = Bukkit.getWorld(locDataList.get(0));
                        int locX = Integer.parseInt(locDataList.get(1));
                        int locY = Integer.parseInt(locDataList.get(2));
                        int locZ = Integer.parseInt(locDataList.get(3));
                        Location shopLocation = new Location(world, locX, locY, locZ);
                        UUID shopOwner = ShopSearchActivityStorageUtil.getShopOwnerUUID(shopLocation);
                        if (player.getUniqueId().equals(shopOwner) && !PlayerPerms.canPlayerTpToOwnShop(player)) {
                            player.sendMessage(Utils.chat(
                                    FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + FindItemAddOn.getConfigProvider().SHOP_TP_NO_PERMISSION_MSG));
                        }
                        Location locToTeleport = LocationUtils.findSafeLocationAroundShop(shopLocation);
                        if (locToTeleport != null) {
                            // Add Player Visit Entry
                            ShopSearchActivityStorageUtil.addPlayerVisitEntryAsync(shopLocation, player);

                            // Add Short Blindness effect... maybe?
                            // TODO: 16/06/22 Make this an option in config -> player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 0, false, false, false));

                            // Check for TP delay
                            if (StringUtils.isNumeric(FindItemAddOn.getConfigProvider().TP_DELAY_IN_SECONDS)
                                    && !"0".equals(FindItemAddOn.getConfigProvider().TP_DELAY_IN_SECONDS)
                                    && !PlayerPerms.hasShopTpDelayBypassPermOrAdmin(player)) {
                                long delay = Long.parseLong(FindItemAddOn.getConfigProvider().TP_DELAY_IN_SECONDS);
                                LoggerUtils.logDebugInfo("Teleporting delay is set to: " + delay);
                                String tpDelayMsg = FindItemAddOn.getConfigProvider().TP_DELAY_MESSAGE;
                                if (!StringUtils.isEmpty(tpDelayMsg)) {
                                    player.sendMessage(
                                            Utils.chat(
                                                    FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + replaceDelayPlaceholder(tpDelayMsg, delay)));
                                }
                                Bukkit.getScheduler().scheduleSyncDelayedTask(
                                        FindItemAddOn.getInstance(),
                                        () -> PaperLib.teleportAsync(player, locToTeleport, PlayerTeleportEvent.TeleportCause.PLUGIN),
                                        delay * 20);
                            } else {
                                PaperLib.teleportAsync(player, locToTeleport, PlayerTeleportEvent.TeleportCause.PLUGIN);
                            }
                        } else {
                            if (!StringUtils.isEmpty(FindItemAddOn.getConfigProvider().UNSAFE_SHOP_AREA_MSG)) {
                                player.sendMessage(Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX
                                        + FindItemAddOn.getConfigProvider().UNSAFE_SHOP_AREA_MSG));
                            }
                        }
                    } else {
                        if (!StringUtils.isEmpty(FindItemAddOn.getConfigProvider().SHOP_TP_NO_PERMISSION_MSG)) {
                            playerMenuUtility.getOwner()
                                    .sendMessage(Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX
                                            + FindItemAddOn.getConfigProvider().SHOP_TP_NO_PERMISSION_MSG));
                            event.getWhoClicked().closeInventory();
                        }
                    }
                    player.closeInventory();
                } else if (FindItemAddOn.getConfigProvider().TP_PLAYER_TO_NEAREST_WARP
                        // if list size = 1, it contains Warp name
                        && locDataList.size() == 1) {
                }
            } else {
                LoggerUtils.logError("PersistentDataContainer doesn't have the right kind of data!");
                return;
            }
        }
    }

    /**
     * Empty method in case we need to handle static GUI icons in future
     */
    @Override
    public void setMenuItems() {
        // Just overriding
    }

    /**
     * Sets the slots in the search result GUI
     *
     * @param foundShops List of found shops
     */
    @Override
    public void setMenuItems(List<FoundShopItemModel> foundShops) {
        addMenuBottomBar();
        if (foundShops != null && !foundShops.isEmpty()) {
            int guiSlotCounter = 0;
            while (guiSlotCounter < super.maxItemsPerPage) {
                index = super.maxItemsPerPage * page + guiSlotCounter;

                if (index >= foundShops.size()) break;

                if (foundShops.get(index) != null) {
                    // Place Search Results here
                    FoundShopItemModel foundShop_i = foundShops.get(index);
                    NamespacedKey key = new NamespacedKey(FindItemAddOn.getInstance(), "locationData");
                    ItemStack item = new ItemStack(foundShop_i.getItem().getType());
                    ItemMeta meta = item.getItemMeta();
                    List<String> lore;
                    lore = new ArrayList<>();
                    com.olziedev.playerwarps.api.warp.Warp nearestPlayerWarp = null;
                    String nearestEWarp = null;

                    if (foundShop_i.getItem().hasItemMeta()) {
                        meta = foundShop_i.getItem().getItemMeta();
                        if (foundShop_i.getItem().getItemMeta().hasLore()) {
                            for (String s : foundShop_i.getItem().getItemMeta().getLore()) {
                                lore.add(Utils.chat(s));
                            }
                        }
                    }
                    List<String> shopItemLore = FindItemAddOn.getConfigProvider().SHOP_GUI_ITEM_LORE;
                    for (String shopItemLore_i : shopItemLore) {

                        lore.add(Utils.chat(replaceLorePlaceholders(shopItemLore_i, foundShop_i)));

                    }

                    if (FindItemAddOn.getConfigProvider().TP_PLAYER_DIRECTLY_TO_SHOP) {
                        if (playerMenuUtility.getOwner().hasPermission(PlayerPerms.FINDITEM_SHOPTP.value())) {
                            lore.add(Utils.chat(FindItemAddOn.getConfigProvider().CLICK_TO_TELEPORT_MSG));
                        }
                    }
                    assert meta != null;
                    meta.setLore(lore);

                    // storing location data in item persistent storage
                    String locData = StringUtils.EMPTY;
                    // store the coordinates
                    if (FindItemAddOn.getConfigProvider().TP_PLAYER_DIRECTLY_TO_SHOP) {
                        locData = Objects.requireNonNull(foundShop_i.getShopLocation().getWorld()).getName() + ","
                                + foundShop_i.getShopLocation().getBlockX() + ","
                                + foundShop_i.getShopLocation().getBlockY() + ","
                                + foundShop_i.getShopLocation().getBlockZ();
                    } else if (FindItemAddOn.getConfigProvider().TP_PLAYER_TO_NEAREST_WARP) {

                    }
                    meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, locData);

                    // handling custom model data
                    if (Objects.requireNonNull(foundShop_i.getItem().getItemMeta()).hasCustomModelData()) {
                        meta.setCustomModelData(foundShop_i.getItem().getItemMeta().getCustomModelData());
                    }
                    item.setItemMeta(meta);
                    inventory.addItem(item);
                }
                guiSlotCounter++;
            }
        }
    }
}
