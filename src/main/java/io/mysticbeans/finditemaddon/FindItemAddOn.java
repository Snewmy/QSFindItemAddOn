package io.mysticbeans.finditemaddon;

import io.mysticbeans.finditemaddon.ConfigUtil.ConfigProvider;
import io.mysticbeans.finditemaddon.ConfigUtil.ConfigSetup;
import io.mysticbeans.finditemaddon.Dependencies.EssentialsXPlugin;
import io.mysticbeans.finditemaddon.Dependencies.PlayerWarpsPlugin;
import io.mysticbeans.finditemaddon.Dependencies.WGPlugin;
import io.mysticbeans.finditemaddon.Handlers.GUIHandler.PlayerMenuUtility;
import io.mysticbeans.finditemaddon.Listeners.*;
import io.mysticbeans.finditemaddon.Metrics.Metrics;
import io.mysticbeans.finditemaddon.SAPICommands.*;
import io.mysticbeans.finditemaddon.ScheduledTasks.Task15MinInterval;
import io.mysticbeans.finditemaddon.Utils.HiddenShopStorageUtil;
import io.mysticbeans.finditemaddon.Utils.LoggerUtils;
import io.mysticbeans.finditemaddon.Utils.PlayerPerms;
import io.mysticbeans.finditemaddon.Utils.UpdateChecker;
import me.kodysimpson.simpapi.colors.ColorTranslator;
import me.kodysimpson.simpapi.command.CommandList;
import me.kodysimpson.simpapi.command.CommandManager;
import me.kodysimpson.simpapi.command.SubCommand;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public final class FindItemAddOn extends JavaPlugin {

    private static Plugin plugin;
    public FindItemAddOn() { plugin = this; }
    public static Plugin getInstance() { return plugin; }
    public static String serverVersion;
    private final static int bsPLUGIN_METRIC_ID = 12382;
    private final static int SPIGOT_PLUGIN_ID = 95104;
    private static ConfigProvider configProvider;
    private static boolean isPluginOutdated = false;

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        LoggerUtils.logInfo("A QuickShop AddOn by &aronsane");
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);
        ConfigSetup.setupConfig();
        ConfigSetup.get().options().copyDefaults(true);
        ConfigSetup.checkForMissingProperties();
        ConfigSetup.saveConfig();
        initConfigProvider();

        if(!Bukkit.getPluginManager().isPluginEnabled("QuickShop")) {
            LoggerUtils.logError("&cQuickShop is required to use this addon. Please install QuickShop and try again!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        else {
            LoggerUtils.logInfo("Found QuickShop");
        }

        serverVersion = Bukkit.getServer().getVersion();
        LoggerUtils.logInfo("Server version found: " + serverVersion);
        initCommands();
        initEvents();

        PlayerWarpsPlugin.setup();
        EssentialsXPlugin.setup();
        WGPlugin.setup();

        // Load all hidden shops from file
        HiddenShopStorageUtil.loadHiddenShopsFromFile();

        // Initiate batch tasks
        LoggerUtils.logInfo("Registering tasks");
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Task15MinInterval(), 0, 15*60*20);

        // init metrics
        LoggerUtils.logInfo("Registering bStats metrics");
        Metrics metrics = new Metrics(this, bsPLUGIN_METRIC_ID);

        // Check for plugin updates
        new UpdateChecker(this, SPIGOT_PLUGIN_ID).getLatestVersion(version -> {
            if(this.getDescription().getVersion().equalsIgnoreCase(version)) {
                LoggerUtils.logInfo("&2Plugin is up to date!");
            } else {
                isPluginOutdated = true;
                LoggerUtils.logWarning("Plugin has an update! (Version: " + this.getDescription().getVersion() + ")");
                LoggerUtils.logWarning("Download the latest version here: &7https://www.spigotmc.org/resources/" + SPIGOT_PLUGIN_ID + "/");
            }
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        HiddenShopStorageUtil.saveHiddenShopsToFile();
        LoggerUtils.logInfo("Bye!");
    }

    private void initCommands() {
        LoggerUtils.logInfo("Registering commands");
//        Objects.requireNonNull(this.getCommand("finditem")).setExecutor(new FindItemCommand());
//        Objects.requireNonNull(this.getCommand("finditem")).setTabCompleter(new FindItemCmdTabCompleter());
        String alias;
        if(StringUtils.isEmpty(FindItemAddOn.getConfigProvider().FIND_ITEM_TO_SELL_AUTOCOMPLETE)
                || StringUtils.containsIgnoreCase(FindItemAddOn.getConfigProvider().FIND_ITEM_TO_SELL_AUTOCOMPLETE, " ")) {
            alias = "shopsearch";
        }
        else {
            alias = FindItemAddOn.getConfigProvider().FIND_ITEM_COMMAND_ALIAS;
        }
        // Register the subcommands under a core command
        try {
            CommandManager.createCoreCommand(
                    this,
                    "finditem",
                    "Search for items from all shops using an interactive GUI",
                    "/finditem",
                    new CommandList() {
                @Override
                public void displayCommandList(CommandSender commandSender, List<SubCommand> subCommandList) {
                    commandSender.sendMessage(ColorTranslator.translateColorCodes(""));
                    commandSender.sendMessage(ColorTranslator.translateColorCodes("&7--------------------------"));
                    commandSender.sendMessage(ColorTranslator.translateColorCodes("&6&lShop Search Commands"));
                    commandSender.sendMessage(ColorTranslator.translateColorCodes("&7--------------------------"));
                    List<String> adminCmds = Arrays.asList("reload", "restart");
                    for (SubCommand subCommand : subCommandList) {
                        if (adminCmds.contains(subCommand.getName())) {
                            if (commandSender.isOp()) {
                                commandSender.sendMessage(ColorTranslator.translateColorCodes("&c" + subCommand.getSyntax() + " &7" + subCommand.getDescription()));
                            } else if (!commandSender.isOp() && (commandSender.hasPermission(PlayerPerms.FINDITEM_ADMIN.toString())
                                    || commandSender.hasPermission(PlayerPerms.FINDITEM_RELOAD.toString()))) {
                                commandSender.sendMessage(ColorTranslator.translateColorCodes("&c" + subCommand.getSyntax() + " &7" + subCommand.getDescription()));
                            }
                        } else {
                            commandSender.sendMessage(ColorTranslator.translateColorCodes("&e" + subCommand.getSyntax() + " &7" + subCommand.getDescription()));
                        }
                    }
                    commandSender.sendMessage(ColorTranslator.translateColorCodes(""));
                }
            },
            Arrays.asList(alias),
            SellSubCmd.class,
            BuySubCmd.class,
            HideShopSubCmd.class,
            RevealShopSubCmd.class,
            ReloadSubCmd.class,
            RestartSubCmd.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LoggerUtils.logError(e.getMessage());
            e.printStackTrace();
        }
    }

    private void initEvents() {
        LoggerUtils.logInfo("Registering events");
        this.getServer().getPluginManager().registerEvents(new PlayerCommandSendListener(), this);
        this.getServer().getPluginManager().registerEvents(new MenuListener(), this);
        this.getServer().getPluginManager().registerEvents(new PWPlayerWarpCreateListener(), this);
        this.getServer().getPluginManager().registerEvents(new PWPlayerWarpRemoveListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(), this);
    }
    
    public static ConfigProvider getConfigProvider() {
        return configProvider;
    }

    public static void initConfigProvider() {
        configProvider = new ConfigProvider();
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player p){
        PlayerMenuUtility playerMenuUtility;
        if(playerMenuUtilityMap.containsKey(p)) {
            return playerMenuUtilityMap.get(p);
        }
        else {
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);
            return playerMenuUtility;
        }
    }

    public static boolean getPluginOutdated() {
        return isPluginOutdated;
    }

    public static int getPluginID() {
        return SPIGOT_PLUGIN_ID;
    }
}
