package io.myzticbean.finditemaddon;

import io.myzticbean.finditemaddon.Commands.SAPICommands.*;
import io.myzticbean.finditemaddon.ConfigUtil.ConfigProvider;
import io.myzticbean.finditemaddon.ConfigUtil.ConfigSetup;
import io.myzticbean.finditemaddon.Dependencies.PlayerWarpsPlugin;
import io.myzticbean.finditemaddon.Handlers.GUIHandler.Menus.ShopMenu;
import io.myzticbean.finditemaddon.Handlers.GUIHandler.PlayerMenuUtility;
import io.myzticbean.finditemaddon.Listeners.*;
import io.myzticbean.finditemaddon.Metrics.Metrics;
import io.myzticbean.finditemaddon.QuickShopHandler.QSApi;
import io.myzticbean.finditemaddon.QuickShopHandler.QSHikariAPIHandler;
import io.myzticbean.finditemaddon.ScheduledTasks.Task15MinInterval;
import io.myzticbean.finditemaddon.Utils.Defaults.PlayerPerms;
import io.myzticbean.finditemaddon.Utils.JsonStorageUtils.ShopSearchActivityStorageUtil;
import io.myzticbean.finditemaddon.Utils.LoggerUtils;
import io.myzticbean.finditemaddon.Utils.Utils;
import me.kodysimpson.simpapi.command.CommandManager;
import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import su.nightexpress.excellentcrates.CratesAPI;
import su.nightexpress.excellentcrates.CratesPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class FindItemAddOn extends JavaPlugin {

    private static FindItemAddOn plugin;
    public static String serverVersion;
    private final static int BS_PLUGIN_METRIC_ID = 12382;
    private final static int SPIGOT_PLUGIN_ID = 95104;
    private final static int REPEATING_TASK_SCHEDULE_MINS = 15 * 60 * 20;
    private static ConfigProvider configProvider;
    private static boolean qSHikariInstalled = false;
    private static QSApi qsApi;
    public static boolean isExcellentCratesInstalled = false;
    public CratesPlugin excellentCrates = null;

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    @Override
    public void onLoad() {
        LoggerUtils.logInfo("A Shop Search AddOn for QuickShop developed by Sam");

        // Show warning if it's a snapshot build
        if (this.getDescription().getVersion().toLowerCase().contains("snapshot")) {
            LoggerUtils.logWarning("This is a SNAPSHOT build! NOT recommended for production servers.");
        }


    }

    @Override
    public void onEnable() {
        plugin = this;
        if (!Bukkit.getPluginManager().isPluginEnabled("QuickShop")
                && !Bukkit.getPluginManager().isPluginEnabled("QuickShop-Hikari")) {
            LoggerUtils.logInfo("Delaying QuickShop hook as they are not enabled yet");
        } else {
            qSHikariInstalled = true;
        }

        if (Bukkit.getPluginManager().isPluginEnabled("ExcellentCrates")) {
            isExcellentCratesInstalled = true;
            this.excellentCrates = CratesAPI.PLUGIN;
        }

        // Registering Bukkit event listeners
        initBukkitEventListeners();

        // Handle config file
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);
        ConfigSetup.setupConfig();
        ConfigSetup.get().options().copyDefaults(true);
        ConfigSetup.checkForMissingProperties();
        ConfigSetup.saveConfig();
        initConfigProvider();
        ConfigSetup.copySampleConfig();
        initCommands();

        // Run plugin startup logic after server is done loading
        Bukkit.getScheduler().scheduleSyncDelayedTask(FindItemAddOn.getInstance(), () -> runPluginStartupTasks());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (qsApi != null) {
            ShopSearchActivityStorageUtil.saveShopsToFile();
        } else {
            LoggerUtils.logError("Uh oh! Looks like either this plugin has crashed or you don't have QuickShop-Hikari installed.");
        }
        LoggerUtils.logInfo("Bye!");
    }

    public static FindItemAddOn getInstance() {
        return plugin;
    }

    private void runPluginStartupTasks() {
//        if(!Bukkit.getPluginManager().isPluginEnabled("QuickShop")
//                && !Bukkit.getPluginManager().isPluginEnabled("QuickShop-Hikari")) {
//            LoggerUtils.logError("QuickShop is required to use this addon. Please install QuickShop and try again!");
//            LoggerUtils.logError("Both QuickShop-Reremake and QuickShop-Hikari are supported by this addon.");
//            LoggerUtils.logError("Download links:");
//            LoggerUtils.logError("» QuickShop-Reremake: https://www.spigotmc.org/resources/62575");
//            LoggerUtils.logError("» QuickShop-Hikari: https://www.spigotmc.org/resources/100125");
//            getServer().getPluginManager().disablePlugin(this);
//            return;
//        }
//        else if(Bukkit.getPluginManager().isPluginEnabled("QuickShop")) {
//            qSReremakeInstalled = true;
//            qsApi = new QSReremakeAPIHandler();
//            LoggerUtils.logInfo("Found QuickShop-Reremake");
//        }
//        else if(Bukkit.getPluginManager().isPluginEnabled("QuickShop-Hikari")) {
//            qSHikariInstalled = true;
//            qsApi = new QSHikariAPIHandler();
//            LoggerUtils.logInfo("Found QuickShop-Hikari");
//        }

        serverVersion = Bukkit.getServer().getVersion();
        LoggerUtils.logInfo("Server version found: " + serverVersion);

        if (!isQSHikariInstalled()) {
            LoggerUtils.logError("QuickShop is required to use this addon. Please install QuickShop and try again!");
            LoggerUtils.logError("Download link:");
            LoggerUtils.logError("» QuickShop-Hikari: https://www.spigotmc.org/resources/100125");
            getServer().getPluginManager().disablePlugin(this);
            return;
        } else {
            LoggerUtils.logInfo("Found QuickShop-Hikari");
            qsApi = new QSHikariAPIHandler();
            qsApi.registerSubCommand();
        }

        // Load all hidden shops from file
        ShopSearchActivityStorageUtil.loadShopsFromFile();

        // v2.0.0 - Migrating hiddenShops.json to shops.json
        ShopSearchActivityStorageUtil.migrateHiddenShopsToShopsJson();

        PlayerWarpsPlugin.setup();
        initExternalPluginEventListeners();

        // Initiate batch tasks
        LoggerUtils.logInfo("Registering tasks");
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Task15MinInterval(), 0, REPEATING_TASK_SCHEDULE_MINS);

        // init metrics
        LoggerUtils.logInfo("Registering anonymous bStats metrics");
        Metrics metrics = new Metrics(this, BS_PLUGIN_METRIC_ID);

    }

    private void initCommands() {
        LoggerUtils.logInfo("Registering commands");
        //initFindItemCmd();
        //initFindItemAdminCmd();
        initShopCmd();
    }

    private void initBukkitEventListeners() {
        LoggerUtils.logInfo("Registering Bukkit event listeners");
        this.getServer().getPluginManager().registerEvents(new PluginEnableEventListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerCommandSendEventListener(), this);
        this.getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }

    private void initExternalPluginEventListeners() {
        LoggerUtils.logInfo("Registering external plugin event listeners");
        if(PlayerWarpsPlugin.getIsEnabled()) {
            this.getServer().getPluginManager().registerEvents(new PWPlayerWarpRemoveEventListener(), this);
            this.getServer().getPluginManager().registerEvents(new PWPlayerWarpCreateEventListener(), this);
        }
    }

    public static ConfigProvider getConfigProvider() {
        return configProvider;
    }

    public static void initConfigProvider() {
        configProvider = new ConfigProvider();
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;
        if (playerMenuUtilityMap.containsKey(p)) {
            return playerMenuUtilityMap.get(p);
        } else {
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);
            return playerMenuUtility;
        }
    }

    public static int getPluginID() {
        return SPIGOT_PLUGIN_ID;
    }

    private void initShopCmd() {
        List<String> alias = new ArrayList<>();
        Class<? extends SubCommand>[] subCommands;

        subCommands = new Class[]{};
        try {
            CommandManager.createCoreCommand(
                    this,
                    "shop",
                    "Opens the shop search gui",
                    "/shop",
                    (commandSender, subCommandList) -> {
                        ShopMenu shopMenu = new ShopMenu(FindItemAddOn.getPlayerMenuUtility((Player) commandSender));
                        shopMenu.open();
                    },
                    alias,
                    subCommands);
            LoggerUtils.logInfo("Registered /shop command");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LoggerUtils.logError(e);
        }
    }

    private void initFindItemAdminCmd() {
        List<String> alias = List.of("fiadmin");
        try {
            CommandManager.createCoreCommand(
                    this,
                    "finditemadmin",
                    "Admin command for Shop Search addon",
                    "/finditemadmin",
                    (commandSender, subCommandList) -> {
                        if (
                                (commandSender.isOp())
                                        || (!commandSender.isOp() && (commandSender.hasPermission(PlayerPerms.FINDITEM_ADMIN.value())
                                        || commandSender.hasPermission(PlayerPerms.FINDITEM_RELOAD.value())))
                        ) {
                            commandSender.sendMessage(Utils.chat(""));
                            commandSender.sendMessage(Utils.chat("&7-----------------------------"));
                            commandSender.sendMessage(Utils.chat("&6&lShop Search Admin Commands"));
                            commandSender.sendMessage(Utils.chat("&7-----------------------------"));

                            for (SubCommand subCommand : subCommandList) {
                                commandSender.sendMessage(Utils.chat("&#ff1a1a" + subCommand.getSyntax() + " &#a3a3c2" + subCommand.getDescription()));
                            }
                            commandSender.sendMessage(Utils.chat(""));
                            commandSender.sendMessage(Utils.chat("&#b3b300Command alias:"));
                            alias.forEach(alias_i -> commandSender.sendMessage(Utils.chat("&8&l» &#2db300/" + alias_i)));
                            commandSender.sendMessage(Utils.chat(""));
                        }
                    },
                    alias,
                    ReloadSubCmd.class);
            LoggerUtils.logInfo("Registered /finditemadmin command");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LoggerUtils.logError(e);
        }
    }

    public static boolean isQSHikariInstalled() {
        return qSHikariInstalled;
    }


    public static void setQSHikariInstalled(boolean qSHikariInstalled) {
        FindItemAddOn.qSHikariInstalled = qSHikariInstalled;
    }

    public static QSApi getQsApiInstance() {
        return qsApi;
    }

}
