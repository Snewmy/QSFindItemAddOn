package io.myzticbean.finditemaddon.Commands.QSSubCommands;

import com.ghostchu.quickshop.api.command.CommandHandler;
import io.myzticbean.finditemaddon.FindItemAddOn;
import io.myzticbean.finditemaddon.Handlers.CommandHandler.CmdExecutorHandler;
import io.myzticbean.finditemaddon.Utils.Defaults.PlayerPerms;
import io.myzticbean.finditemaddon.Utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author myzticbean
 */
public class FindItemCmdHikariImpl implements CommandHandler<Player> {

    private final CmdExecutorHandler cmdExecutor;
    private final List<String> itemsList = new ArrayList<>();
    private final List<String> buyOrSellList = new ArrayList<>();

    public FindItemCmdHikariImpl() {

        cmdExecutor = new CmdExecutorHandler();
    }

    @Override
    public void onCommand(Player commandSender, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            commandSender.sendMessage(Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + "&cIncorrect usage!"));
        } else if (args.length == 1) {
            if (commandSender.hasPermission(PlayerPerms.FINDITEM_HIDESHOP.value())) {
                commandSender.sendMessage(Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + "&cIncorrect usage!"));

            } else {
                commandSender.sendMessage(Utils.chat(FindItemAddOn.getConfigProvider().PLUGIN_PREFIX + "&cYou don't have permission to use that!"));
            }
        } else {
            cmdExecutor.handleShopSearch(args[0], commandSender, args[1]);
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull Player sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (itemsList.isEmpty()) {
            for (Material mat : Material.values()) {
                itemsList.add(mat.name());
            }
        }
        if (buyOrSellList.isEmpty()) {
        }
        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String a : buyOrSellList) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(a);
                }
            }
            return result;
        } else if (args.length == 2) {
            for (String a : itemsList) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase())) {
                    result.add(a);
                }
            }
            return result;
        } else {
            return null;
        }

    }
}
