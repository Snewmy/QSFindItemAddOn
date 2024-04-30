package io.myzticbean.finditemaddon.Handlers.GUIHandler.Menus;

import io.myzticbean.finditemaddon.FindItemAddOn;
import io.myzticbean.finditemaddon.Handlers.GUIHandler.Menu;
import io.myzticbean.finditemaddon.Handlers.GUIHandler.PlayerMenuUtility;
import io.myzticbean.finditemaddon.Handlers.GUIHandler.ShopCategory;
import io.myzticbean.finditemaddon.Models.FoundShopItemModel;
import io.myzticbean.finditemaddon.Utils.Defaults.PlayerPerms;
import io.myzticbean.finditemaddon.Utils.ItemBuilder;
import io.myzticbean.finditemaddon.Utils.LoggerUtils;
import io.myzticbean.finditemaddon.Utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ShopMenu extends Menu {
    public ShopMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return Utils.chat("&8Player-Shop Directory");
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        if (event.getSlot() == 10) {
            BuildingMenu buildingMenu = new BuildingMenu(FindItemAddOn.getPlayerMenuUtility((Player) event.getWhoClicked()));
            buildingMenu.open();
        } else if (event.getSlot() == 11) {
            MineralsMenu mineralsMenu = new MineralsMenu(FindItemAddOn.getPlayerMenuUtility((Player) event.getWhoClicked()));
            mineralsMenu.open();
        } else if (event.getSlot() == 12) {
            FarmingMenu farmingMenu = new FarmingMenu(FindItemAddOn.getPlayerMenuUtility((Player) event.getWhoClicked()));
            farmingMenu.open();
        } else if (event.getSlot() == 13) {
            if (!FindItemAddOn.isExcellentCratesInstalled) {
                return;
            }
            openCrateKeySearch(playerMenuUtility.getOwner());
        } else if (event.getSlot() == 14) {
            RedstoneMenu redstoneMenu = new RedstoneMenu(FindItemAddOn.getPlayerMenuUtility((Player) event.getWhoClicked()));
            redstoneMenu.open();
        } else if (event.getSlot() == 15) {
            PotionsMenu potionsMenu = new PotionsMenu(FindItemAddOn.getPlayerMenuUtility((Player) event.getWhoClicked()));
            potionsMenu.open();
        } else if (event.getSlot() == 16) {
            MiscMenu miscMenu = new MiscMenu(FindItemAddOn.getPlayerMenuUtility((Player) event.getWhoClicked()));
            miscMenu.open();
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack buildingBlocks = new ItemBuilder(Material.BRICKS, 1)
                .setDisplayName(Utils.chat("&6Building Blocks"))
                .toItemStack();
        ItemStack mineralsOres = new ItemBuilder(Material.DIAMOND, 1)
                .setDisplayName(Utils.chat("&bMinerals & Ores"))
                .toItemStack();
        ItemStack farmingMobDrops = new ItemBuilder(Material.WHEAT, 1)
                .setDisplayName(Utils.chat("&eFarming & Mob Drops"))
                .toItemStack();
        ItemStack redstoneItems = new ItemBuilder(Material.REDSTONE, 1)
                .setDisplayName(Utils.chat("&cRedstone Items"))
                .toItemStack();
        ItemStack potionsEnchanting = new ItemBuilder(Material.DRAGON_BREATH, 1)
                .setDisplayName(Utils.chat("&dPotions & Enchanting"))
                .toItemStack();
        ItemStack miscellaneous = new ItemBuilder(Material.ENDER_EYE, 1)
                .setDisplayName(Utils.chat("&aMiscellaneous"))
                .toItemStack();
        ItemStack crateKeys = new ItemBuilder(Material.TRIPWIRE_HOOK, 1)
                .setDisplayName(Utils.chat("&bCrate Keys"))
                        .toItemStack();
        inventory.setItem(10, buildingBlocks);
        inventory.setItem(11, mineralsOres);
        inventory.setItem(12, farmingMobDrops);
        inventory.setItem(13, crateKeys);
        inventory.setItem(14, redstoneItems);
        inventory.setItem(15, potionsEnchanting);
        inventory.setItem(16, miscellaneous);

        //filler
        inventory.setItem(0, super.GUI_FILLER_ITEM);
        inventory.setItem(1, super.GUI_FILLER_ITEM);
        inventory.setItem(2, super.GUI_FILLER_ITEM);
        inventory.setItem(3, super.GUI_FILLER_ITEM);
        inventory.setItem(4, super.GUI_FILLER_ITEM);
        inventory.setItem(5, super.GUI_FILLER_ITEM);
        inventory.setItem(6, super.GUI_FILLER_ITEM);
        inventory.setItem(7, super.GUI_FILLER_ITEM);
        inventory.setItem(8, super.GUI_FILLER_ITEM);
        inventory.setItem(9, super.GUI_FILLER_ITEM);
        inventory.setItem(17, super.GUI_FILLER_ITEM);
        inventory.setItem(18, super.GUI_FILLER_ITEM);
        inventory.setItem(19, super.GUI_FILLER_ITEM);
        inventory.setItem(20, super.GUI_FILLER_ITEM);
        inventory.setItem(21, super.GUI_FILLER_ITEM);
        inventory.setItem(22, super.GUI_FILLER_ITEM);
        inventory.setItem(23, super.GUI_FILLER_ITEM);
        inventory.setItem(24, super.GUI_FILLER_ITEM);
        inventory.setItem(25, super.GUI_FILLER_ITEM);
        inventory.setItem(26, super.GUI_FILLER_ITEM);
    }

    @Override
    public void setMenuItems(List<FoundShopItemModel> foundShops) {
        // useless
    }

    public void openCrateKeySearch(Player player) {
        if (player.hasPermission(PlayerPerms.FINDITEM_USE.value())) {

            // Show searching... message
            if (!StringUtils.isEmpty(FindItemAddOn.getConfigProvider().SHOP_SEARCH_LOADING_MSG)) {
                player.sendMessage(Utils.chat(
                        FindItemAddOn.getConfigProvider().PLUGIN_PREFIX
                                + FindItemAddOn.getConfigProvider().SHOP_SEARCH_LOADING_MSG));
            }

            boolean isBuying = true;
            // If QS Hikari installed and Shop Cache feature available (>6), then run in async thread (Fix for Issue #12)
            if (FindItemAddOn.getQsApiInstance().isQSShopCacheImplemented()) {
                Bukkit.getScheduler().runTaskAsynchronously(FindItemAddOn.getInstance(), () -> {
                    //List<FoundShopItemModel> searchResultList = FindItemAddOn.getQsApiInstance().findItemBasedOnTypeFromAllShops(new ItemStack(mat), isBuying, player);
                    List<FoundShopItemModel> searchResultList = FindItemAddOn.getQsApiInstance().findItemCrateKeysFromAllShops(isBuying, player);
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
                    KeyShopsMenu menu = new KeyShopsMenu(FindItemAddOn.getPlayerMenuUtility(player), searchResultList);
                    Bukkit.getScheduler().runTask(FindItemAddOn.getInstance(), () -> menu.open(searchResultList));
                });
            }

        } else {
            player.sendMessage(Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + "&cNo permission!"));
        }
    }
}
