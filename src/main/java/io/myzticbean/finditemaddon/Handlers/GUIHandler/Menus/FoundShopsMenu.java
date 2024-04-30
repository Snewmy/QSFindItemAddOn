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
public class FoundShopsMenu extends PaginatedMenu {

    ShopCategory shopCategory;
    Material material;
    public static final String SHOP_STOCK_UNLIMITED = "Unlimited";
    public static final String SHOP_STOCK_UNKNOWN = "Unknown";

    public FoundShopsMenu(PlayerMenuUtility playerMenuUtility, List<FoundShopItemModel> searchResult, ShopCategory shopCategory, Material material) {
        super(playerMenuUtility, searchResult);
        this.shopCategory = shopCategory;
        this.material = material;
    }

    public FoundShopsMenu(PlayerMenuUtility playerMenuUtility, List<FoundShopItemModel> searchResult) {
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

        FormatItem formatItem = new FormatItem(material);
        return Utils.chat("&l» &r" + formatItem.getName() + " Shops");
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
            returnToCategory(shopCategory, (Player) event.getWhoClicked());
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

    public void handleMenuClickForNavToNextPage(InventoryClickEvent event) {
        if (!((index + 1) >= super.playerMenuUtility.getPlayerShopSearchResult().size())) {
            page = page + 1;
            super.open(super.playerMenuUtility.getPlayerShopSearchResult());
        } else {
            if (!StringUtils.isEmpty(FindItemAddOn.getConfigProvider().SHOP_NAV_LAST_PAGE_ALERT_MSG)) {
                event.getWhoClicked().sendMessage(
                        Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + FindItemAddOn.getConfigProvider().SHOP_NAV_LAST_PAGE_ALERT_MSG));
            }
        }
    }

