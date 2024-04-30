package io.myzticbean.finditemaddon.Handlers.GUIHandler.Menus;

import com.ghostchu.quickshop.api.QuickShopAPI;
import com.ghostchu.quickshop.api.shop.Shop;
import com.ghostchu.quickshop.api.shop.ShopType;
import io.myzticbean.finditemaddon.FindItemAddOn;
import io.myzticbean.finditemaddon.Handlers.GUIHandler.CategoryPaginated;
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
import org.apache.commons.lang3.StringUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class MineralsMenu extends CategoryPaginated {

    ArrayList<ItemStack> itemStacks = new ArrayList<>();

    public MineralsMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        addMenuPotions();
    }

    public void addMenuPotions() {
        itemStacks.add(new ItemStack(Material.EMERALD));
        itemStacks.add(new ItemStack(Material.DIAMOND));
        itemStacks.add(new ItemStack(Material.IRON_INGOT));
        itemStacks.add(new ItemStack(Material.GOLD_INGOT));
        itemStacks.add(new ItemStack(Material.REDSTONE));
        itemStacks.add(new ItemStack(Material.COPPER_INGOT));
        itemStacks.add(new ItemStack(Material.COAL));
        itemStacks.add(new ItemStack(Material.AMETHYST_SHARD));
        itemStacks.add(new ItemStack(Material.LAPIS_LAZULI));
        itemStacks.add(new ItemStack(Material.GOLDEN_APPLE));
        itemStacks.add(new ItemStack(Material.GOLDEN_CARROT));
        itemStacks.add(new ItemStack(Material.GOLDEN_HORSE_ARMOR));
        itemStacks.add(new ItemStack(Material.IRON_HORSE_ARMOR));
        itemStacks.add(new ItemStack(Material.DIAMOND_HORSE_ARMOR));
        itemStacks.add(new ItemStack(Material.NETHERITE_INGOT));
    }

    @Override
    public String getMenuName() {
        return Utils.chat("&8Minerals & Ores");
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
                        FoundShopsMenu menu = new FoundShopsMenu(FindItemAddOn.getPlayerMenuUtility(player), searchResultList, ShopCategory.MINERALS, mat);
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

