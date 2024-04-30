package io.myzticbean.finditemaddon.Handlers.CommandHandler;

import io.myzticbean.finditemaddon.ConfigUtil.ConfigSetup;
import io.myzticbean.finditemaddon.FindItemAddOn;
import io.myzticbean.finditemaddon.Handlers.GUIHandler.Menus.FoundShopsMenu;
import io.myzticbean.finditemaddon.Models.FoundShopItemModel;
import io.myzticbean.finditemaddon.Utils.Defaults.PlayerPerms;
import io.myzticbean.finditemaddon.Utils.JsonStorageUtils.HiddenShopStorageUtil;
import io.myzticbean.finditemaddon.Utils.LoggerUtils;
import io.myzticbean.finditemaddon.Utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

/**
 * Handler for different parameters of /finditem command
 *
 * @author myzticbean
 */
public class CmdExecutorHandler {

    /**
     * Handles the main shop search process
     *
     * @param buySellSubCommand Whether player is buying or selling
     * @param commandSender     Who is the command sender: console or player
     * @param itemArg           Specifies Item ID or Item name
     */
    public void handleShopSearch(String buySellSubCommand, CommandSender commandSender, String itemArg) {
        if (!(commandSender instanceof Player)) {
            LoggerUtils.logInfo("This command can only be run from in game");
        } else {
            Player player = (Player) commandSender;
            if (player.hasPermission(PlayerPerms.FINDITEM_USE.value())) {

                // Show searching... message
                if (!StringUtils.isEmpty(FindItemAddOn.getConfigProvider().SHOP_SEARCH_LOADING_MSG)) {
                    player.sendMessage(Utils.chat(
                            FindItemAddOn.getConfigProvider().PLUGIN_PREFIX
                                    + FindItemAddOn.getConfigProvider().SHOP_SEARCH_LOADING_MSG));
                }

                boolean isBuying;
                    Material mat = Material.getMaterial(itemArg.toUpperCase());
                    if (mat != null) {
                        LoggerUtils.logDebugInfo("Material found: " + mat);
                        // If QS Hikari installed and Shop Cache feature available (>6), then run in async thread (Fix for Issue #12)
                        if (FindItemAddOn.getQsApiInstance().isQSShopCacheImplemented()) {
                            Bukkit.getScheduler().runTaskAsynchronously(FindItemAddOn.getInstance(), () -> {
                            });
                        } else {
                            //List<FoundShopItemModel> searchResultList = FindItemAddOn.getQsApiInstance().findItemBasedOnTypeFromAllShops(new ItemStack(mat), isBuying, player);
                        }
                    } else {
                        LoggerUtils.logDebugInfo("Material not found! Performing query based search..");
                        // If QS Hikari installed and Shop Cache feature available (>6), then run in async thread (Fix for Issue #12)
                        if (FindItemAddOn.getQsApiInstance().isQSShopCacheImplemented()) {
                            Bukkit.getScheduler().runTaskAsynchronously(FindItemAddOn.getInstance(), () -> {
                            });
                        } else {

                        }
                    }

            } else {
                player.sendMessage(Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + "&cNo permission!"));
            }
        }
    }

    /**
     * Handles the shop hiding feature
     *
     * @param commandSender Who is the command sender: console or player
     */
    public void handleHideShop(CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            LoggerUtils.logInfo("This command can only be run from in game");
        } else {
            Player player = (Player) commandSender;
            if (player.hasPermission(PlayerPerms.FINDITEM_HIDESHOP.value())) {
                Block playerLookAtBlock = player.getTargetBlock(null, 3);
                LoggerUtils.logDebugInfo("TargetBlock found: " + playerLookAtBlock.getType());
               // hideShop((com.ghostchu.quickshop.api.shop.Shop) FindItemAddOn.getQsApiInstance().findShopAtLocation(playerLookAtBlock), player);
            } else {
                player.sendMessage(Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + "&cNo permission!"));
            }
        }
    }

    /**
     * Handles the shop reveal feature
     *
     * @param commandSender Who is the command sender: console or player
     */
    public void handleRevealShop(CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            LoggerUtils.logInfo("This command can only be run from in game");
        } else {
            Player player = (Player) commandSender;
            if (player.hasPermission(PlayerPerms.FINDITEM_HIDESHOP.value())) {
                Block playerLookAtBlock = player.getTargetBlock(null, 5);
                if (playerLookAtBlock != null) {
                    LoggerUtils.logDebugInfo("TargetBlock found: " + playerLookAtBlock.getType());
                    //revealShop((com.ghostchu.quickshop.api.shop.Shop) FindItemAddOn.getQsApiInstance().findShopAtLocation(playerLookAtBlock), player);
                } else {
                    LoggerUtils.logDebugInfo("TargetBlock is null!");
                }
            } else {
                player.sendMessage(Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + "&cNo permission!"));
            }
        }
    }

    /**
     * Handles the saving hidden shops to file feature
     * @param commandSender Who is the command sender: console or player
     */
    /*
    public void handleHiddenShopSavingToFile(CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            LoggerUtils.logInfo("This command can only be run from in game");
        }
        else {
            Player player = (Player) commandSender;
            if(player.hasPermission(PlayerPerms.FINDITEM_ADMIN.toString())) {
                HiddenShopStorageUtil.saveHiddenShopsToFile();
                player.sendMessage(Utils.chat(
                        FindItemAddOn.getConfigProvider().PLUGIN_PREFIX
                                + "&aSaved hidden shops!"));
            }
            else {
                player.sendMessage(
                        Utils.chat(
                                FindItemAddOn.getConfigProvider().PLUGIN_PREFIX
                                        + "&cNo permission!"));
            }
        }
    }
     */

    /**
     * Handles the loading of hidden shops from file feature
     * @param commandSender Who is the command sender: console or player
     */
    /*
    public void handleHiddenShopLoadingFromFile(CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            LoggerUtils.logInfo("This command can only be run from in game");
        }
        else {
            Player player = (Player) commandSender;
            if(player.hasPermission(PlayerPerms.FINDITEM_ADMIN.toString())) {
                HiddenShopStorageUtil.loadHiddenShopsFromFile();
                player.sendMessage(Utils.chat(
                        FindItemAddOn.getConfigProvider().PLUGIN_PREFIX
                                + "&aSaved hidden shops!"));
            }
            else {
                player.sendMessage(
                        Utils.chat(
                                FindItemAddOn.getConfigProvider().PLUGIN_PREFIX
                                        + "&cNo permission!"));
            }
        }
    }
     */

    /**
     * Handles plugin reload
     *
     * @param commandSender Who is the command sender: console or player
     */
    public void handlePluginReload(CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            ConfigSetup.reloadConfig();
            ConfigSetup.checkForMissingProperties();
            ConfigSetup.saveConfig();
            FindItemAddOn.initConfigProvider();
            List allServerShops = FindItemAddOn.getQsApiInstance().getAllShops();
            if (allServerShops.size() == 0) {
                LoggerUtils.logWarning("&6Found &e0 &6shops on the server. If you ran &e/qs reload &6recently, please restart your server!");
            } else {
                LoggerUtils.logInfo("&aFound &e" + allServerShops.size() + " &ashops on the server.");
            }
        } else {
            Player player = (Player) commandSender;
            if (player.hasPermission(PlayerPerms.FINDITEM_RELOAD.value()) || player.hasPermission(PlayerPerms.FINDITEM_ADMIN.value())) {
                ConfigSetup.reloadConfig();
                ConfigSetup.checkForMissingProperties();
                ConfigSetup.saveConfig();
                FindItemAddOn.initConfigProvider();
                player.sendMessage(Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + "&aConfig reloaded!"));
                List allServerShops = FindItemAddOn.getQsApiInstance().getAllShops();
                if (allServerShops.size() == 0) {
                    player.sendMessage(Utils.chat(
                            FindItemAddOn.getConfigProvider().PLUGIN_PREFIX
                                    + "&6Found &e0 &6shops on the server. If you ran &e/qs reload &6recently, please restart your server!"));
                } else {
                    player.sendMessage(Utils.chat(
                            FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + "&aFound &e" + allServerShops.size() + " &ashops on the server."));
                }
            } else {
                player.sendMessage(Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + "&cNo permission!"));
            }
        }
    }

    /**
     * @param commandSender Who is the command sender: console or player
     * @deprecated Handles plugin restart
     */
    @Deprecated(forRemoval = true)
    public void handlePluginRestart(CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            Bukkit.getPluginManager().disablePlugin(FindItemAddOn.getInstance());
            Bukkit.getPluginManager().enablePlugin(FindItemAddOn.getPlugin(FindItemAddOn.class));
            LoggerUtils.logInfo("&aPlugin restarted!");
            List allServerShops = FindItemAddOn.getQsApiInstance().getAllShops();
            if (allServerShops.size() == 0) {
                LoggerUtils.logWarning("&6Found &e0 &6shops on the server. If you ran &e/qs reload &6recently, please restart your server!");
            } else {
                LoggerUtils.logInfo("&aFound &e" + allServerShops.size() + " &ashops on the server.");
            }
        } else {
            Player player = (Player) commandSender;
            if (player.hasPermission(PlayerPerms.FINDITEM_RESTART.value()) || player.hasPermission(PlayerPerms.FINDITEM_ADMIN.value())) {
                Bukkit.getPluginManager().disablePlugin(FindItemAddOn.getInstance());
                Bukkit.getPluginManager().enablePlugin(FindItemAddOn.getPlugin(FindItemAddOn.class));
                player.sendMessage(Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + "&aPlugin restarted!"));
                List allServerShops = FindItemAddOn.getQsApiInstance().getAllShops();
                if (allServerShops.size() == 0) {
                    player.sendMessage(Utils.chat(
                            FindItemAddOn.getConfigProvider().PLUGIN_PREFIX
                                    + "&6Found &e0 &6shops on the server. If you ran &e/qs reload &6recently, please restart your server!"));
                } else {
                    player.sendMessage(Utils.chat(
                            FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + "&aFound &e" + allServerShops.size() + " &ashops on the server."));
                }
            } else {
                player.sendMessage(Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + "&cNo permission!"));
            }
        }
    }
}

