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

public class MiscMenu extends CategoryPaginated {

    ArrayList<ItemStack> miscItems = new ArrayList<>();

    public MiscMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        addMenuItems();
    }

    public void addMenuItems() {
        miscItems.add(new ItemStack(Material.CANDLE));
        miscItems.add(new ItemStack(Material.BEACON));
        miscItems.add(new ItemStack(Material.ARMOR_STAND));
        miscItems.add(new ItemStack(Material.BOW));
        miscItems.add(new ItemStack(Material.DIAMOND_HELMET));
        miscItems.add(new ItemStack(Material.DIAMOND_CHESTPLATE));
        miscItems.add(new ItemStack(Material.DIAMOND_LEGGINGS));
        miscItems.add(new ItemStack(Material.DIAMOND_BOOTS));
        miscItems.add(new ItemStack(Material.NETHERITE_HELMET));
        miscItems.add(new ItemStack(Material.NETHERITE_CHESTPLATE));
        miscItems.add(new ItemStack(Material.NETHERITE_LEGGINGS));
        miscItems.add(new ItemStack(Material.NETHERITE_BOOTS));
        miscItems.add(new ItemStack(Material.DIAMOND_SWORD));
        miscItems.add(new ItemStack(Material.DIAMOND_AXE));
        miscItems.add(new ItemStack(Material.DIAMOND_PICKAXE));
        miscItems.add(new ItemStack(Material.DIAMOND_SHOVEL));
        miscItems.add(new ItemStack(Material.DIAMOND_HOE));
        miscItems.add(new ItemStack(Material.NETHERITE_SWORD));
        miscItems.add(new ItemStack(Material.NETHERITE_AXE));
        miscItems.add(new ItemStack(Material.NETHERITE_PICKAXE));
        miscItems.add(new ItemStack(Material.NETHERITE_SHOVEL));
        miscItems.add(new ItemStack(Material.NETHERITE_HOE));
        miscItems.add(new ItemStack(Material.AXOLOTL_BUCKET));
        miscItems.add(new ItemStack(Material.CAKE));
        miscItems.add(new ItemStack(Material.BLACK_DYE));
        miscItems.add(new ItemStack(Material.BLUE_DYE));
        miscItems.add(new ItemStack(Material.BROWN_DYE));
        miscItems.add(new ItemStack(Material.CYAN_DYE));
        miscItems.add(new ItemStack(Material.GREEN_DYE));
        miscItems.add(new ItemStack(Material.LIGHT_BLUE_DYE));
        miscItems.add(new ItemStack(Material.LIGHT_GRAY_DYE));
        miscItems.add(new ItemStack(Material.LIME_DYE));
        miscItems.add(new ItemStack(Material.MAGENTA_DYE));
        miscItems.add(new ItemStack(Material.ORANGE_DYE));
        miscItems.add(new ItemStack(Material.PINK_DYE));
        miscItems.add(new ItemStack(Material.PURPLE_DYE));
        miscItems.add(new ItemStack(Material.RED_DYE));
        miscItems.add(new ItemStack(Material.WHITE_DYE));
        miscItems.add(new ItemStack(Material.CROSSBOW));
        miscItems.add(new ItemStack(Material.ELYTRA));
        miscItems.add(new ItemStack(Material.END_ROD));
        miscItems.add(new ItemStack(Material.ENDER_CHEST));
        miscItems.add(new ItemStack(Material.ENDER_EYE));
        miscItems.add(new ItemStack(Material.FIREWORK_ROCKET));
        miscItems.add(new ItemStack(Material.FISHING_ROD));
        miscItems.add(new ItemStack(Material.HONEY_BOTTLE));
        miscItems.add(new ItemStack(Material.NAME_TAG));
        miscItems.add(new ItemStack(Material.NAUTILUS_SHELL));
        miscItems.add(new ItemStack(Material.NETHER_STAR));
        miscItems.add(new ItemStack(Material.PAINTING));
        miscItems.add(new ItemStack(Material.SHEARS));
        miscItems.add(new ItemStack(Material.SADDLE));
        miscItems.add(new ItemStack(Material.PUFFERFISH_BUCKET));
        miscItems.add(new ItemStack(Material.TADPOLE_BUCKET));
        miscItems.add(new ItemStack(Material.TROPICAL_FISH_BUCKET));
        miscItems.add(new ItemStack(Material.COD_BUCKET));
        miscItems.add(new ItemStack(Material.SALMON_BUCKET));
        miscItems.add(new ItemStack(Material.SHULKER_SHELL));
        miscItems.add(new ItemStack(Material.SOUL_LANTERN));
        miscItems.add(new ItemStack(Material.SPECTRAL_ARROW));
        miscItems.add(new ItemStack(Material.STICK));
        miscItems.add(new ItemStack(Material.TORCH));
        miscItems.add(new ItemStack(Material.TOTEM_OF_UNDYING));
        miscItems.add(new ItemStack(Material.TRIDENT));
        miscItems.add(new ItemStack(Material.TURTLE_EGG));
        miscItems.add(new ItemStack(Material.WITHER_SKELETON_SKULL));
        miscItems.add(new ItemStack(Material.WRITABLE_BOOK));
        miscItems.add(new ItemStack(Material.WRITTEN_BOOK));
        miscItems.add(new ItemStack(Material.OCHRE_FROGLIGHT));
        miscItems.add(new ItemStack(Material.PEARLESCENT_FROGLIGHT));
        miscItems.add(new ItemStack(Material.VERDANT_FROGLIGHT));
        miscItems.add(new ItemStack(Material.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE));
        miscItems.add(new ItemStack(Material.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE));
        miscItems.add(new ItemStack(Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE));
        miscItems.add(new ItemStack(Material.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE));
        miscItems.add(new ItemStack(Material.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE));
        miscItems.add(new ItemStack(Material.COAST_ARMOR_TRIM_SMITHING_TEMPLATE));
        miscItems.add(new ItemStack(Material.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE));
        miscItems.add(new ItemStack(Material.EYE_ARMOR_TRIM_SMITHING_TEMPLATE));
        miscItems.add(new ItemStack(Material.HOST_ARMOR_TRIM_SMITHING_TEMPLATE));
        miscItems.add(new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE));
        miscItems.add(new ItemStack(Material.RIB_ARMOR_TRIM_SMITHING_TEMPLATE));
        miscItems.add(new ItemStack(Material.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE));
        miscItems.add(new ItemStack(Material.VEX_ARMOR_TRIM_SMITHING_TEMPLATE));
        miscItems.add(new ItemStack(Material.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE));
        miscItems.add(new ItemStack(Material.WARD_ARMOR_TRIM_SMITHING_TEMPLATE));
        miscItems.add(new ItemStack(Material.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE));
        miscItems.add(new ItemStack(Material.WILD_ARMOR_TRIM_SMITHING_TEMPLATE));
    }

    @Override
    public String getMenuName() {
        return Utils.chat("&8Miscellaneous");
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
                        FoundShopsMenu menu = new FoundShopsMenu(FindItemAddOn.getPlayerMenuUtility(player), searchResultList, ShopCategory.MISCELLANEOUS, mat, isBuying, SearchType.NORMAL);
                        Bukkit.getScheduler().runTask(FindItemAddOn.getInstance(), () -> menu.open(searchResultList));
                    });
                }

            } else {
                player.sendMessage(Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + "&cNo permission!"));
            }
        }
    }

    private void handleMenuClickForNavToNextPage(InventoryClickEvent event) {
        if (!((index + 1) >= miscItems.size())) {
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
        int listSize = miscItems.size();
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

                if (index >= miscItems.size()) break;

                if (miscItems.get(index) != null) {
                    // Place Search Results here
                    ItemStack itemStack = miscItems.get(index);
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

