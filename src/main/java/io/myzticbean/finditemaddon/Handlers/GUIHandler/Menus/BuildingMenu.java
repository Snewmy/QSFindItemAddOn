package io.myzticbean.finditemaddon.Handlers.GUIHandler.Menus;

import com.ghostchu.quickshop.api.QuickShopAPI;
import com.ghostchu.quickshop.api.shop.Shop;
import com.ghostchu.quickshop.api.shop.ShopType;
import io.myzticbean.finditemaddon.FindItemAddOn;
import io.myzticbean.finditemaddon.Handlers.GUIHandler.*;
import io.myzticbean.finditemaddon.Models.FoundShopItemModel;
import io.myzticbean.finditemaddon.Utils.Defaults.PlayerPerms;
import io.myzticbean.finditemaddon.Utils.Defaults.ShopLorePlaceholders;
import io.myzticbean.finditemaddon.Utils.FormatItem;
import io.myzticbean.finditemaddon.Utils.JsonStorageUtils.ShopSearchActivityStorageUtil;
import io.myzticbean.finditemaddon.Utils.LocationUtils;
import io.myzticbean.finditemaddon.Utils.LoggerUtils;
import io.myzticbean.finditemaddon.Utils.Utils;
import io.papermc.lib.PaperLib;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class BuildingMenu extends CategoryPaginated {

    ArrayList<ItemStack> itemStacks = new ArrayList<>();

    public BuildingMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        addMenuPotions();
    }

    public void addMenuPotions() {
        itemStacks.add(new ItemStack(Material.MOSS_BLOCK));
        itemStacks.add(new ItemStack(Material.MOSS_CARPET));
        itemStacks.add(new ItemStack(Material.DRIPSTONE_BLOCK));
        itemStacks.add(new ItemStack(Material.COBBLED_DEEPSLATE));
        itemStacks.add(new ItemStack(Material.CALCITE));
        itemStacks.add(new ItemStack(Material.ANDESITE));
        itemStacks.add(new ItemStack(Material.DIORITE));
        itemStacks.add(new ItemStack(Material.GRANITE));
        itemStacks.add(new ItemStack(Material.ACACIA_LOG));
        itemStacks.add(new ItemStack(Material.BIRCH_LOG));
        itemStacks.add(new ItemStack(Material.CHERRY_LOG));
        itemStacks.add(new ItemStack(Material.JUNGLE_LOG));
        itemStacks.add(new ItemStack(Material.OAK_LOG));
        itemStacks.add(new ItemStack(Material.DARK_OAK_LOG));
        itemStacks.add(new ItemStack(Material.SPRUCE_LOG));
        itemStacks.add(new ItemStack(Material.MANGROVE_LOG));
        itemStacks.add(new ItemStack(Material.CRIMSON_STEM));
        itemStacks.add(new ItemStack(Material.WARPED_STEM));
        itemStacks.add(new ItemStack(Material.SMOOTH_BASALT));
        itemStacks.add(new ItemStack(Material.BEEHIVE));
        itemStacks.add(new ItemStack(Material.BARREL));
        itemStacks.add(new ItemStack(Material.BONE_BLOCK));
        itemStacks.add(new ItemStack(Material.COBBLESTONE));
        itemStacks.add(new ItemStack(Material.GRAVEL));
        itemStacks.add(new ItemStack(Material.STONE_BRICKS));
        itemStacks.add(new ItemStack(Material.DIRT));
        itemStacks.add(new ItemStack(Material.SAND));
        itemStacks.add(new ItemStack(Material.BRICK));
        itemStacks.add(new ItemStack(Material.BRICKS));
        itemStacks.add(new ItemStack(Material.CHAIN));
        itemStacks.add(new ItemStack(Material.COARSE_DIRT));
        itemStacks.add(new ItemStack(Material.GRASS_BLOCK));
        itemStacks.add(new ItemStack(Material.MYCELIUM));
        itemStacks.add(new ItemStack(Material.MOSSY_COBBLESTONE));
        itemStacks.add(new ItemStack(Material.PACKED_ICE));
        itemStacks.add(new ItemStack(Material.SCAFFOLDING));
        itemStacks.add(new ItemStack(Material.SHULKER_BOX));
        itemStacks.add(new ItemStack(Material.NETHER_BRICKS));
        itemStacks.add(new ItemStack(Material.GLASS));
        itemStacks.add(new ItemStack(Material.GRAY_STAINED_GLASS));
        itemStacks.add(new ItemStack(Material.GREEN_STAINED_GLASS));
        itemStacks.add(new ItemStack(Material.RED_STAINED_GLASS));
        itemStacks.add(new ItemStack(Material.BROWN_STAINED_GLASS));
        itemStacks.add(new ItemStack(Material.BLACK_STAINED_GLASS));
        itemStacks.add(new ItemStack(Material.BLUE_STAINED_GLASS));
        itemStacks.add(new ItemStack(Material.CYAN_STAINED_GLASS));
        itemStacks.add(new ItemStack(Material.GREEN_STAINED_GLASS));
        itemStacks.add(new ItemStack(Material.MAGENTA_STAINED_GLASS));
        itemStacks.add(new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS));
        itemStacks.add(new ItemStack(Material.LIME_STAINED_GLASS));
        itemStacks.add(new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS));
        itemStacks.add(new ItemStack(Material.YELLOW_STAINED_GLASS));
        itemStacks.add(new ItemStack(Material.ORANGE_STAINED_GLASS));
        itemStacks.add(new ItemStack(Material.PINK_STAINED_GLASS));
        itemStacks.add(new ItemStack(Material.PURPLE_STAINED_GLASS));
        itemStacks.add(new ItemStack(Material.BLACK_CONCRETE));
        itemStacks.add(new ItemStack(Material.CYAN_CONCRETE));
        itemStacks.add(new ItemStack(Material.GRAY_CONCRETE));
        itemStacks.add(new ItemStack(Material.GREEN_CONCRETE));
        itemStacks.add(new ItemStack(Material.MAGENTA_CONCRETE));
        itemStacks.add(new ItemStack(Material.LIGHT_BLUE_CONCRETE));
        itemStacks.add(new ItemStack(Material.LIME_CONCRETE));
        itemStacks.add(new ItemStack(Material.LIGHT_GRAY_CONCRETE));
        itemStacks.add(new ItemStack(Material.YELLOW_CONCRETE));
        itemStacks.add(new ItemStack(Material.ORANGE_CONCRETE));
        itemStacks.add(new ItemStack(Material.PINK_CONCRETE));
        itemStacks.add(new ItemStack(Material.PURPLE_CONCRETE));
        itemStacks.add(new ItemStack(Material.BLUE_CONCRETE));
        itemStacks.add(new ItemStack(Material.RED_CONCRETE));
        itemStacks.add(new ItemStack(Material.BROWN_CONCRETE));
        itemStacks.add(new ItemStack(Material.TERRACOTTA));
        itemStacks.add(new ItemStack(Material.BLACK_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.CYAN_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.GRAY_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.GREEN_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.MAGENTA_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.LIGHT_BLUE_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.LIME_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.LIGHT_GRAY_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.YELLOW_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.ORANGE_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.PINK_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.PURPLE_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.BLUE_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.RED_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.BLACK_GLAZED_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.BLUE_GLAZED_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.RED_GLAZED_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.ORANGE_GLAZED_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.PINK_GLAZED_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.GRAY_GLAZED_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.GREEN_GLAZED_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.LIGHT_BLUE_GLAZED_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.LIME_GLAZED_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.LIGHT_GRAY_GLAZED_TERRACOTTA));
        itemStacks.add(new ItemStack(Material.YELLOW_GLAZED_TERRACOTTA));
    }

    @Override
    public String getMenuName() {
        return Utils.chat("&8Building Blocks");
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
            if (player.hasPermission(PlayerPerms.FINDITEM_USE.value())) {

                // Show searching... message
                if (!StringUtils.isEmpty(FindItemAddOn.getConfigProvider().SHOP_SEARCH_LOADING_MSG)) {
                    player.sendMessage(Utils.chat(
                            FindItemAddOn.getConfigProvider().PLUGIN_PREFIX
                                    + FindItemAddOn.getConfigProvider().SHOP_SEARCH_LOADING_MSG));
                }

                boolean isBuying = true;
                Material mat = item.getType();
                LoggerUtils.logDebugInfo("Material found: " + mat);
                // If QS Hikari installed and Shop Cache feature available (>6), then run in async thread (Fix for Issue #12)
                if (FindItemAddOn.getQsApiInstance().isQSShopCacheImplemented()) {
                    Bukkit.getScheduler().runTaskAsynchronously(FindItemAddOn.getInstance(), () -> {
                        List<FoundShopItemModel> searchResultList = FindItemAddOn.getQsApiInstance().findItemBasedOnTypeFromAllShops(new ItemStack(mat), isBuying, player);
                        /*
                        if (!searchResultList.isEmpty()) {
                            FoundShopsMenu menu = new FoundShopsMenu(FindItemAddOn.getPlayerMenuUtility(player), searchResultList);
                            Bukkit.getScheduler().runTask(FindItemAddOn.getInstance(), () -> menu.open(searchResultList));
                        } else {
                            if (!StringUtils.isEmpty(FindItemAddOn.getConfigProvider().NO_SHOP_FOUND_MSG)) {
                                player.sendMessage(Utils.chat(
                                        FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + FindItemAddOn.getConfigProvider().NO_SHOP_FOUND_MSG));
                            }
                        }
                         */
                        FoundShopsMenu menu = new FoundShopsMenu(FindItemAddOn.getPlayerMenuUtility(player), searchResultList, ShopCategory.BUILDING_BLOCKS, mat, isBuying, SearchType.NORMAL);
                        Bukkit.getScheduler().runTask(FindItemAddOn.getInstance(), () -> menu.open(searchResultList));
                    });
                }

            } else {
                player.sendMessage(Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + "&cNo permission!"));
            }
        }
    }

    private void handleMenuClickForNavToNextPage(InventoryClickEvent event) {
        if (!((index + 1) >= itemStacks.size())) {
            page = page + 1;
            super.open();
        } else {
            if (!StringUtils.isEmpty(FindItemAddOn.getConfigProvider().SHOP_NAV_LAST_PAGE_ALERT_MSG)) {
                event.getWhoClicked().sendMessage(
                        Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + FindItemAddOn.getConfigProvider().SHOP_NAV_LAST_PAGE_ALERT_MSG));
            }
        }
    }

    private void handleMenuClickForNavToPrevPage(InventoryClickEvent event) {
        if (page == 0) {
            if (!StringUtils.isEmpty(FindItemAddOn.getConfigProvider().SHOP_NAV_FIRST_PAGE_ALERT_MSG)) {
                event.getWhoClicked().sendMessage(
                        Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX
                                + FindItemAddOn.getConfigProvider().SHOP_NAV_FIRST_PAGE_ALERT_MSG));
            }
        } else {
            page = page - 1;
            super.open();
        }
    }

    private void handleFirstPageClick(InventoryClickEvent event) {
        if (page == 0) {
            if (!StringUtils.isEmpty(FindItemAddOn.getConfigProvider().SHOP_NAV_FIRST_PAGE_ALERT_MSG)) {
                event.getWhoClicked().sendMessage(
                        Utils.chat(
                                FindItemAddOn.getConfigProvider().PLUGIN_PREFIX
                                        + FindItemAddOn.getConfigProvider().SHOP_NAV_FIRST_PAGE_ALERT_MSG));
            }
        } else {
            page = 0;
            super.open();
        }
    }

    private void handleLastPageClick(InventoryClickEvent event) {
        int listSize = itemStacks.size();
        if (!((index + 1) >= listSize)) {
            double totalPages = listSize / maxItemsPerPage;
            if (totalPages % 10 == 0) {
                page = (int) Math.floor(totalPages);
                LoggerUtils.logDebugInfo("Floor page value: " + page);
            } else {
                page = (int) Math.ceil(totalPages);
                LoggerUtils.logDebugInfo("Ceiling page value: " + page);
            }
            super.open();
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
        addMenuBottomBar();
        Bukkit.getScheduler().runTaskAsynchronously(FindItemAddOn.getInstance(), () -> {
            int guiSlotCounter = 0;
            while (guiSlotCounter < super.maxItemsPerPage) {
                index = super.maxItemsPerPage * page + guiSlotCounter;

                if (index >= itemStacks.size()) break;

                if (itemStacks.get(index) != null) {
                    // Place Search Results here
                    ItemStack itemStack = itemStacks.get(index);
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    FormatItem formatItem = new FormatItem(itemStack.getType());
                    itemMeta.setDisplayName(Utils.chat("&6" + formatItem.getName()));
                    List<String> lore = new ArrayList<>();
                    lore.add(Utils.chat("&fPlayer Shops: &7" + getTotalShopsForItemType(itemStack.getType())));
                    itemMeta.setLore(lore);
                    itemStack.setItemMeta(itemMeta);
                    inventory.addItem(itemStack);
                }
                guiSlotCounter++;
            }
        });
    }

    /**
     * Sets the slots in the search result GUI
     *
     * @param foundShops List of found shops
     */
    @Override
    public void setMenuItems(List<FoundShopItemModel> foundShops) {
        //do nothing
    }

    /**
     * Replaces all the placeholders in the Shop item lore in GUI
     *
     * @param text Line of lore
     * @param shop Shop instance
     * @return Line of lore replaced with placeholder values
     */
    private String replaceLorePlaceholders(String text, FoundShopItemModel shop) {

        if (text.contains(ShopLorePlaceholders.ITEM_PRICE.value())) {
            text = text.replace(ShopLorePlaceholders.ITEM_PRICE.value(), String.valueOf(shop.getShopPrice()));
        }
        if (text.contains(ShopLorePlaceholders.SHOP_STOCK.value())) {
            if (shop.getRemainingStockOrSpace() == Integer.MAX_VALUE) {
                text = text.replace(ShopLorePlaceholders.SHOP_STOCK.value(), "Unlimited");
            } else if (shop.getRemainingStockOrSpace() == -2) {
                text = text.replace(ShopLorePlaceholders.SHOP_STOCK.value(), "Unknown");
            } else {
                text = text.replace(ShopLorePlaceholders.SHOP_STOCK.value(), String.valueOf(shop.getRemainingStockOrSpace()));
            }
        }
        if (text.contains(ShopLorePlaceholders.SHOP_OWNER.value())) {
            OfflinePlayer shopOwner = Bukkit.getOfflinePlayer(shop.getShopOwner());
            if (shopOwner.getName() != null) {
                text = text.replace(ShopLorePlaceholders.SHOP_OWNER.value(), shopOwner.getName());
            } else {
                // set a generic name for shops with no owner name
                text = text.replace(ShopLorePlaceholders.SHOP_OWNER.value(), "Admin");
            }
        }
        if (text.contains(ShopLorePlaceholders.SHOP_LOCATION.value())) {
            text = text.replace(ShopLorePlaceholders.SHOP_LOCATION.value(),
                    shop.getShopLocation().getBlockX() + ", "
                            + shop.getShopLocation().getBlockY() + ", "
                            + shop.getShopLocation().getBlockZ());
        }
        if (text.contains(ShopLorePlaceholders.SHOP_WORLD.value())) {
            text = text.replace(ShopLorePlaceholders.SHOP_WORLD.value(), Objects.requireNonNull(shop.getShopLocation().getWorld()).getName());
        }
        // Added in v2.0
        if (text.contains(ShopLorePlaceholders.SHOP_VISITS.value())) {
            text = text.replace(ShopLorePlaceholders.SHOP_VISITS.value(), String.valueOf(ShopSearchActivityStorageUtil.getPlayerVisitCount(shop.getShopLocation())));
        }
        return text;
    }

    private String replaceDelayPlaceholder(String tpDelayMsg, long delay) {
        return tpDelayMsg.replace("{DELAY}", String.valueOf(delay));
    }
}