    public void handleMenuClickForNavToPrevPage(InventoryClickEvent event) {
        if (page == 0) {
            if (!StringUtils.isEmpty(FindItemAddOn.getConfigProvider().SHOP_NAV_FIRST_PAGE_ALERT_MSG)) {
                event.getWhoClicked().sendMessage(
                        Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX
                                + FindItemAddOn.getConfigProvider().SHOP_NAV_FIRST_PAGE_ALERT_MSG));
            }
        } else {
            page = page - 1;
            super.open(super.playerMenuUtility.getPlayerShopSearchResult());
        }
    }

    public void handleFirstPageClick(InventoryClickEvent event) {
        if (page == 0) {
            if (!StringUtils.isEmpty(FindItemAddOn.getConfigProvider().SHOP_NAV_FIRST_PAGE_ALERT_MSG)) {
                event.getWhoClicked().sendMessage(
                        Utils.chat(
                                FindItemAddOn.getConfigProvider().PLUGIN_PREFIX
                                        + FindItemAddOn.getConfigProvider().SHOP_NAV_FIRST_PAGE_ALERT_MSG));
            }
        } else {
            page = 0;
            super.open(super.playerMenuUtility.getPlayerShopSearchResult());
        }
    }

    public void handleLastPageClick(InventoryClickEvent event) {
        int listSize = super.playerMenuUtility.getPlayerShopSearchResult().size();
        if (!((index + 1) >= listSize)) {
            double totalPages = listSize / maxItemsPerPage;
            if (totalPages % 10 == 0) {
                page = (int) Math.floor(totalPages);
                LoggerUtils.logDebugInfo("Floor page value: " + page);
            } else {
                page = (int) Math.ceil(totalPages);
                LoggerUtils.logDebugInfo("Ceiling page value: " + page);
            }
            super.open(super.playerMenuUtility.getPlayerShopSearchResult());
        } else {
            if (!StringUtils.isEmpty(FindItemAddOn.getConfigProvider().SHOP_NAV_LAST_PAGE_ALERT_MSG)) {
                event.getWhoClicked().sendMessage(
                        Utils.chat(
                                FindItemAddOn.getConfigProvider().PLUGIN_PREFIX
                                        + FindItemAddOn.getConfigProvider().SHOP_NAV_LAST_PAGE_ALERT_MSG));
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

    /**
     * Replaces all the placeholders in the Shop item lore in GUI
     * @param text Line of lore
     * @param shop Shop instance
     * @return Line of lore replaced with placeholder values
     */
    public String replaceLorePlaceholders(String text, FoundShopItemModel shop) {

        if(text.contains(ShopLorePlaceholders.ITEM_PRICE.value())) {
            text = text.replace(ShopLorePlaceholders.ITEM_PRICE.value(), String.valueOf(shop.getShopPrice()));
        }
        if(text.contains(ShopLorePlaceholders.SHOP_STOCK.value())) {
            if(shop.getRemainingStockOrSpace() == -2) {
                // if -2 (cache doesn't have value) -> try to fetch from MAIN thread
                int stockOrSpace = processUnknownStockSpace(shop);
                if(stockOrSpace == -2) {
                    text = text.replace(ShopLorePlaceholders.SHOP_STOCK.value(), SHOP_STOCK_UNKNOWN);
                }
                else {
                    text = text.replace(ShopLorePlaceholders.SHOP_STOCK.value(),
                            (stockOrSpace == -1 ? SHOP_STOCK_UNLIMITED : String.valueOf(stockOrSpace)));
                }
            } else if(shop.getRemainingStockOrSpace() == Integer.MAX_VALUE) {
                text = text.replace(ShopLorePlaceholders.SHOP_STOCK.value(), SHOP_STOCK_UNLIMITED);
            } else {
                text = text.replace(ShopLorePlaceholders.SHOP_STOCK.value(), String.valueOf(shop.getRemainingStockOrSpace()));
            }
        }
        if(text.contains(ShopLorePlaceholders.SHOP_OWNER.value())) {
            OfflinePlayer shopOwner = Bukkit.getOfflinePlayer(shop.getShopOwner());
            if(shopOwner.getName() != null) {
                text = text.replace(ShopLorePlaceholders.SHOP_OWNER.value(), shopOwner.getName());
            }
            else {
                // set a generic name for shops with no owner name
                text = text.replace(ShopLorePlaceholders.SHOP_OWNER.value(), "Admin");
            }
        }
        if(text.contains(ShopLorePlaceholders.SHOP_LOCATION.value())) {
            text = text.replace(ShopLorePlaceholders.SHOP_LOCATION.value(),
                    shop.getShopLocation().getBlockX() + ", "
                            + shop.getShopLocation().getBlockY() + ", "
                            + shop.getShopLocation().getBlockZ());
        }
        if(text.contains(ShopLorePlaceholders.SHOP_WORLD.value())) {
            text = text.replace(ShopLorePlaceholders.SHOP_WORLD.value(), Objects.requireNonNull(shop.getShopLocation().getWorld()).getName());
        }
        // Added in v2.0
        if(text.contains(ShopLorePlaceholders.SHOP_VISITS.value())) {
            text = text.replace(ShopLorePlaceholders.SHOP_VISITS.value(), String.valueOf(ShopSearchActivityStorageUtil.getPlayerVisitCount(shop.getShopLocation())));
        }
        return text;
    }

    public String replaceDelayPlaceholder(String tpDelayMsg, long delay) {
        return tpDelayMsg.replace("{DELAY}", String.valueOf(delay));
    }

    private int processUnknownStockSpace(FoundShopItemModel shop) {
        return FindItemAddOn.getQsApiInstance().processUnknownStockSpace(shop.getShopLocation(), shop.isToBuy());
    }

    public void returnToCategory(ShopCategory shopCategory, Player player) {
        switch (shopCategory) {
            case POTIONS:
                PotionsMenu potionsMenu = new PotionsMenu(FindItemAddOn.getPlayerMenuUtility(player));
                potionsMenu.open();
                break;
            case MISCELLANEOUS:
                MiscMenu miscMenu = new MiscMenu(FindItemAddOn.getPlayerMenuUtility(player));
                miscMenu.open();
                break;
            case FARMING:
                FarmingMenu farmingMenu = new FarmingMenu(FindItemAddOn.getPlayerMenuUtility(player));
                farmingMenu.open();
                break;
            case MINERALS:
                MineralsMenu mineralsMenu = new MineralsMenu(FindItemAddOn.getPlayerMenuUtility(player));
                mineralsMenu.open();
                break;
            case REDSTONE_ITEMS:
                RedstoneMenu redstoneMenu = new RedstoneMenu(FindItemAddOn.getPlayerMenuUtility(player));
                redstoneMenu.open();
                break;
            case BUILDING_BLOCKS:
                BuildingMenu buildingMenu = new BuildingMenu(FindItemAddOn.getPlayerMenuUtility(player));
                buildingMenu.open();
                break;
        }
    }
}
